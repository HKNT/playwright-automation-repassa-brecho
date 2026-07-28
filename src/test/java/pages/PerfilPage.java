package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PerfilPage extends BasePage {

    public PerfilPage(Page page) {
        super(page);
    }

    public void acessoPerfil(String url){
        navegarPara(url);
    }

    public Locator getNomePerfil(){
        return this.page.locator("input[name='firstName']");
    }

    public void printPerfil(){
        tirarPrintTelaCheia("telaPerfil");
    }
}
