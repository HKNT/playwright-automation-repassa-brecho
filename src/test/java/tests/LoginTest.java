package tests;

import io.github.cdimascio.dotenv.Dotenv;

import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
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
    private static String path_login;

    @BeforeAll
    static void abrirNavegador(){
        env  = Dotenv.configure().ignoreIfMissing().load();
        URL  = System.getenv("APP_URL") != null ? System.getenv("APP_URL") : env.get("APP_URL");
        USER = System.getenv("USER_LOGIN") != null ? System.getenv("USER_LOGIN") : env.get("USER_LOGIN");
        PASS = System.getenv("USER_PASSWORD") != null ? System.getenv("USER_PASSWORD"): env.get("USER_PASSWORD");

        path_login = "/perfil/login";
        boolean CI = false;
        CI = System.getenv("CI") != null;

        playwright = Playwright.create();
        browser    = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(CI)
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
    @DisabledIfEnvironmentVariable(named= "CI", matches = "true")
    @DisplayName("Fluxo de login + validação de perfil - Deve falhar por conta do recaptcha")
    void testFluxoLoginEPerfil(){
        page.navigate(URL+path_login);
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

    @Test
    @DisplayName("Fluxo de validação antibot")
    void testFluxoAntiBot(){
        page.navigate(URL+path_login);
        LoginPage login = new LoginPage(page);

        login.loginSenha(USER,PASS);
        login.clicarLogar();

        Response response = login.aguardandoRecaptchaResponse(() -> login.clicarLogar());
        Assertions.assertNotNull(response, "A requisição do Recaptcha foi disparada.");
        Assertions.assertTrue(response.ok(), "Deve falhar no Recaptcha");
    }
}