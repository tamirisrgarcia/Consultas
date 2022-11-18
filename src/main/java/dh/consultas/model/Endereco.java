package dh.consultas.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class Endereco {
    private Long id;
    private String rua, numero, complemento, bairro, cidade, estado, cep;
}
