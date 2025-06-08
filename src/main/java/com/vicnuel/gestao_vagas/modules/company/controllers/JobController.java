package com.vicnuel.gestao_vagas.modules.company.controllers;

import com.vicnuel.gestao_vagas.modules.company.dto.CreateJobDTO;
import com.vicnuel.gestao_vagas.modules.company.entities.JobEntity;
import com.vicnuel.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    CreateJobUseCase createJobUseCase;

    @PostMapping("")
    ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO job, HttpServletRequest request) {
        try {
            var companyId = request.getAttribute("company_id");

            var jobEntity = JobEntity.builder()
                    .benefits(job.getBenefits())
                    .description(job.getDescription())
                    .level(job.getLevel())
                    .companyId(UUID.fromString(companyId.toString()))
                    .build();

            var result =  this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
