package br.com.hub.core.repository.person.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Francislin Dos Reis on 28/11/2018
 */
@Entity
@Data
@Table(name = "pessoa_juridica")
public class LegalPersonEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String cnpj;

    private String reasonSocial;

    private String nameOfFantasy;

    @Tolerate
    public LegalPersonEntity() {
        // Default method constructor for hibernate
    }

    @Builder
    public LegalPersonEntity(Long id, String cnpj, String reasonSocial, String nameOfFantasy) {
        this.id = id;
        this.cnpj = cnpj;
        this.reasonSocial = reasonSocial;
        this.nameOfFantasy = nameOfFantasy;
    }
}
