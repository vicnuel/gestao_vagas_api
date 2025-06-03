package com.vicnuel.gestao_vagas.modules.candidate.useCases;

import com.vicnuel.gestao_vagas.exceptions.UserFoundException;
import com.vicnuel.gestao_vagas.modules.candidate.CandidateRepository;
import com.vicnuel.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidate) {
        this.candidateRepository.findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });


        return this.candidateRepository.save(candidate);
    }
}
