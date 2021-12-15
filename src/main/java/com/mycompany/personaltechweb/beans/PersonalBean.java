/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.Aluno;
import com.mycompany.personaltechweb.entities.PersonalTrainer;
import com.mycompany.personaltechweb.services.AlunoServico;
import com.mycompany.personaltechweb.services.PersonalTrainerServico;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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

    private static String usuario = "";
    private static String senha = "";
    @Inject
    private PersonalTrainerServico personalServico;
    private PersonalTrainer personalLogado;
    private List<PersonalTrainer> personals;
    private List<Aluno> personalsAluno;
    private Aluno novoAluno;
    private AlunoServico as;
    public Aluno getNovoAluno() {
        return as.getAlunos().get(0);
    }

    public void setNovoAluno(Aluno novoAluno) {
        this.novoAluno = novoAluno;
    }

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

    /* public String Login (PersonalTrainer entidade){
        if (personals == null) {
            personals = personalServico.ConsultarPorLogin(entidade.getLogin(), entidade.getSenha());
        }
        if(personals != null){
            return "indexPersonal";
        }
        else return "index";
}*/
    public List<Aluno> getAlunosPorPersonal() {
        personalsAluno = entidade.getAlunos();
        return personalsAluno;
    }

    public String efetuaLogin() {
        System.out.println("USUARIO  :->" + usuario);
        System.out.println("SENHA  :->" + senha);
        System.out.println("CONSULTA  :->" + personalServico.ConsultarPorLogin(usuario));
        if (personalServico.ConsultarPorLogin(usuario) != null) {
            return "indexProfessor?faces-redirect=true";
        }
        return "index?faces-redirect=true";
    }

    public void linkarAlunoPersonal(Aluno aluno) {
        PersonalTrainer personalLinkar;
        System.out.println("ALUNO : "+aluno);
        System.out.println("USUARIO  :->" + usuario);
        System.out.println("SENHA  :->" + senha);
        System.out.println("CONSULTA  :->" + personalServico.ConsultarPorLogin(usuario));
        personalLinkar = personalServico.ConsultarPorLogin(usuario).get(0);
        System.out.println("Nome PERSONAL  :->" + personalLinkar.getNome());
        System.out.println("VAI ADICIONAR AGORA");
         System.out.println("ALUNO NOME: "+aluno.getNome());
        System.out.println("__________________________________________________________________");
        personalLinkar.addAluno(aluno);
        System.out.println("ADD ALUNO NO PERSONAL  :->" + personalLinkar.getAlunos());
        personalServico.atualizar(personalLinkar);
        System.out.println("FIM DA ATUALIZAÇÃO");
        System.out.println("__________________________________________________________________");
        
        
         
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
