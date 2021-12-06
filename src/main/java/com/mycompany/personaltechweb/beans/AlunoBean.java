package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.Aluno;
import com.mycompany.personaltechweb.services.AlunoServico;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;

@RequestScoped
@Named("AlunoBean")
public class AlunoBean extends Bean<Aluno> implements Serializable {

    @Inject
    private AlunoServico alunoServico;
    private AlunoBean alunoBean;
    private List<Aluno> alunos;
    private List<Aluno> alunosSemPersonal;
    @Override
    protected void iniciarCampos() {
        setEntidade(alunoServico.criar());       
    }
    
    @Override
    protected boolean salvar(Aluno entidade) {
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
    public String efetuaLogin(Aluno entidade) {

        boolean existe = alunoServico.existe(entidade);

        if (existe) {
            return "indexAluno?faces-redirect=true";
        }

        return null;
    }
    public List<Aluno> getAlunosSemPersonal(){
        alunos = null;
        alunos = alunoServico.consultarAlunoSemProfessor(Long.MIN_VALUE);
        System.out.print(alunos);
        return alunos;
    }  
}
