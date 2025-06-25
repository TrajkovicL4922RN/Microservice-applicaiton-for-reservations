package com.example.demo.repository;


import com.example.demo.domain.Role;
import com.example.demo.domain.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName name);
}
