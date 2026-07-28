package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ScreenshotUtils;

public class BasePage {
    Page page;
    Locator locator;

    BasePage(Page page){
        this.page = page;
    }

    BasePage(Locator locator){
        this.locator = locator;
    }

    public void tirarPrintTelaCheia(String nomeArquivo){
        ScreenshotUtils.tirarPrintTelaInteira(page, nomeArquivo);
    }

    public void tirarPrintLocalizado(String nomeArquivo){
        ScreenshotUtils.tirarPrintAreaDaPagina(locator, nomeArquivo);
    }
}
