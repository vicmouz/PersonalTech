/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.PersonalTrainer;
import com.mycompany.personaltechweb.services.PersonalTrainerServico;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.persistence.TypedQuery;

@ManagedBean
@SessionScoped
public class LoginBean {
    private String usuario = "";
    private String senha = "";
    private PersonalTrainerServico pts = new PersonalTrainerServico();
    private PersonalBean pb = new PersonalBean();
    private List<PersonalTrainer> personals;
    
    
     public String logar(){
         System.out.println("PERSONAL TRAINER AQUI LOGANDO LOGIN::::::::::::" +usuario);
         System.out.println("PERSONAL TRAINER AQUI LOGANDO SENHA::::::::::::" +senha);
         personals = pb.getPersonals();
         System.out.println("TESTE DO AMIGO :::::::::::::::::::" +personals);
        if(personals.size()>0){
            return "indexProfessor?faces-redirect=true";
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário inválido", "Usuário inválido");
        ctx.addMessage(null, msg);
        return "";              
    }
    
    
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
  
}
