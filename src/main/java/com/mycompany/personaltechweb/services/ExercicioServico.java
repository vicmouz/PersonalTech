/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.services;

import com.mycompany.personaltechweb.entities.Exercicio;
import static com.mycompany.personaltechweb.entities.Exercicio.EXERCICIO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static javax.persistence.PersistenceContextType.TRANSACTION;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import static javax.validation.executable.ExecutableType.ALL;
import javax.validation.executable.ValidateOnExecution;

/**
 *
 * @author T-Gamer
 */
@Stateless(name = "ejb/ExercicioServico")
@LocalBean
@ValidateOnExecution(type = ALL)
public abstract class ExercicioServico <T extends Exercicio>{
    
    @PersistenceContext(name = "PersonalTechWebPU", type = TRANSACTION)
    protected EntityManager entityManager;
    protected Class<T> classe;

    @TransactionAttribute(NOT_SUPPORTED)
    protected void setClasse(@NotNull Class<T> classe) {
        this.classe = classe;
    }

    @TransactionAttribute(SUPPORTS)
    public abstract T criar();

    @TransactionAttribute(SUPPORTS)
    public boolean existe(@NotNull T entidade) {
        return true;
    }

    public void persistir(@Valid T entidade) {      
            entityManager.persist(entidade);       
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

    @TransactionAttribute(SUPPORTS)
    public T consultarPorId(@NotNull Long id) {
        return entityManager.find(classe, id);
    }

    @TransactionAttribute(SUPPORTS)
    protected T consultarEntidade(@NotNull Object[] parametros, String nomeQuery) {
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

    @TransactionAttribute(SUPPORTS)
    protected List<T> getEntidades(String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);
        return query.getResultList();
    }
    
    @PostConstruct
    public void init() {
       setClasse((Class<T>) Exercicio.class);
    }
}
