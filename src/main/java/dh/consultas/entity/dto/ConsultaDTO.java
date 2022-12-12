package dh.consultas.entity.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dh.consultas.entity.Dentista;
import dh.consultas.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaDTO {

    private Long id;
    @NotBlank
    private Paciente paciente;
    @NotBlank
    private Dentista dentista;
    @NotBlank
    @Column(nullable = false)
    private Timestamp dataHoraConsulta;
}