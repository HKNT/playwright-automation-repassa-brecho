package pages;

import io.github.cdimascio.dotenv.Dotenv;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ScreenshotUtils;

public class PerfilPage {
    Page page;
    Dotenv env = Dotenv.load();
    String URL = env.get("APP_URL");

    public PerfilPage(Page page) {
        this.page = page;
    }

    public void acessoPerfil(){
        this.page.navigate(URL+"/perfil/");
    }

    public Locator getNomePerfil(){
        return this.page.locator("input[name='firstName']");
    }

    public void printPerfil(){
        ScreenshotUtils.tirarPrintTelaInteira(this.page, "telaPerfil");
    }
}
