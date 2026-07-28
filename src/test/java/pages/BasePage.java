package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.ScreenshotUtils;

public class BasePage {
    protected Page    page;
    Locator locator;

    BasePage(Page page){
        this.page = page;
    }

    public void tirarPrintTelaCheia(String nomeArquivo){
        ScreenshotUtils.tirarPrintTelaInteira(page, nomeArquivo);
    }

    public void tirarPrintLocalizado(Locator locator, String nomeArquivo){
        ScreenshotUtils.tirarPrintAreaDaPagina(locator, nomeArquivo);
    }

    public void navegarPara(String url){
        this.page.navigate(url);
    }
}
