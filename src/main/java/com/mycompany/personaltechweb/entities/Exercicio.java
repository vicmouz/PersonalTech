package com.mycompany.personaltechweb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MarcosBrasileiro
 */
@Entity
@Table(name = "TB_EXERCICIO")
@NamedNativeQueries(
        {
            @NamedNativeQuery(
                    name = "Exercicio.RetornaTipoExercicio",
                    query = "SELECT ex.ID, ex.TXT_NOME_EXERCICIO, ex.TXT_TIPO_EXERCICIO, a.TXT_NOME mapa FROM TB_EXERCICIO ex JOIN TB_USUARIO a ON ex.ID_ALUNO = a.ID WHERE ex.ID = ?1",
                    resultSetMapping = "mapping"
            ),
            @NamedNativeQuery(
                    name = Exercicio.EXERCICIOSEALUNO,
                    query = "SELECT * FROM ROOT.TB_EXERCICIO INNER JOIN ROOT.TB_ALUNO ON ROOT.TB_EXERCICIO.ID_ALUNO IS NULL",
                    resultSetMapping = "mapping"
            )
        }
)
@NamedQueries(
        {
            @NamedQuery(
                    name = Exercicio.EXERCICIO,
                    query = "SELECT a FROM Exercicio a ORDER BY a.exercicio"
            )})
@SqlResultSetMapping(
        name = "mapping",
        entities = {
            @EntityResult(entityClass = Exercicio.class)
        },
        columns = {
            @ColumnResult(name = "mapa", type = String.class)
        }
)
public class Exercicio implements Serializable {
 public static final String EXERCICIO = "Exercicio";
 public static final String EXERCICIOSEALUNO = "ExerciciosEAluno";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_TIPO_EXERCICIO", length = 20, nullable = false)
    private TipoExercicio tipo;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TXT_NOME_EXERCICIO", length = 50, nullable = false)
    private NomeExercicio exercicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoExercicio getTipo() {
        return tipo;
    }

    public void setTipo(TipoExercicio tipo) {
        this.tipo = tipo;
    }

    public NomeExercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(NomeExercicio exercicio) {
        this.exercicio = exercicio;
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
        if (!(object instanceof Exercicio)) {
            return false;
        }
        Exercicio other = (Exercicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.personaltech.Exercicio[ id=" + id + " ]";
    }
}
