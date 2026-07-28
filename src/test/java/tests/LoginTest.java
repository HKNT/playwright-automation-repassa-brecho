package tests;

import io.github.cdimascio.dotenv.Dotenv;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;
import pages.LoginPage;
import pages.PerfilPage;


public class LoginTest {
    Dotenv env = Dotenv.load();
    String URL = env.get("APP_URL");

    private static Playwright playwright;
    private static Browser browser;
    private static Page page;

    @BeforeAll
    static void abrirNavegador(){
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
        login.loginSenha();
        login.printLogin();
        login.clicarLogar();
        page.pause(); // pausa para resolver o recaptcha

        PerfilPage perfil = new PerfilPage(page);
        perfil.acessoPerfil();
        Locator nomePerfil = perfil.getNomePerfil();
        PlaywrightAssertions.assertThat(nomePerfil).isVisible();
        perfil.printPerfil();
        PlaywrightAssertions.assertThat(nomePerfil).hasValue("Hugo");
    }
}