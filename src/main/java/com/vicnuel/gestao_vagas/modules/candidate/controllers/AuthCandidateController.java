package com.vicnuel.gestao_vagas.modules.candidate.controllers;

import com.vicnuel.gestao_vagas.modules.candidate.dto.AuthCandidateDTO;
import com.vicnuel.gestao_vagas.modules.candidate.useCases.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("auth")
    public ResponseEntity<Object> loginCompany(@RequestBody AuthCandidateDTO authCandidateDTO)  {
        try {
            return ResponseEntity.ok().body( this.authCandidateUseCase.execute(authCandidateDTO));
        } catch (AuthenticationException | UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
