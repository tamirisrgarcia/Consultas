package dh.consultas.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class Paciente extends Pessoa {
    private String rg;
}
