/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.Exercicio;
import static com.mycompany.personaltechweb.entities.Exercicio.EXERCICIO;
import static com.mycompany.personaltechweb.entities.Exercicio.EXERCICIOSEALUNO;
import com.mycompany.personaltechweb.services.ExercicioServico;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author T-Gamer
 */
@RequestScoped
@Named("ExercicioBean")

public class ExercicioBean extends ExercicioServico implements Serializable {
    
    ExercicioServico exercicioServico ;
    private List<Exercicio> listaExercicioMock;
    
 protected void iniciarCampos() {
        exercicioServico.init();
 }
 
 public List<Exercicio> getExercicios(){
     
     return super.getEntidades(EXERCICIO);
 }
public List<Exercicio> getExerciciosSemAluno(){
        return super.getEntidades(EXERCICIOSEALUNO);
    } 


    @Override
    public Exercicio criar() {
return new Exercicio();    }

    public ExercicioServico getExercicioServico() {
        return exercicioServico;
    }

    public void setExercicioServico(ExercicioServico exercicioServico) {
        this.exercicioServico = exercicioServico;
    }

    public List<Exercicio> getListaExercicioMock() {
        return listaExercicioMock;
    }

    public void setListaExercicioMock(List<Exercicio> listaExercicioMock) {
        this.listaExercicioMock = listaExercicioMock;
    }

  
     
    

   
 
    
    
}
