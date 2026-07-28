package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class ScreenshotUtils {

    public static void tirarPrintTelaInteira(Page page, String nomeArquivo){
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenShot/"+nomeArquivo+".png"))
                .setFullPage(true));
    }

    public static void tirarPrintAreaDaPagina(Locator locator, String nomeArquivo){
        locator.screenshot(new Locator.ScreenshotOptions()
                .setPath(Paths.get("screenShot/"+nomeArquivo+".png")));
    }
}
