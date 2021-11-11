/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.services;

import com.mycompany.personaltechweb.entities.Aluno;
import static com.mycompany.personaltechweb.entities.Aluno.ALUNOS;
import static com.mycompany.personaltechweb.entities.Aluno.ALUNOS_POR_SEXO;
import static com.mycompany.personaltechweb.entities.Aluno.ALUNO_POR_CPF;
import com.mycompany.personaltechweb.entities.Endereco;
import com.mycompany.personaltechweb.entities.TipoExercicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import static javax.validation.executable.ExecutableType.ALL;
import javax.validation.executable.ValidateOnExecution;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author MarcosBrasileiro
 */
@Stateless(name = "ejb/AlunoServico")
@LocalBean
@ValidateOnExecution(type = ALL)
public class AlunoServico extends ServicoUsuario<Aluno> {

    @PostConstruct
    public void init() {
        super.setClasse(Aluno.class);
        
    }

    @Override
    public Aluno criar() {
        
        return new Aluno();
    }
    

    @Override
    public boolean existe(@NotNull Aluno usuario) {
        TypedQuery<Aluno> query
                = entityManager.createNamedQuery(ALUNO_POR_CPF, classe);
        query.setParameter(1, usuario.getCpf());
        return !query.getResultList().isEmpty();
    }

    @TransactionAttribute(SUPPORTS)
    public Aluno consultarPorCPF(@CPF String cpf) {
        return super.consultarEntidade(new Object[]{cpf}, ALUNO_POR_CPF);
    }

    @TransactionAttribute(SUPPORTS)
    public Object consultarPorNome(String nome) {
        return super.consultarEntidades(new Object[]{nome}, "Aluno.PorNome");
    }

    @TransactionAttribute(SUPPORTS)
    public Object consultarPorTipoExercicio(TipoExercicio tipoExercicio) {
        return super.consultarEntidades(new Object[]{tipoExercicio}, "Aluno.PorTipoDeExercicio");
    }

    @TransactionAttribute(SUPPORTS)
    public List<Aluno> consultarPorSexo(@NotBlank String sexo) {
        return super.consultarEntidades(new Object[]{sexo}, ALUNOS_POR_SEXO);
    }

    @TransactionAttribute(SUPPORTS)
    public List<Aluno> getAlunos() {
        return getEntidades(ALUNOS);
    }
    
}
