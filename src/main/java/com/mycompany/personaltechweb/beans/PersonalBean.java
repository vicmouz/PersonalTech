/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.beans;


import com.mycompany.personaltechweb.entities.Aluno;
import com.mycompany.personaltechweb.entities.PersonalTrainer;
import com.mycompany.personaltechweb.services.PersonalTrainerServico;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author T-Gamer
 */
@RequestScoped
@Named("PersonalBean")
public class PersonalBean extends Bean<PersonalTrainer> implements Serializable {

 @Inject
    private PersonalTrainerServico personalServico;
    private PersonalTrainer personalLogado;
    private List<PersonalTrainer> personals;
    private List<Aluno> personalsAluno;
    private String usuario = "";
    private String senha = "";
    
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
        return "alterarProfessor";       
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
    public String Login (PersonalTrainer entidade){
        if (personals == null) {
            personals = personalServico.ConsultarPorLogin(entidade.getLogin(), entidade.getSenha());
        }
        if(personals != null){
            return "indexPersonal";
        }
        else return "index";
}
       public List<Aluno> getAlunosPorPersonal(){
        personalsAluno = entidade.getAlunos();
        return personalsAluno;
       }
       public String efetuaLogin(PersonalTrainer entidade) {
         
        System.out.println(entidade);
        if (personalServico.existe(entidade)== true) {
           return "indexProfessor?faces-redirect=true";
            
                  
        }

        return null;
    }
       public boolean LinkarAlunoPersonal(Aluno aluno) {  
        if (personals == null) {
            personals = personalServico.getPersonals();
            System.out.println("PERSONALS::::::::::::" +personals);
        }  
         System.out.println("ALUNO::::::::::::" +aluno);
        personalLogado = personals.get(0);
        System.out.println("NOME >>>>>>>>>>>>>>>>>>>>>>>>>>>" +personalLogado.getNome());
        System.out.println("ID >>>>>>>>>>>>>>>>>>>>>>>>>>>" +personalLogado.getId());
        personalLogado.addAluno(aluno);
        
        personalServico.atualizar(personalLogado);
        return true;
    }
}
