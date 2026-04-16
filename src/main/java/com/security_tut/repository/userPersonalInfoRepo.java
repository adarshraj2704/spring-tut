package com.security_tut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.security_tut.entity.UsersPersonalInfo;
import java.util.Optional;

@Repository
public interface userPersonalInfoRepo extends JpaRepository<UsersPersonalInfo, Long> {
	
	Optional<UsersPersonalInfo> findByEmail(String email);

}
