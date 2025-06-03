package com.vicnuel.gestao_vagas.modules.company.repositories;

import com.vicnuel.gestao_vagas.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    Optional<CompanyEntity> findByNameOrEmail(String username, String email );
}
