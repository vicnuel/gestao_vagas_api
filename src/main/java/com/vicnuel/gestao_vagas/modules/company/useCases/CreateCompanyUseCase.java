package com.vicnuel.gestao_vagas.modules.company.useCases;

import com.vicnuel.gestao_vagas.exceptions.UserFoundException;
import com.vicnuel.gestao_vagas.modules.company.entities.CompanyEntity;
import com.vicnuel.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
        .findByNameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserFoundException();
                });

        var pass = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(pass);
        return this.companyRepository.save(companyEntity);
    }
}
