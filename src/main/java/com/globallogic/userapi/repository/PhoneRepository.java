package com.globallogic.userapi.repository;

import com.globallogic.userapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<User.UserPhone, Long> {
}
