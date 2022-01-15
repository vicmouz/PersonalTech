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
import com.mycompany.personaltechweb.services.AlunoServico;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author T-Gamer
 */
@RequestScoped  
@Named("PersonalBean")
public class PersonalBean extends Bean<PersonalTrainer> implements Serializable {

    private static String usuario = "";
    private static String senha = "";
    private String novoAluno = "";

    @Inject
    private PersonalTrainerServico personalServico;
    private AlunoServico alunoServico;
    private PersonalTrainer personalLogado;
    private List<PersonalTrainer> personals;
    private List<Aluno> personalsAluno;

    public String getNovoAluno() {
        return novoAluno;
    }

    public void setNovoAluno(String novoAluno) {
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

    public void linkarAlunoPersonal() {
        System.out.println("USUARIO  :->" + novoAluno);
        try {
            Connection con = DriverManager.getConnection(
                     "jdbc:derby://localhost:1527/personaltechv1", "root", "root");
            Statement stmt = con.createStatement();
            stmt.execute("UPDATE TB_ALUNO SET ID_PT=2 WHERE ID_PT IS NULL");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
//        PersonalTrainer personalLinkar;
//        System.out.println("ALUNO : "+aluno);
//        System.out.println("USUARIO  :->" + usuario);
//        System.out.println("SENHA  :->" + senha);
//        System.out.println("CONSULTA  :->" + personalServico.ConsultarPorLogin(usuario));
//        personalLinkar = personalServico.ConsultarPorLogin(usuario).get(0);
//        System.out.println("Nome PERSONAL  :->" + personalLinkar.getNome());
//        System.out.println("VAI ADICIONAR AGORA");
//         System.out.println("ALUNO NOME: "+aluno.getNome());
//        System.out.println("__________________________________________________________________");
//        personalLinkar.addAluno(aluno);
//        System.out.println("ADD ALUNO NO PERSONAL  :->" + personalLinkar.getAlunos());
//        personalServico.atualizar(personalLinkar);
//        System.out.println("FIM DA ATUALIZAÇÃO");
//        System.out.println("__________________________________________________________________");

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
    
    public String redirectHome(){
    return "indexProfessor?faces-redirect=true";
}
   public String redirectAvaliacao(){
             
        try {
            Connection con = DriverManager.getConnection(
                     "jdbc:derby://localhost:1527/personaltechv1", "root", "root");
            Statement stmt = con.createStatement();
            
            stmt.execute("INSERT INTO TB_PERGUNTA (TXT_PERGUNTA) VALUES ('Alguma vez o médico lhe disse que você possui um problema de coração e lhe recomendou que só fizesse atividade física sob supervisão médica? '),('Tem dor no peito, causada por atividade física?'),('Independente de atividade física, você sentiu dor no peito no último mês?'),('Já perdeu a consiência em alguma ocasião ou sofreu alguma queda em virtude de tonteira?'),('Algum médico já lhe prescreveu medicamento para sua pressão arterial ou para o coração?')");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
       
        try {
            Connection con = DriverManager.getConnection(
                     "jdbc:derby://localhost:1527/personaltechv1", "root", "root");
            Statement stmt = con.createStatement();
            
            stmt.execute("INSERT INTO TB_AVALIACAO (DT_AVALIACAO,TXT_NOME_PT) VALUES ('2022-01-20','personal-teste')");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Connection con = DriverManager.getConnection(
                     "jdbc:derby://localhost:1527/personaltechv1", "root", "root");
            Statement stmt = con.createStatement();
            stmt.execute("UPDATE TB_AVALIACAO SET ID_ALUNO=1 WHERE ID_ALUNO IS NULL");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    return "AvaliarAluno?faces-redirect=true";
}
}
