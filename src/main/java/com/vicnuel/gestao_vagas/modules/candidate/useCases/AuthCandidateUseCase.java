package com.vicnuel.gestao_vagas.modules.candidate.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vicnuel.gestao_vagas.modules.candidate.CandidateRepository;
import com.vicnuel.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.vicnuel.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateDTO authCandidateDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateDTO.username()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username ou password incorrect");
        });


        // verificar a senha
        var passwordsMatches = this.passwordEncoder.matches(authCandidateDTO.password(), candidate.getPassword());

        if (!passwordsMatches) {
            throw new AuthenticationException("Username ou password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        var payload = Map.of("username", candidate.getUsername());
        var expires = Instant.now().plus(1, ChronoUnit.HOURS);

        var token = JWT.create().withIssuer("vicnuel")
                .withSubject(candidate.getId().toString())
                .withPayload(payload)
                .withClaim("roles", List.of( "candidate"))
                .withExpiresAt(expires)
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .token(token)
                .expires(expires.toEpochMilli())
                .build();
    }
}
