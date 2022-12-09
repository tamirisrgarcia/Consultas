package dh.consultas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String sobrenome;

    @NotNull
    @Column(unique = true)
    @Size(min = 7, max = 9, message = "RG fora dos parametros")
    private String rg;

    private LocalDateTime alta;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
}