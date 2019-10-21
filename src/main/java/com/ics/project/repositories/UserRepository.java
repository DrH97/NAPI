package com.ics.project.repositories;

import com.ics.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dr H
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByIdNumber(String idNumber);
}
