package tests;

import io.github.cdimascio.dotenv.Dotenv;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PerfilPage;


public class LoginTest {
    static Dotenv env;
    static String URL;
    static String USER;
    static String PASS;

    private static Playwright playwright;
    private static Browser browser;
    private static Page page;

    @BeforeAll
    static void abrirNavegador(){
        URL  = System.getenv("APP_URL");
        USER = System.getenv("USER_LOGIN");
        PASS = System.getenv("USER_PASSWORD");

        if(URL == null || USER == null  || PASS == null){
            env   = Dotenv.load();
            URL  = env.get("APP_URL");
            USER = env.get("USER_LOGIN");
            PASS = env.get("USER_PASSWORD");
        }

        playwright = Playwright.create();
        browser    = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
    }

    @BeforeEach
    void abrirAbaNavegacao(){
        page = browser.newPage();
    }

    @AfterEach
    void fecharNavegador(){
        page.close();
    }

    @AfterAll
    static void fecharPlayWright(){
        playwright.close();
    }

    @Test
    void testFluxoLoginEPerfil(){
        page.navigate(URL+"/perfil/login");
        LoginPage login = new LoginPage(page);

        Locator buttonFacebook = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Facebook"));
        PlaywrightAssertions.assertThat(buttonFacebook).isVisible();
        login.loginSenha(USER,PASS);
        login.printLogin();
        login.clicarLogar();


        PerfilPage perfil = new PerfilPage(page);
        perfil.acessoPerfil(URL+"/perfil/");
        Locator nomePerfil = perfil.getNomePerfil();
        PlaywrightAssertions.assertThat(nomePerfil).isVisible();
        perfil.printPerfil();
        PlaywrightAssertions.assertThat(nomePerfil).hasValue("Hugo");
    }
}