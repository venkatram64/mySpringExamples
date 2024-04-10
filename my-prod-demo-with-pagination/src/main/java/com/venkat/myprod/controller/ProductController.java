package com.venkat.myprod.controller;

import com.venkat.myprod.model.Product;
import com.venkat.myprod.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String myHome(){
        return "home";
    }

    @GetMapping
    public String getAllProducts(Model model){
        return getPaginatedProducts("",1, "5","id", "asc", model);
    }

    @GetMapping("/page")
    public String getPaginatedProducts(@RequestParam(required = false) String keyword,
                                       @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "5") String pageSize,
                                       @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                       @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                                       Model model){
        Page<Product> page = null;
        if(keyword == null || keyword.equals("")){
            page = productService.findAll(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sortField, sortDir);
        }else{
            page = productService.findAllByKeywordAndPagination(keyword, Integer.valueOf(pageNo), Integer.valueOf(pageSize), sortField, sortDir);
            model.addAttribute("keyword", keyword);
        }
        List<Product> products = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("products", products);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);//sort direction --> ascending or descending
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");//reversing the sort direction
        return "index";
    }

    @GetMapping("/new")
    public String showCreateProduct(Model model){ //to display new product screen
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", "Create new Product");
        return "createProduct";
    }

    @PostMapping("/save")
    public String saveProduct(Product product, RedirectAttributes redirectAttributes){//to save the new product
        try{
            this.productService.save(product);
            redirectAttributes.addFlashAttribute("message", "The Product has been saved successfully!");
        }catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/products"; //it calls the @GetMapping method
    }

    @GetMapping("/{id}")  //to display edit screen
    public String editProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = productService.findById(id).get();
            model.addAttribute("product", product);
            model.addAttribute("pageTitle", "Edit Product (Id: " + id + ")");
            return "createProduct";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/products"; //it calls the @GetMapping method
        }
    }

    @GetMapping("/delete/{id}")//to delete the product
    public String deleteProduct(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "The Product with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/products"; //it calls the @GetMapping method
    }

}
