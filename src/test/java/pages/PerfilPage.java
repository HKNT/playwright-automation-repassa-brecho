package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ScreenshotUtils;

public class PerfilPage {
    Page page;

    public PerfilPage(Page page) {
        this.page = page;
    }

    public void acessoPerfil(String url){
        this.page.navigate(url);
    }

    public Locator getNomePerfil(){
        return this.page.locator("input[name='firstName']");
    }

    public void printPerfil(){
        ScreenshotUtils.tirarPrintTelaInteira(this.page, "telaPerfil");
    }
}
