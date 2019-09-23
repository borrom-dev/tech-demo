package com.angkorsuntrix.techdemo.repository;

import com.angkorsuntrix.techdemo.entity.Role;
import com.angkorsuntrix.techdemo.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
