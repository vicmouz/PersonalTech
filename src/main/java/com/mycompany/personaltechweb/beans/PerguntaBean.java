/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.Pergunta;
import static com.mycompany.personaltechweb.entities.Pergunta.PERGUNTA;
import com.mycompany.personaltechweb.entities.RespostasAvaliacao;
import com.mycompany.personaltechweb.services.PerguntaServico;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *
 * @author T-Gamer
 */
@RequestScoped
@Named("PerguntaBean")
public class PerguntaBean extends PerguntaServico implements Serializable {
  
    private PerguntaServico perguntaServico ;
     private List<Pergunta> listaPergunta;
     private RespostasAvaliacao respostas;
     private String resposta;
    @Override
    public void criar() {
    perguntaServico.init();
    }
    public List<Pergunta> getPerguntas(){
     
     return super.getEntidades(PERGUNTA);
 }

    public PerguntaServico getPerguntaServico() {
        return perguntaServico;
    }

    public void setPerguntaServico(PerguntaServico perguntaServico) {
        this.perguntaServico = perguntaServico;
    }

    public List<Pergunta> getListaPergunta() {
        return listaPergunta;
    }

    public void setListaPergunta(List<Pergunta> listaPergunta) {
        this.listaPergunta = listaPergunta;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }

    public RespostasAvaliacao getRespostas() {
        return respostas;
    }

    public void setRespostas(RespostasAvaliacao respostas) {
        this.respostas = respostas;
    }
    
    
    public void salvarResposta( Long idPergunta, RespostasAvaliacao r){
        System.out.println("ID PERGUNTA : " +idPergunta);
        System.out.println("Respostas : " +r.getTxt_resposta());
        
          try {
            Connection con = DriverManager.getConnection(
                     "jdbc:derby://localhost:1527/personaltechv1", "root", "root");
            Statement stmt = con.createStatement();
            
            stmt.execute("INSERT INTO TB_AV_PERG (TXT_RESP_ALUNO,ID_PERG,ID_AV) VALUES ('"  + r.getTxt_resposta()      +"','" +idPergunta   + "','1')");
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
       // return "indexProfessor?faces-redirect=true";
    }  

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
}
