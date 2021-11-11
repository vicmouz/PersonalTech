package com.mycompany.personaltechweb.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import javax.persistence.CascadeType;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.FetchType.LAZY;
import javax.persistence.JoinColumn;
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
@Table(name = "TB_ALUNO")
@DiscriminatorValue(value = "A")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Aluno.PorNome",
                    query = "SELECT a FROM Aluno a WHERE a.nome LIKE ?1 ORDER BY a.id"
            ),
                @NamedQuery(
                    name = "Aluno.PorTipoDeExercicio",
                    query = "SELECT DISTINCT a FROM Aluno a JOIN a.exercicios xs WHERE xs.tipo = ?1"
            ),
            @NamedQuery(
                    name = Aluno.ALUNO_POR_CPF,
                    query = "SELECT a FROM Aluno a WHERE a.cpf IS NOT NULL AND a.cpf LIKE ?1"
            ),
            @NamedQuery(
                    name = Aluno.ALUNOS_POR_SEXO,
                    query = "SELECT a FROM Aluno a WHERE a.sexo LIKE ?1"
            ),
            @NamedQuery(
                    name = Aluno.ALUNOS,
                    query = "SELECT a FROM Aluno a ORDER BY a.nome"
            )
        }
)
public class Aluno extends Usuario implements Serializable {
public static final String ALUNO_POR_CPF = "AlunoPorCPF";
public static final String ALUNOS_POR_SEXO = "AlunoPorSexo";
public static final String ALUNOS = "Alunos";

    @Size(max = 5)
    @ElementCollection
    @CollectionTable(name = "TB_TELEFONE_ALUNO",
            joinColumns = @JoinColumn(name = "ID_ALUNO", nullable = false))
    @Column(name = "TXT_NUM_TELEFONE", nullable = false, length = 20)
    private Collection<String> telefones;
    
    @Size(max = 10)
    @OneToMany(fetch = LAZY,
            cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "ID_ALUNO", referencedColumnName = "ID_USUARIO")
    private List<Exercicio> exercicios;
    
    @OneToMany(mappedBy = "aluno", fetch = LAZY,
            cascade = ALL)
    private List<Avaliacao> avaliacoes;

    public List<Exercicio> getExercicios() {
        return exercicios;
    }

    public void addExercicio(Exercicio exercicio) {
        if (this.exercicios == null) {
            this.exercicios = new ArrayList<>();
        }
        this.exercicios.add(exercicio);
    }

    public void removeExercicio(Exercicio exercicio) {
        if (this.exercicios == null) {
            return;
        }
        this.exercicios.remove(exercicio);
    }

    public Collection<String> getTelefones() {
        return telefones;
    }

    public void addTelefone(String telefone) {
        if (this.telefones == null) {
            this.telefones = new HashSet<>();
        }
        telefones.add(telefone);
    }

    public void setTelefones(Collection<String> telefones) {
        for (String telefone : telefones) {
            this.telefones.add(telefone);
        }
    }

    public void setExercicios(List<Exercicio> exercicios) {
        // corrigido
        for (Exercicio exercicio : exercicios) {
            this.exercicios.add(exercicio);
        }
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        // corrigido
        for (Avaliacao avaliacao : avaliacoes) {
            addAvaliacao(avaliacao);
        }
    }

    public void addAvaliacao(Avaliacao avaliacao) {
        if (this.avaliacoes == null) {
            this.avaliacoes = new ArrayList<>();
        }
        this.avaliacoes.add(avaliacao);
        avaliacao.setAluno(this);
    }

    public void removeAvaliacao(Avaliacao avaliacao) {
        if (this.avaliacoes == null) {
            return;
        }
        this.avaliacoes.remove(avaliacao);
    }

    @Override
    public String toString() {
        return "com.mycompany.personaltech.Aluno[ id=" + id + " ]";
    }

}
