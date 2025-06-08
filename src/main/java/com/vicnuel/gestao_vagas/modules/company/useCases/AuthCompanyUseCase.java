package com.vicnuel.gestao_vagas.modules.company.useCases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vicnuel.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.vicnuel.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Username ou password incorrect");
        });


        // verificar a senha
        var passwordsMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordsMatches) {
            throw new AuthenticationException("Username ou password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        var payload = Map.of("username", company.getUsername());
        var expires = Instant.now().plus(2, ChronoUnit.HOURS);

        var token = JWT.create().withIssuer("vicnuel")
                .withSubject(company.getId().toString())
                .withPayload(payload)
                .withExpiresAt(expires)
                .sign(algorithm);

        return token;
    }
}
