package com.dh.consultas.entity;

import dh.consultas.entity.Dentista;
import dh.consultas.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Dentista dentista;

    private Timestamp dataHoraConsulta;
}