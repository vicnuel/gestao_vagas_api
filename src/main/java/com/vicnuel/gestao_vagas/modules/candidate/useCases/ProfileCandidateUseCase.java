package com.vicnuel.gestao_vagas.modules.candidate.useCases;

import com.vicnuel.gestao_vagas.modules.candidate.CandidateRepository;
import com.vicnuel.gestao_vagas.modules.candidate.dto.ProfileCandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateDTO execute (UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        return ProfileCandidateDTO.builder().id(candidate.getId())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .description(candidate.getDescription())
                .username(candidate.getUsername()).build();
    }
}
