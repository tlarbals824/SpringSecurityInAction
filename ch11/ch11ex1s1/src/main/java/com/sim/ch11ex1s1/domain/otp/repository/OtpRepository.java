package com.sim.ch11ex1s1.domain.otp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sim.ch11ex1s1.domain.otp.entity.Otp;

public interface OtpRepository extends JpaRepository<Otp, Long> {

	Optional<Otp> findByUsername(String username);
}
