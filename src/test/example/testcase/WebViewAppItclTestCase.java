package test.example.testcase;

import com.mte.common.base.MteSenseBaseCase;
import org.junit.After;
import org.junit.Before;
import test.example.task.WebViewAppHomeTask;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/16/15
 */
public class WebViewAppItclTestCase extends MteSenseBaseCase {

    private String testCaseName = this.getClass().getSimpleName();

    private WebViewAppHomeTask homeTask;

    @Before
    public void setUp() throws Exception {
        beforeClass("ios");
        asBaseCore.setTestCaseName(testCaseName);
    }

    @Test
    public void testMain() throws Exception {

        homeTask=new WebViewAppHomeTask(asBaseCore);

        homeTask.doSearchByUrl("cn.bing.com");

        homeTask.changeToWebView();

        homeTask.doBingSearch("baidu");
    }

    @After
    public void tearDown() throws Exception {
        afterClass();
    }

}
