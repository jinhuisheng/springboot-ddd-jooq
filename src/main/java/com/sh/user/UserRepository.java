package com.sh.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author huisheng.jin
 * @version 2019/11/26.
 */
@Repository
public interface UserRepository extends JpaRepository<Employee,String> {
}
