package br.com.hub.core.repository.conta.models;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;

/**
 * Created by Francislin Dos Reis on 06/09/2018
 */
@Entity
@Data
@Table(name = "Situacao")
public class Situacao {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "Descricao")
    private String desc;

    @Tolerate
    public Situacao() {
        // Default method constructor for hibernate
    }

    @Builder
    public Situacao(Long id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}
