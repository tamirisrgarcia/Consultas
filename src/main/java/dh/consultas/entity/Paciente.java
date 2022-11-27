package dh.consultas.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Paciente {
    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    private String rg;
    private String alta;
    @ManyToOne
    private Endereco endereco;
}