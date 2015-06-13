package test.example.task;

import com.mte.common.base.MteSenseBaseTask;
import com.mte.common.base.MteSenseCore;
import test.example.page.WebViewAppHomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import java.util.Set;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/16/15
 */
public class WebViewAppHomeTask extends MteSenseBaseTask {

    private Logger logger = Logger.getLogger(WebViewAppHomeTask.class);

    private WebViewAppHomePage homepage;

    public WebViewAppHomeTask(MteSenseCore senseCore) {

        super(senseCore,null);
        homepage=new WebViewAppHomePage(asCore);
    }

    public void doSearchByUrl(String url){

        logger.info("Start to run doSearchByUrl");

        WebElement element = homepage.getUrlField();

        asCore.sendKeys(element, url);

        asCore.hideKeyboard();

        asCore.captureScreenshotByCase();
        

        element = homepage.getGoButton();

        asCore.click(element);

        asCore.pause(3000);

        asCore.captureScreenshotByCase();

    }

    public void changeToWebView(){

        logger.info("Start to run changeToWebView");

        Set<String> contextNames = asCore.getContextHandles();

        for (String contextName : contextNames) {
            if (contextName.contains("WEBVIEW")) {
                asCore.context(contextName);
            }
        }

    }

    public void doBingSearch(String url){

        logger.info("Start to run doBingSearch");

        WebElement element = homepage.getBingSearchField();

        element.sendKeys("baidu");

        element.submit();

        asCore.hideKeyboard();

        asCore.pause(3000);

        asCore.captureScreenshotByCase();

    }


}
