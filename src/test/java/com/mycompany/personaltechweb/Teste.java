/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.personaltechweb;

import static com.mycompany.personaltechweb.DbUnitUtil.inserirDados;
import java.util.logging.Logger;
import static java.util.logging.Logger.getGlobal;
import javax.ejb.embeddable.EJBContainer;
import static javax.ejb.embeddable.EJBContainer.createEJBContainer;
//import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 *
 * @author marcos
 */
public class Teste {

    protected static EJBContainer container;
    protected final Logger logger = getGlobal();

    @BeforeClass
    public static void setUpClass() {
        container = createEJBContainer();
        inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }
}
