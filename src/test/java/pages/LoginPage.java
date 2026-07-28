package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage extends BasePage {

     public LoginPage(Page page){
         super(page);
     }

    public void loginSenha(String user, String pass){
        this.page.getByPlaceholder("E-mail").fill(user);
        this.page.getByPlaceholder("Senha").fill(pass);
    }

    public Response aguardandoRecaptchaResponse(){
        try{
            Response resp = page.waitForResponse(
                    response -> response.url().contains("https://www.google.com/recaptcha/api2/reload"),
                    () ->{
                        System.out.println("Recaptcha carregou!");
                    }
            );
            return resp;
        }catch (Exception e){
            System.out.println("TimeOut: A requisição do Recaptcha não respondeu no tempo certo!");
            System.out.println(e.getMessage());
            return page.waitForResponse(
                    response -> response.url().contains("https://www.google.com/recaptcha/api2/reload"),
                    () -> System.out.println("Aguardando disparo do Recaptcha...")
            );
        }
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
        tirarPrintTelaCheia("telaLogin");
    }
}