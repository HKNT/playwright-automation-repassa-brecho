package pages;

import io.github.cdimascio.dotenv.Dotenv;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import utils.ScreenshotUtils;

public class LoginPage {
    Page page;
    Dotenv env  = Dotenv.load();
    String USER = env.get("USER_LOGIN");
    String PASS = env.get("USER_PASSWORD");

     public LoginPage(Page page){
         this.page = page;
     }

    public void loginSenha(){
        this.page.getByPlaceholder("E-mail").fill(USER);
        this.page.getByPlaceholder("Senha").fill(PASS);
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
    }

    public void printLogin(){
        ScreenshotUtils.tirarPrintTelaInteira(this.page, "telaLogin");
    }
}

/*
page.navigate("https://repassa.com.br/perfil/login");
            System.out.println(page.title());


            PlaywrightAssertions.assertThat(page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Facebook"))).isVisible();

            page.getByPlaceholder("E-mail").fill("hgcontatos@gmail.com");
            page.getByPlaceholder("Senha").fill("!Deathnote757");

            PlaywrightAssertions.assertThat(page.getByRole(
                    AriaRole.BUTTON,
                    new Page.GetByRoleOptions().setName("Entrar"))).isVisible();

            page.waitForResponse(
                    response -> response.url().contains("https://www.google.com/recaptcha/api2/reload"),
                    () ->{
                        System.out.println("Esperando o recaptcha");
                    }
            );
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Entrar")).click();
            System.out.println("Esperando");
 */