package com.sim.ch10ex3.domain.Token.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sim.ch10ex3.domain.Token.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	Optional<Token> findByIdentifier(String identifier);
}
