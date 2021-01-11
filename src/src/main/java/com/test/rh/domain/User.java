package com.test.rh.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rh_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "name")
    @NotNull(message = "o Nome do usuário é obrigatório")
    private String name;

    @Column(name = "gender", length = 1)
    @Size(max = 1)
    private String gender;

    @Column(name = "email")
    @Pattern(regexp = "colocar regex aqui", message = "Email inválido")
    private String email;

    @Column(name = "dt_birth")
    @NotNull(message = "data de nascimento é obrigatória")
    private LocalDate dtBirth;

    @Column(name = "naturalness")
    private String naturalness;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "cpf", unique = true)
    @NotNull(message = "cpf é obrigatório")
    private String cpf;

    @Column(name = "dt_creation")
    private LocalDateTime dtCreation = LocalDateTime.now();

    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

}
