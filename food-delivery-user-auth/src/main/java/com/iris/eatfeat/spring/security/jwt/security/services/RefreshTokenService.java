package com.iris.eatfeat.spring.security.jwt.security.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iris.eatfeat.spring.security.jwt.exception.TokenRefreshException;
import com.iris.eatfeat.spring.security.jwt.models.RefreshToken;
import com.iris.eatfeat.spring.security.jwt.models.User;
import com.iris.eatfeat.spring.security.jwt.repository.RefreshTokenRepository;
import com.iris.eatfeat.spring.security.jwt.repository.UserRepository;

@Service
public class RefreshTokenService {
	@Value("${bezkoder.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();

		User user = userRepository.findById(userId).get();

		refreshToken.setUser(user);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		Optional<RefreshToken> first = refreshTokenRepository.findAll().stream()
				.filter(p -> p.getUser().getId().equals(user.getId())).findFirst();
		if (first.isPresent() && !first.isEmpty()) {
			RefreshToken savedRefreshToken = first.get();
			refreshTokenRepository.deleteById(savedRefreshToken.getId());
		}

		refreshToken = refreshTokenRepository.save(refreshToken);

		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

	@Transactional
	public int deleteByUserId(Long userId) {
		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	}
}
