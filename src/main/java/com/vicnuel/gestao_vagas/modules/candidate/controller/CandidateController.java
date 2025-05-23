package com.vicnuel.gestao_vagas.modules.candidate.controller;

import com.vicnuel.gestao_vagas.modules.candidate.model.CandidateEntity;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("candidate")
public class CandidateController {

    @PostMapping("")
    public void create (@Valid @RequestBody CandidateEntity candidate) {
        System.out.println("Candidato: ");
        System.out.println(candidate.getEmail());
    }
}
