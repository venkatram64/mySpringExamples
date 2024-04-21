package com.venkat.post.Repository;

import com.venkat.post.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u where CONCAT(u.id, ' ', u.firstName, ' ', u.lastName, ' ', u.email, ' '," +
            " u.password, ' ', u.gender, ' ', u.address, ' ', u.married, ' ', u.profession, ' ') LIKE %?1%")
    public Page<User> findAll(String keyword, Pageable pageable);
}
