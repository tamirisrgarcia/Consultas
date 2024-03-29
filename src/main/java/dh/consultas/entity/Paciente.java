package dh.consultas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    @Size(min = 7, max = 10, message = "RG fora dos parametros")
    private String rg;

    private Timestamp alta;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    public Paciente(String nome, String sobrenome, String rg) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
    }
}