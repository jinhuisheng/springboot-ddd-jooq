package com.sh.role;

import com.sh.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huisheng.jin
 * @version 2019/11/28.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
