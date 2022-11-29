package dh.consultas.entity.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dh.consultas.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaDTO {
    private String paciente;        //todo Paciente paciente ?
    private String dentista;        //todo Dentista dentista ?
    private LocalDateTime consulta;
    /*
    todo
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;
     */
}