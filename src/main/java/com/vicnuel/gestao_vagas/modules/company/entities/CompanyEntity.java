package com.vicnuel.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    @Length(min = 3, max = 250)
    private String name;
    @NotBlank
    @Pattern(regexp = "^[a-z0-9_-]{3,16}$",
            message = "O nome da organização deve conter de 3 a 16 caracteres alfanuméricos (letras minúsculas, números, underscores e hífens).")
    private String username;
    @NotBlank
    @Email(message = "E-mail inválido")
    private String email;

    // no máximo 32 caracteres, pelo menos 10 caracteres. No banco de dados, o tamanho máximo é 250 caracteres.
    @NotBlank
    @Length(min = 10, max = 32)
    @Column(length = 150)
    private String password;
    @Length( max = 250)
    private String website;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
