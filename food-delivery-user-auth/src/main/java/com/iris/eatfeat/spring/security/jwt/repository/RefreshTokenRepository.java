package com.iris.eatfeat.spring.security.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iris.eatfeat.spring.security.jwt.models.RefreshToken;
import com.iris.eatfeat.spring.security.jwt.models.User;

import jakarta.transaction.Transactional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);

	@Modifying
	@Transactional
	@Query("Delete from refreshtoken r where r.user = ?1")
	int deleteByUser(User user);

}
