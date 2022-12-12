package dh.consultas.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    private String nome;
    private String sobrenome;
    private String rg;
    private String alta;

    public PacienteDTO(String nome, String sobrenome, String rg) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
    }
}