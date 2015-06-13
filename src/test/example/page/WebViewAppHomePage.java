package test.example.page;

import com.mte.common.base.MteSenseBasePage;
import com.mte.common.base.MteSenseCore;
import com.mte.common.base.MteSenseLocator;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/8/15
 */
public class WebViewAppHomePage extends MteSenseBasePage {

    private Logger logger = Logger.getLogger(WebViewAppHomePage.class);
    MteSenseLocator locator = new MteSenseLocator("./config/mtesenseexample.yaml");

    private String pageName = this.getClass().getSimpleName();

    public String getPageName() {
        return pageName;
    }


    public WebViewAppHomePage(MteSenseCore senseCore) {
        super(senseCore);
    }

    public WebElement getUrlField() {

        return asCore.findElement(locator.getLocator("urlfield"));

    }

    public WebElement getGoButton() {

        return asCore.findElement(locator.getLocator("go"));

    }

    public WebElement getBingSearchField() {

        return asCore.findElement(locator.getLocator("bingsearchfield"));

    }

}
