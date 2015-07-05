package com.mte.android.mmr;

import com.android.chimpchat.ChimpChat;
import com.android.chimpchat.adb.AdbBackend;
import com.android.chimpchat.core.ChimpImageBase;
import com.android.chimpchat.core.IChimpBackend;
import com.android.chimpchat.core.IChimpDevice;
import com.android.chimpchat.core.IChimpImage;

import java.util.Collection;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
 * Project : MteMonkeyRunner
 * Created : java
 * Date : 7/1/15
 */
public class MteMonkeyRunner {

    private static final Logger LOG = Logger.getLogger(MteMonkeyRunner.class.getCanonicalName());
    private static ChimpChat chimpchat;

    static void setChimpChat() {
        TreeMap<String, String> options = new TreeMap<String, String>();
        options.put("backend", "adb");
        chimpchat = ChimpChat.getInstance(options);
    }

    public static MteMonkeyDevice waitForConnection(long timeoutMs, String deviceId) {
        setChimpChat();
        IChimpDevice device = chimpchat.waitForConnection(timeoutMs, deviceId);

        MteMonkeyDevice mmd = new MteMonkeyDevice(device);

        return mmd;
    }

    public static MteMonkeyDevice waitForConnection() {
        setChimpChat();
        IChimpDevice device = chimpchat.waitForConnection();

        MteMonkeyDevice mmd = new MteMonkeyDevice(device);

        return mmd;
    }

    public static void shutDown() {
        chimpchat.shutdown();
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            LOG.log(Level.SEVERE, "Error sleeping", e);
        }
    }


    public static MteMonkeyImage loadImageFromFile(String path) {
        IChimpImage image = ChimpImageBase.loadImageFromFile(path);

        return new MteMonkeyImage(image);
    }

    /**
     * Display an alert dialog.
     *
     * @param message the message to show.
     * @param title   the title of the dialog box.
     * @param okTitle the title of the button.
     */
    public static void alert(String message, String title, String okTitle) {
        Object[] options = {okTitle};
        JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }

    /**
     * Display a dialog allow the user to pick a choice from a list of choices.
     *
     * @param message the message to show.
     * @param title   the title of the dialog box.
     * @param choices the list of the choices to display.
     * @return the index of the selected choice, or -1 if nothing was chosen.
     */
    public static int choice(String message, String title, Collection<String> choices) {
        Object[] possibleValues = choices.toArray();
        Object selectedValue = JOptionPane.showInputDialog(null, message, title,
                JOptionPane.QUESTION_MESSAGE, null, possibleValues, possibleValues[0]);

        for (int x = 0; x < possibleValues.length; x++) {
            if (possibleValues[x].equals(selectedValue)) {
                return x;
            }
        }
        // Error
        return -1;
    }


    /**
     * Display a dialog that allows the user to input a text string.
     *
     * @param message      the message to show.
     * @param initialValue the initial value to display in the dialog
     * @param title        the title of the dialog box.
     * @return the entered string, or null if cancelled
     */
    public static String input(String message, String initialValue, String title) {
        return (String) JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE, null, null,
                initialValue);
    }

}
