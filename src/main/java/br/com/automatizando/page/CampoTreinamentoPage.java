package br.com.automatizando.page;

import br.com.automatizando.core.BasePage;
import br.com.automatizando.core.DSL;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage extends BasePage {
    public void setNome(String nome){
        dsl.escreve("elementosForm:nome",nome);
    }

    public void setSobrenome(String sobrenome){
        dsl.escreve("elementosForm:sobrenome",sobrenome);
    }

    public void setSexoMasculino(){
        dsl.clickRadioButton("elementosForm:sexo:0");
    }
    public void setSexoFeminino(){
        dsl.clickRadioButton("elementosForm:sexo:1");
    }


    public void setComidaPizza(){
        dsl.clickCheckBox("elementosForm:comidaFavorita:2");
    }

    public void setComidaCarne(){
        dsl.clickCheckBox("elementosForm:comidaFavorita:0");
    }

    public void setComidaVegetariano(){
        dsl.clickCheckBox("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valor){
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
    }

    public void setEsporte(String... valores){
        for(String valor : valores){
            dsl.selecionarCombo("elementosForm:esportes", valor);
        }
    }

    public void cadastrar(){
        dsl.clickBotao("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro(){
        return dsl.obterTexto("resultado");
    }

    public String obterNomeCadastro(){
        return dsl.obterTexto("descNome");
    }

    public String obterSobrenomeCadastro(){
        return dsl.obterTexto("descSobrenome");
    }

    public String obterSexoCadastro(){
        return dsl.obterTexto("descSexo");
    }
    public String obterComidaCadastro(){
        return dsl.obterTexto("descComida");
    }
    public String obterEscolaridadeCadastro(){
        return dsl.obterTexto("descEscolaridade");
    }
    public String obterEsporteCadastro(){
        return dsl.obterTexto("descEsportes");
    }

}