package com.nyanmyohtet.tdd.persistence;

import com.nyanmyohtet.tdd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}

