package com.globallogic.userapi.repository;

import com.globallogic.userapi.entities.UserPhone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<UserPhone, String> {
}
