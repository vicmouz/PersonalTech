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
    private PersonalTrainerServico pts;
   
    
    
          
    
    
    
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
  public final class FacesManager {

    public void putObjectIntoSessionMap(final String key, final Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put(key, value);
    }
}}