package com.vicnuel.gestao_vagas.modules.company.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateJobDTO {
    @NotBlank(message = "Campo obrigatório")
    @NotNull
    private String description;
    @NotBlank(message = "Campo obrigatório")
    @NotNull
    private String level;
    private String benefits;
}
