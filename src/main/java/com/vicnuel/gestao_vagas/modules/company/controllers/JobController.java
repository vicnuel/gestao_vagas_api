package com.vicnuel.gestao_vagas.modules.company.controllers;

import com.vicnuel.gestao_vagas.modules.company.entities.JobEntity;
import com.vicnuel.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    CreateJobUseCase createJobUseCase;

    @PostMapping("")
    ResponseEntity<Object> create(@Valid @RequestBody JobEntity job) {
        try {
            var result =  this.createJobUseCase.execute(job);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
