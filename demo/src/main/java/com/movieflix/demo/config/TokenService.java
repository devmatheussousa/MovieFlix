package com.movieflix.demo.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.movieflix.demo.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenService {

    @Value("${movieFlix.security.jwt.secret}")
    private String secret;

    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret); // Escolha o algoritmo de assinatura, por exemplo, HMAC SHA-256

        return JWT.create()
                .withSubject(user.getEmail()) // Defina o assunto do token, geralmente o email ou ID do usuário
                .withClaim("userId", user.getId()) // Salve o ID do usuário como uma claim personalizada
                .withClaim("userName", user.getName()) // Sabe o nome do usuário como uma claim personalizada
                .withExpiresAt(Instant.now().plusSeconds(86400)) // Defina a data de expiração do token (por exemplo, 24 horas)
                .withIssuedAt(Instant.now())
                .withIssuer("API MovieFlix") // Defina o emissor do token
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData
                    .builder()
                    .id(jwt.getClaim("userId").asLong())
                    .name(jwt.getClaim("userName").asString())
                    .email(jwt.getSubject())
                    .build());
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }

    }
}
