package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.Aluno;
import com.mycompany.personaltechweb.entities.Exercicio;
import com.mycompany.personaltechweb.entities.NomeExercicio;
import com.mycompany.personaltechweb.entities.TipoExercicio;
import com.mycompany.personaltechweb.services.AlunoServico;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.inject.Inject;

@RequestScoped
@Named("AlunoBean")
public class AlunoBean extends Bean<Aluno> implements Serializable {
    private static String usuario = "";
    private static String senha = "";
    @Inject
    private AlunoServico alunoServico;
    private AlunoBean alunoBean;
    private List<Aluno> alunos;
    private List<Aluno> alunosSemPersonal;
    private static List<Exercicio> listaExercicioMock;
    
    @Override
    protected void iniciarCampos() {
        setEntidade(alunoServico.criar());       
    }
    
    @Override
    protected boolean salvar(Aluno entidade) {
        System.out.println("com.mycompany.personaltechweb.beans.AlunoBean.salvar()");
        System.out.println(entidade);
        alunoServico.persistir(entidade);
        return true;
    }

    @Override
    public boolean atualizar(Aluno entidade) {       
        alunoServico.atualizar(entidade);
        return true;
    }
    
    public String editar(Aluno aluno) {        
        entidade = alunoServico.consultarPorId(aluno.getId());        
        return "alterarAluno";       
    }
    
    @Override
    public boolean deletar(Aluno entidade) {      
        alunoServico.deletar(entidade);
        return true;
    }
    
    // Metodo gambiarra p tela atualizar
    public String remover(Aluno entidade) {                
        alunoServico.deletar(entidade);
        return "listadeAlunos?faces-redirect=true";
    }

    public List<Aluno> getAlunos() {
        alunos = null;
        if (alunos == null) {
            alunos = alunoServico.getAlunos();
        }
        return alunos;
    }
    public String efetuaLogin() {
        System.out.println("USUARIO  :->" +usuario);
        System.out.println("SENHA  :->" +senha);
        System.out.println("CONSULTA  :->" +alunoServico.ConsultarPorLogin(usuario));
        
        if(alunoServico.ConsultarPorLogin(usuario) !=null){
            return "indexAluno?faces-redirect=true";
        }
            return "index?faces-redirect=true";
    }
    public List<Aluno> getAlunosSemPersonal(){
        alunos = null;
        alunos = alunoServico.consultarAlunoSemProfessor(Long.MIN_VALUE);
        System.out.print(alunos);
        return alunos;
    }  
    
    public void buscarCep(String cep) {
        System.out.print('1');
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

    public List<Exercicio> getListaExercicioMock() {
        return listaExercicioMock;
    }

    public void setListaExercicioMock(List<Exercicio> listaExercicioMock) {
        this.listaExercicioMock = listaExercicioMock;
    }

    
   
    
 
    
    
}
