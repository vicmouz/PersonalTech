/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.beans;


import com.mycompany.personaltechweb.entities.PersonalTrainer;
import com.mycompany.personaltechweb.services.PersonalTrainerServico;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author T-Gamer
 */
@RequestScoped
@Named("PersonalBean")
public class PersonalBean extends Bean<PersonalTrainer> implements Serializable {

 @Inject
    private PersonalTrainerServico personalServico;

    private List<PersonalTrainer> personals;

    @Override
    protected void iniciarCampos() {
        setEntidade(personalServico.criar());       
    }
    
    @Override
    protected boolean salvar(PersonalTrainer entidade) {
        personalServico.persistir(entidade);
        return true;
    }

    @Override
    public boolean atualizar(PersonalTrainer entidade) {       
        personalServico.atualizar(entidade);
        return true;
    }
    
    public String editar(PersonalTrainer personal) {        
        entidade = personalServico.consultarPorId(personal.getId());        
        return "alterarPersonal";       
    }
    
    @Override
    public boolean deletar(PersonalTrainer entidade) {      
        personalServico.deletar(entidade);
        return true;
    }
    
    // Metodo gambiarra p tela atualizar
    public String remover(PersonalTrainer entidade) {                
        personalServico.deletar(entidade);
        return "listadeProfessor?faces-redirect=true";
    }

    public List<PersonalTrainer> getPersonals() {
        if (personals == null) {
            personals = personalServico.getPersonals();
        }
        return personals;
    }
    public Boolean Login (PersonalTrainer entidade){
        if (personals == null) {
            personals = personalServico.consultarPorLogin(entidade.getLogin(), entidade.getSenha());
        }
        if(personals != null){
            return true;
        }
        else return false;
}}
