/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb;

import com.mycompany.personaltechweb.entities.Aluno;
import com.mycompany.personaltechweb.entities.TipoExercicio;
import static com.mycompany.personaltechweb.entities.TipoExercicio.BICEPS;
import com.mycompany.personaltechweb.services.AlunoServico;
import java.util.List;
import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author john
 */
public class AlunoTest extends Teste {

    private AlunoServico alunoServico;

    @Before
    public void setUp() throws NamingException {
        alunoServico = (AlunoServico) container.getContext().lookup("java:global/classes/ejb/AlunoServico!com.mycompany.personaltechweb.services.AlunoServico");
    }

    @After
    public void tearDown() {
        alunoServico = null;
    }

    @Test
    public void existeAluno() {
        Aluno aluno = alunoServico.criar();
        aluno.setCpf("382.487.724-46");
        assertTrue(alunoServico.existe(aluno));
    }

    @Test
    public void getAlunoPorCPF() {
        assertEquals(alunoServico.consultarPorCPF("382.487.724-46").getNome(), "JIMMY");
    }

    @Test
    public void getAlunosPorNome() {
        String nome = "JOAO";
        List<Aluno> alunos = (List<Aluno>) alunoServico.consultarPorNome(nome.toUpperCase());
        assertEquals(alunos.size(), 1);
    }

    @Test
    public void getAlunoPorID() {
        assertNotNull(alunoServico.consultarPorId(new Long(20)));
    }

    @Test
    public void getAlunosPorTipoDeExercicio() {
        TipoExercicio tipoExercicio = BICEPS;
        List<Aluno> alunos = (List<Aluno>) alunoServico.consultarPorTipoExercicio(tipoExercicio);
        assertEquals(alunos.size(), 6);
    }

    @Test
    public void atualizarAlunoPorCpf() {
        Aluno aluno = alunoServico.criar();
        aluno = alunoServico.consultarPorCPF("188.070.374-24");
        aluno.setNome("ZULEICA");
        alunoServico.atualizar(aluno);
        aluno = alunoServico.consultarPorCPF("188.070.374-24");
        assertEquals("ZULEICA", aluno.getNome());
    }

    @Test
    public void deletarAlunoPorId() {
        Aluno aluno = alunoServico.criar();
        aluno = alunoServico.consultarPorId((long) 19);
        alunoServico.deletar(aluno);
        assertNull(alunoServico.consultarPorId((long) 19));
    }

    @Test
    public void mensagensValidacao() {
        Aluno aluno = alunoServico.criar();
        aluno = alunoServico.consultarPorCPF("188.070.374-24");
        aluno.setSexo("L");
        aluno.setSenha("SENHAINVALIDA");
        aluno.setLogin("LOGININVALIDO");
        try {
            alunoServico.atualizar(aluno);
            assertTrue(false);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        anyOf(startsWith("Sexo inválido"),
                                startsWith("Login inválido"), startsWith("Senha inválida")));
            }
        }
    }

    @Test
    public void consultarAlunoCPFInvalido() {
        try {
            alunoServico.consultarPorCPF("111.111.111-11");
            assertTrue(false);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
        }
    }

    @Test
    public void getAlunosMulheres() {
        List<Aluno> alunos = alunoServico.consultarPorSexo("F");
        assertEquals(alunos.size(), 7);
    }

    @Test
    public void alunos() {
        List<Aluno> alunos = alunoServico.getAlunos();
        assertEquals(30, alunos.size());
    }
}
