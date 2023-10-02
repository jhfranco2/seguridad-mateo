package com.seguridad.seguridad.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.seguridad.seguridad.entities.User;

@Repository
public interface UsersRepository extends JpaRepository<User,Long>{

    Optional <User> findByUserName(String username);
}
