package test.example.testcase;

import java.util.Collection;

import com.mte.android.mmr.MteMonkeyDevice;
import com.mte.android.mmr.MteMonkeyImage;
import com.mte.android.mmr.MteMonkeyRunner;
import com.mte.util.DateTimeUtil;

public class MteMonkeyRunnerTest {

    public static void main(String args[]) {

        String apppath = "./app/android/oschina/osc-android-app-2.2.apk";
        String packageName = "net.oschina.app";
        String startActivity = "net.oschina.app/.AppStart";

        MteMonkeyDevice device = MteMonkeyRunner.waitForConnection(100000, "HC477WY00656");


        System.out.println("Device name is : " + device.getProperty("build.model"));

        for (String prop : device.getPropertyList()) {

            System.out.println(prop + " : " + device.getProperty(prop));
        }

        device.installPackage(apppath);

        device.startActivity(startActivity);

        MteMonkeyRunner.sleep(30000);

        MteMonkeyImage image = new MteMonkeyImage(device.takeSnapshot());

        image.writeToFile("./screen/MteMonkeyRunnerTest" + DateTimeUtil.getCurrentDateTime() + ".png", "png");

        MteMonkeyRunner.sleep(10000);

        Collection<String> viewLst = device.getViewIdList();

        System.out.println("device.getViewIdList() is : " + viewLst.size());

        if (viewLst.size() >= 1) {
            for (String prop : viewLst) {
                System.out.println(prop);
            }
        }
// This is test

        MteMonkeyRunner.sleep(20000);

        device.removePackage(packageName);

        device.dispose();

    }

}
