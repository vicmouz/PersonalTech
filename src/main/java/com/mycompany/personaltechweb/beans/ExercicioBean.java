/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.Exercicio;
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

public class ExercicioBean  implements Serializable {
    private ExercicioServico exercicioServico;
 
    private List<Exercicio> listaExercicioMock;
    
    
    

    public ExercicioServico getExercicioServico() {
        return exercicioServico;
    }

    public void setExercicioServico(ExercicioServico exercicioServico) {
        this.exercicioServico = exercicioServico;
    }

    public List<Exercicio> getListaExercicioMock() {
        listaExercicioMock = null;
        listaExercicioMock = exercicioServico.getExercicio();
        System.out.print(listaExercicioMock);
        return listaExercicioMock;
    }

    public void setListaExercicioMock(List<Exercicio> listaExercicioMock) {
        this.listaExercicioMock = listaExercicioMock;
    }

 
    
    
}
