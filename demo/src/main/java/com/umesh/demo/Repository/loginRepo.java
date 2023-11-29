package com.umesh.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.umesh.demo.Model.UserDetails;

@EnableJpaRepositories
@Repository
public interface loginRepo extends JpaRepository<UserDetails, Integer> {

    UserDetails findByUsername(String username);

    // userdetails model
    List<UserDetails> findAll();
}
