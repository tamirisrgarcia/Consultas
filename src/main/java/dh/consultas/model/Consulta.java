package dh.consultas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {
    private Paciente paciente;
    private Dentista dentista;
    private LocalDateTime horario;

}