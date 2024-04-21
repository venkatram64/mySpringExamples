package com.venkat.post.controller;

import com.venkat.post.model.User;
import com.venkat.post.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String myHome(){
        return "home";
    }

    @GetMapping("/page")
    public String getPaginatedUsers(@RequestParam(required = false) String keyword,
                                       @RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = "5") String pageSize,
                                       @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                       @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                                       Model model){
        Page<User> page = null;
        if(keyword == null || keyword.equals("")){
            page = userService.findAll(Integer.valueOf(pageNo), Integer.valueOf(pageSize), sortField, sortDir);
        }else{
            page = userService.findAllByKeywordAndPagination(keyword, Integer.valueOf(pageNo), Integer.valueOf(pageSize), sortField, sortDir);
            model.addAttribute("keyword", keyword);
        }
        List<User> users = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("users", users);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);//sort direction --> ascending or descending
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc": "asc");//reversing the sort direction
        return "index";
    }

    @GetMapping
    public String findAll(Model model){
        return this.getPaginatedUsers("",1, "5","id", "asc", model);
    }

/*    @GetMapping("/{id}")
    public User findByUser(@PathVariable("id") Integer id){
        return this.userService.findUserById(id).get();
    }*/

    @GetMapping("/view")
    public String showView(Model model){
        User user = new User();
        model.addAttribute("user", user);
        List<String> professions = Arrays.asList("Developer", "Sr. Developer", "Architect", "Tester");
        model.addAttribute("professions", professions);
        model.addAttribute("pageTitle", "Add User");
        return "user-form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes){//to save the new product
        try{
            this.userService.save(user);
            redirectAttributes.addFlashAttribute("message", "The User has been saved successfully!");
        }catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/users"; //it calls the @GetMapping method
    }

    @GetMapping("/{id}")  //to display edit screen
    public String editUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            User user = userService.findUserById(id).get();
            model.addAttribute("user", user);
            List<String> professions = Arrays.asList("Developer", "Sr. Developer", "Architect", "Tester");
            model.addAttribute("professions", professions);
            model.addAttribute("pageTitle", "Edit User (Id: " + id + ")");
            return "user-form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/users"; //it calls the @GetMapping method
        }
    }

    @GetMapping("/delete/{id}")//to delete the user
    public String deleteUser(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "The User with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users"; //it calls the @GetMapping method
    }
}
