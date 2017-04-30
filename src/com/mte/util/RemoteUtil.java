package com.mte.util;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/8/15
 */
public class RemoteUtil {
    private InputStream in;

    private BufferedReader out;

    Connection conn = null;
    Session sess = null;

    public boolean getConnectToRemoteServer(String ip1, String user1,
                                            String password1, String port) {
        try {
            conn = new Connection(ip1);
            conn.connect();
            boolean isAuthenticated = conn.authenticateWithPassword(user1,
                    password1);
            if (!isAuthenticated) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String readUntil() {
        try {
            StringBuffer sb = new StringBuffer();
            while (true) {
                String line = out.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line + "\n");
            }
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param value
     */
    private void write(String value) {
        try {
            sess.execCommand(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param command
     * @return
     */
    public String sendCommand(String command) {
        try {
            sess = conn.openSession();
            in = new StreamGobbler(sess.getStdout());

            out = new BufferedReader(new InputStreamReader(in));

            write(command);
            return readUntil();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            conn.close();
            sess.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
