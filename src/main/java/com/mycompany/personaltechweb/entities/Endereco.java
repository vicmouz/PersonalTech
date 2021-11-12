package com.mycompany.personaltechweb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MarcosBrasileiro
 */
@Embeddable
public class Endereco implements Serializable {



    
    
    @NotNull
    @Size(max = 150)
    @Column(name = "END_TXT_LOGRADOURO", length = 150, nullable = false)
    private String logradouro;
    
    @NotNull
    @Size(max = 150)
    @Column(name = "END_TXT_BAIRRO", length = 150, nullable = false)
    private String bairro;
    
    @NotNull
    @Min(1)
    @Max(99999)
    @Column(name = "END_NUMERO", length = 5, nullable = false)
    private Integer numero;

    @NotNull
    @Column(name = "END_TXT_COMPLEMENTO", length = 30, nullable = true)
    @Size(max = 30)
    private String complemento;

    @NotNull
    @Size(max = 20)
    @Column(name = "END_TXT_CEP", length = 20, nullable = false)
    private String cep;
    
    @NotNull
    @Size(max = 50)
    @Column(name = "END_TXT_CIDADE", length = 50, nullable = false)
    private String cidade;
    
    @Size(min = 2, max = 2)
    @Column(name = "END_TXT_ESTADO", length = 2, nullable = false)
    private String estado;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
