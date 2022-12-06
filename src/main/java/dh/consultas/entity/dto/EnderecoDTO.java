package dh.consultas.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {

    @NotBlank
    @Column(nullable = false, length = 50)
    private String rua;

    @NotBlank
    @PositiveOrZero
    @Column(nullable = false, length = 4)
    private int numero;

    @Column(length = 100)
    private String complemento;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String bairro;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String cidade;

    @NotBlank
    @Column(nullable = false, length = 2)
    private String uf;

    @NotBlank
    @Positive
    @Column(nullable = false, length = 8)
    private String cep;
}