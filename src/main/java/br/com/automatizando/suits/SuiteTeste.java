package br.com.automatizando.suits;

import br.com.automatizando.core.DriverFactory;
import br.com.automatizando.test.TesteCadastro;
import br.com.automatizando.test.TesteCampoDeTreinamento;
import br.com.automatizando.test.TesteRegrasCadastro;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCadastro.class,
        TesteRegrasCadastro.class
})
public class SuiteTeste {
    @AfterClass
    public static void finalizaTudo(){
        DriverFactory.killDrive();
    }

}
