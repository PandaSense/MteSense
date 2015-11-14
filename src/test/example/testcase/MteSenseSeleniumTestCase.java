package test.example.testcase;

import com.mte.common.base.MteSenseBaseCase;
import com.mte.util.ReportUtil;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  6/12/15
 */
public class MteSenseSeleniumTestCase extends MteSenseBaseCase {

    private Logger logger = Logger.getLogger(MteSenseSeleniumTestCase.class);
    private String testCaseName = this.getClass().getSimpleName();
    private ReportUtil report = new ReportUtil(testCaseName,props.get("mte.reportFile.path"));

    @Before
    public void setUp() throws Exception {
        beforeClass("chrome");
        asBaseCore.setTestCaseName(testCaseName);
        this.setReporter(report);
    }

    @Test
    public void testMain() throws Exception {

        asBaseCore.pause(4000);
        report.printStep("this is test",1);
        report.printImageIntoReport(asBaseCore.getCaptureScreenshotFile());

    }


    @After
    public void tearDown() throws Exception {
        afterClass();
    }

}
