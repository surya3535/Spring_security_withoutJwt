package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.UserCustom;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<UserCustom, Integer> {
	
	Optional<UserCustom> findByUserName(String userName);

}
