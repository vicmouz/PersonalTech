/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MarcosBrasileiro
 */
@Entity
@Table(name = "TB_AV_PERG")
public class RespostasAvaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "TXT_RESP_ALUNO", nullable = false, length = 1000)
    private String txt_resposta;
    
    @NotNull
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "ID_AV", referencedColumnName = "ID")
    private Avaliacao avaliacao;
    
    @NotNull
    @ManyToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "ID_PERG", referencedColumnName = "ID")
    private Pergunta pergunta;

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }
    
    

    public Long getId() {
        return id;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTxt_resposta() {
        return txt_resposta;
    }

    public void setTxt_resposta(String txt_resposta) {
        this.txt_resposta = txt_resposta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespostasAvaliacao)) {
            return false;
        }
        RespostasAvaliacao other = (RespostasAvaliacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.personaltech.RespostasAvaliacao[ id=" + id + " ]";
    }

}
