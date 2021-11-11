/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb.services;

import com.mycompany.personaltechweb.entities.Aluno;
import com.mycompany.personaltechweb.entities.PersonalTrainer;
import static com.mycompany.personaltechweb.entities.PersonalTrainer.QUANTIDADE_PERSONAL_TRAINER;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import javax.validation.executable.ExecutableType;
import static javax.validation.executable.ExecutableType.ALL;
import javax.validation.executable.ValidateOnExecution;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author MarcosBrasileiro
 */
@Stateless(name = "ejb/PersonalTrainerServico")
@LocalBean
@ValidateOnExecution(type = ALL)
public class PersonalTrainerServico extends ServicoUsuario<PersonalTrainer> {

    @PostConstruct
    public void init() {
        super.setClasse(PersonalTrainer.class);
    }

    @Override
    public PersonalTrainer criar() {
        return new PersonalTrainer();
    }

    @TransactionAttribute(SUPPORTS)
    public List<PersonalTrainer> quantidadePersonalTrainer() {
        return super.consultarEntidades(new Object[]{}, QUANTIDADE_PERSONAL_TRAINER);
    }
}
