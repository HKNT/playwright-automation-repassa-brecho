package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.ScreenshotUtils;

public class LoginPage {
    Page page;

     public LoginPage(Page page){
         this.page = page;
     }

    public void loginSenha(String user, String pass){
        this.page.getByPlaceholder("E-mail").fill(user);
        this.page.getByPlaceholder("Senha").fill(pass);
        aguardandoRecaptchaResponse(this.page);
    }

    private void aguardandoRecaptchaResponse(Page page){
        page.waitForResponse(
                response -> response.url().contains("https://www.google.com/recaptcha/api2/reload"),
                () ->{
                    System.out.println("Recaptcha carregou!");
                }
        );
    }

    public void clicarLogar(){
        Locator botaoLogar =
                this.page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Entrar"));
        botaoLogar.click();

        if(System.getenv("CI") == null){
            page.pause(); // pausa para resolver o recaptcha
        }
    }

    public void printLogin(){
        ScreenshotUtils.tirarPrintTelaInteira(this.page, "telaLogin");
    }
}