package com.mycompany.personaltechweb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author MarcosBrasileiro
 */
@Entity
@Table(name = "TB_PERSONALTRAINER")
@DiscriminatorValue(value = "P")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
@NamedQueries({
    @NamedQuery(
            name  = PersonalTrainer.QUANTIDADE_PERSONAL_TRAINER,
            query = "SELECT pt FROM PersonalTrainer pt"
    ),
    @NamedQuery(
            name  = PersonalTrainer.CONSULTAR_POR_ID,
            query = "SELECT pt FROM Usuario pt WHERE pt.id = ?1"
    ),
     @NamedQuery(
                    name = PersonalTrainer.PERSONALS,
                    query = "SELECT a FROM PersonalTrainer a ORDER BY a.nome"
            ),
    @NamedQuery(
            name  = PersonalTrainer.CONSULTAR_POR_LOGIN,
            query = "SELECT pt FROM Usuario pt WHERE pt.login = ?1 and  pt.senha = ?1" 
    ),
})

public class PersonalTrainer extends Usuario implements Serializable {

    /**
     * CONSTANTE PARA ACESSAR AS NAMED QUERY
     */
    public static final String CONSULTAR_POR_ID = "ConsultarPorID";
    public static final String CONSULTAR_POR_LOGIN = "ConsultarPorLogin";
    public static final String QUANTIDADE_PERSONAL_TRAINER = "QuantidadePersonalTrainer";
    public static final String REMOVER_POR_ID = "RemoverPorID";
    public static final String PERSONALS = "Personals";
    /*
    
     */
    @Size(max = 5)
    @ElementCollection
    @CollectionTable(name = "TB_TELEFONE_PT",
            joinColumns = @JoinColumn(name = "ID_PERSONALTRAINER", nullable = false))
    @Column(name = "TXT_NUM_TELEFONE", nullable = false, length = 20)
    private Collection<String> telefones;

    @Size(max = 100)
    @OneToMany(fetch = LAZY, cascade = PERSIST)
    @JoinColumn(name = "ID_PT", referencedColumnName = "ID_USUARIO")
    private List<Aluno> alunos;

    public Collection<String> getTelefones() {
        return telefones;
    }

    public void addTelefone(String telefone) {
        if (this.telefones == null) {
            this.telefones = new HashSet<>();
        }
        telefones.add(telefone);
    }

    public void addAluno(Aluno aluno) {
        if (this.alunos == null) {
            this.alunos = new ArrayList<>();
        }
        alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        if (aluno == null) {
            return;
        }
        this.alunos.remove(aluno);
    }

    public void setTelefones(Collection<String> telefones) {
        for (String telefone : telefones) {
            this.telefones.add(telefone);
        }
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    @Override
    public String toString() {
        return "com.mycompany.personaltech.Exercicio[ id=" + id + " ]";
    }

}
