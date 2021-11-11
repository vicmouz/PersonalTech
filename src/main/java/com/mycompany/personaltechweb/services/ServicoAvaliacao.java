/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.services;

import com.mycompany.personaltechweb.entities.Aluno;
import com.mycompany.personaltechweb.entities.Avaliacao;
import static com.mycompany.personaltechweb.entities.Avaliacao.AVALIACAO;

import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionManagementType.CONTAINER;
import static javax.persistence.PersistenceContextType.TRANSACTION;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ExecutableType;
import static javax.validation.executable.ExecutableType.ALL;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author MarcosBrasileiro
 */
@Stateless(name = "ejb/ServicoAvaliacao")
@LocalBean
@ValidateOnExecution(type = ALL)
@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED)
public class ServicoAvaliacao<T extends Avaliacao> {

    
    @PersistenceContext(name = "PersonalTechWebPU", type = TRANSACTION)
    protected EntityManager entityManager;
    protected Class<T> classe;
    
    @TransactionAttribute(NOT_SUPPORTED)
    protected void setClasse(@NotNull Class<T> classe) {
        this.classe = classe;
    }
    
    @PostConstruct
    public void init() {
        setClasse((Class<T>) Avaliacao.class);
    }
    
    public Avaliacao criar() {
        return new Avaliacao();
    }

    @TransactionAttribute(SUPPORTS)
    public boolean existe(@NotNull T avaliacao) {
        TypedQuery<Avaliacao> query
                = (TypedQuery<Avaliacao>) entityManager.createNamedQuery(AVALIACAO, classe);
        query.setParameter(1, avaliacao.getNome_personal());
        return !query.getResultList().isEmpty();
    }
    
   @TransactionAttribute(SUPPORTS)
    public List<Avaliacao> avaliacoesPorPersonal(@NotNull String personal) {
        TypedQuery<Avaliacao> query
                = (TypedQuery<Avaliacao>) entityManager.createNamedQuery(AVALIACAO, classe);
        query.setParameter(1, personal);
        return query.getResultList();
    }

    public void persistir(@Valid T entidade) {
        if (!existe(entidade)) {
            entityManager.persist(entidade);
        }
    }

    public void atualizar(@Valid T entidade) {
        if (existe(entidade)) {
            entityManager.merge(entidade);
            entityManager.flush();
        }
    }

    public void deletar(@Valid T entidade) {
        if (existe(entidade)) {
            T ems = entityManager.merge(entidade);
            entityManager.remove(ems);
            entityManager.flush();
        }

    }

    
    public T consultarPorId(@NotNull Long id) {
        return entityManager.find(classe, id);
    }

    @TransactionAttribute(SUPPORTS)
    protected T consultarEntidade(Object[] parametros, String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);

        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }

        return query.getSingleResult();
    }

    @TransactionAttribute(SUPPORTS)
    protected List<T> consultarEntidades(Object[] parametros, String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);

        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }

        return query.getResultList();
    }
}
