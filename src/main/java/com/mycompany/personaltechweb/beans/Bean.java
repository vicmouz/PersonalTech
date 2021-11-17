package com.mycompany.personaltechweb.beans;

import com.mycompany.personaltechweb.entities.Usuario;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class Bean<T extends Usuario> {

    protected T entidade;

    protected abstract boolean salvar(T entidade);

    protected abstract boolean atualizar(T entidade);

    protected abstract boolean deletar(T entidade);

    @PostConstruct
    public void init() {
        iniciarCampos();
    }

    protected abstract void iniciarCampos();

    public T getEntidade() {
        return entidade;
    }

    public void setEntidade(T entidade) {
        this.entidade = entidade;
    }

    public String salvar() {
        try {
            boolean sucesso = salvar(entidade);
            if (sucesso && entidade.toString().equalsIgnoreCase("Personal")) {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!");
                return "listadeProfessor?faces-redirect=true";
            } else {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Cadastro realizado com sucesso!");
                return "listadeAlunos?faces-redirect=true";
            }
        } catch (Exception ex) {
            //adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
            adicionarMessagem(FacesMessage.SEVERITY_WARN, "Ocorreu um erro!");
        } finally {
            iniciarCampos();
        }
        return "index.xhtml";
    }

    public String atualizar() {
        try {
            boolean sucesso = atualizar(entidade);
            if (sucesso && entidade.toString().equalsIgnoreCase("Personal")) {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alteração Realizada com sucesso!");
                return "listadeProfessor?faces-redirect=true";
            } else {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Alteração Realizada com sucesso!");
                return "listadeAlunos?faces-redirect=true";
            }
        } catch (Exception ex) {
            //adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
            adicionarMessagem(FacesMessage.SEVERITY_WARN, "Ocorreu um erro!");
            return "index.xhtml";
        }

    }

    public String deletar() {
        try {
            boolean sucesso = deletar(entidade);
            if (sucesso) {
                adicionarMessagem(FacesMessage.SEVERITY_INFO, "Exclusão realizada com sucesso!");
            }

        } catch (Exception ex) {
            //adicionarMessagem(FacesMessage.SEVERITY_WARN, ex.getMessage());
            adicionarMessagem(FacesMessage.SEVERITY_WARN, "Ocorreu um erro!");
        }
        return "listadeAlunos?faces-redirect=true";
    }

    protected void adicionarMessagem(FacesMessage.Severity severity, String mensagem) {
        FacesMessage message = new FacesMessage(severity, mensagem, null);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
