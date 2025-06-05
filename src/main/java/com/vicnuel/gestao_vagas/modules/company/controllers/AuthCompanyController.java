package com.vicnuel.gestao_vagas.modules.company.controllers;

import com.vicnuel.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.vicnuel.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("company")
    public ResponseEntity<Object> loginCompany(@RequestBody AuthCompanyDTO authCompanyDTO)  {
        try {
            return ResponseEntity.ok().body(Map.of("token", this.authCompanyUseCase.execute(authCompanyDTO)));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
