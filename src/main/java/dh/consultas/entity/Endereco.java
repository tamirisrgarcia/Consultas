package dh.consultas.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String rua;

    @NotNull @Positive
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

    @NotNull
    @Column(nullable = false, length = 2)
    private String uf;

    @NotNull @Positive
    @Column(nullable = false, length = 8)
    private String cep;
}