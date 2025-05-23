package com.vicnuel.gestao_vagas.modules.candidate.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {

    private UUID id;
    @NotBlank
    @Length(min = 3, max = 250)
    private String name;
    @NotBlank
    @Pattern(regexp = "^[a-z0-9_-]{3,16}$",
            message = "O nome de usuário deve conter de 3 a 16 caracteres alfanuméricos (letras minúsculas, números, underscores e hífens).")
    private String username;
    @NotBlank
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank
    @Length(min = 10, max = 32)
    private String password;
    @Length( max = 250)
    private String description;
    private String curriculum;


}
