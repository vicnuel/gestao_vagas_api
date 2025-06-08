package com.vicnuel.gestao_vagas.modules.candidate;

import com.vicnuel.gestao_vagas.modules.candidate.entities.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    // O Spring Data JPA busca metodos com "findBy"
    // O operador "Or" ou "And" pode ser usado e tem funções diferentes
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntity> findByUsername(String username);
}
