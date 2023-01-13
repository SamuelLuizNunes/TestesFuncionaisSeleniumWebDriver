package br.com.automatizando;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCadastro.class,
        TesteRegrasCadastro.class,
        TesteCampoDeTreinamento.class
})
public class SuiteTeste {
}
