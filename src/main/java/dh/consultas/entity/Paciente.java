package dh.consultas.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "paciente")

public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @Column
    private String  nome;

    @Column
    private String  sobrenome;

    @Column
    private String  rg;

    @Column
    private LocalDateTime alta;

    @OneToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
}