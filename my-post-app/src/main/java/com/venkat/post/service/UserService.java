package com.venkat.post.service;

import com.venkat.post.Repository.UserRepository;
import com.venkat.post.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public Optional<User> findUserById(Integer id){
        return this.userRepository.findById(id);
    }

    public User save(User user){
        return this.userRepository.save(user);
    }

    public User update(User user){
        return this.userRepository.save(user);
    }

    public void deleteById(Integer id){
        this.userRepository.deleteById(id);
    }

    public Page<User> findAll(int pageNo, int pageSize, String sortField, String sortDirection){

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.userRepository.findAll(pageable);
    }

    public Page<User> findAllByKeywordAndPagination(String keyword, int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(keyword, pageable);
    }
}
