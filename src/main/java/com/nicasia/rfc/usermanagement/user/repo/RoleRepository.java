package com.nicasia.rfc.usermanagement.user.repo;

import com.nicasia.rfc.shared.enums.RoleName;
import com.nicasia.rfc.usermanagement.user.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

    @Query("Select Roles from Roles where Roles.name=:roleName")
    Optional<Roles> finByName(@Param("rolename") RoleName roleName);

}
