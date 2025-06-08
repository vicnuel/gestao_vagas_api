package com.vicnuel.gestao_vagas.modules.candidate.useCases;

import com.vicnuel.gestao_vagas.exceptions.UserFoundException;
import com.vicnuel.gestao_vagas.modules.candidate.CandidateRepository;
import com.vicnuel.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var pass = passwordEncoder.encode(candidate.getPassword());
        candidate.setPassword(pass);

        return this.candidateRepository.save(candidate);
    }
}
