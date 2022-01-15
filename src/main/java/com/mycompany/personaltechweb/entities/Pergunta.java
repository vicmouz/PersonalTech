package com.mycompany.personaltechweb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author MarcosBrasileiro
 */
@Entity
@Table(name = "TB_PERGUNTA")
@NamedQueries(
        {
            @NamedQuery(
                    name = Pergunta.PERGUNTA,
                    query = "SELECT a FROM Pergunta a ORDER BY a.pergunta"
            )})
public class Pergunta implements Serializable {
 public static final String PERGUNTA = "Pergunta";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "TXT_PERGUNTA", nullable = false, length = 2000)
    private String pergunta;
    
    @NotNull
    @OneToMany(mappedBy ="pergunta" ,fetch = LAZY,
            cascade = ALL, orphanRemoval = false)
    private List<RespostasAvaliacao> respostas;

    void addResposta(RespostasAvaliacao resposta) {
        if (respostas == null) {
            respostas = new ArrayList<>();
        }
        respostas.add(resposta);
        resposta.setPergunta(this);
    }

    void removeResposta(RespostasAvaliacao resposta) {
        if (respostas == null) {
            return;
        }
        respostas.remove(resposta);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
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
        if (!(object instanceof Pergunta)) {
            return false;
        }
        Pergunta other = (Pergunta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getPergunta();
    }

}
