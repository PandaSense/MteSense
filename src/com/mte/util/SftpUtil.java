package com.mte.util;

import com.jcraft.jsch.*;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.sftp.SftpClientFactory;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/8/15
 */
public class SftpUtil {


    private ChannelSftp command;

    private Session session;

    public SftpUtil() {
        command = null;
    }

    public boolean connect(String host, String login, String password, int port)
            throws JSchException {

        // If the client is already connected, disconnect
        if (command != null) {
            disconnect();
        }
        FileSystemOptions fso = new FileSystemOptions();
        try {
            SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
                    fso, "no");
            session = SftpClientFactory.createConnection(host, port,
                    login.toCharArray(), password.toCharArray(), fso);
            Channel channel = session.openChannel("sftp");
            channel.connect();
            command = (ChannelSftp) channel;

        } catch (FileSystemException e) {
            e.printStackTrace();
            return false;
        }
        return command.isConnected();
    }

    public void disconnect() {
        if (command != null) {
            command.exit();
        }
        if (session != null) {
            session.disconnect();
        }
        command = null;
    }

    public Vector<String> listFileInDir(String remoteDir) throws Exception {
        try {
            Vector<ChannelSftp.LsEntry> rs = command.ls(remoteDir);
            Vector<String> result = new Vector<String>();
            for (int i = 0; i < rs.size(); i++) {
                if (!isARemoteDirectory(rs.get(i).getFilename())) {
                    result.add(rs.get(i).getFilename());
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(remoteDir);
            throw new Exception(e);
        }
    }

    public Vector<String> listSubDirInDir(String remoteDir) throws Exception {
        Vector<ChannelSftp.LsEntry> rs = command.ls(remoteDir);
        Vector<String> result = new Vector<String>();
        for (int i = 0; i < rs.size(); i++) {
            if (isARemoteDirectory(rs.get(i).getFilename())) {
                result.add(rs.get(i).getFilename());
            }
        }
        return result;
    }

    public boolean createDirectory(String dirName) {
        try {
            command.mkdir(dirName);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean downloadFileAfterCheck(String remotePath, String localPath)
            throws IOException {
        FileOutputStream outputSrr = null;
        try {
            File file = new File(localPath);
            if (!file.exists()) {
                outputSrr = new FileOutputStream(localPath);
                command.get(remotePath, outputSrr);
            }
        } catch (SftpException e) {
            try {
                System.err.println(remotePath + " not found in "
                        + command.pwd());
            } catch (SftpException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (outputSrr != null) {
                outputSrr.close();
            }
        }
        return true;
    }

    public boolean downloadFile(String remotePath, String localPath)
            throws IOException {
        FileOutputStream outputSrr = new FileOutputStream(localPath);
        try {
            command.get(remotePath, outputSrr);
        } catch (SftpException e) {
            try {
                System.err.println(remotePath + " not found in "
                        + command.pwd());
            } catch (SftpException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (outputSrr != null) {
                outputSrr.close();
            }
        }
        return true;
    }

    public boolean uploadFile(String localPath, String remotePath)
            throws IOException {
        FileInputStream inputSrr = new FileInputStream(localPath);
        try {
            command.put(inputSrr, remotePath);
        } catch (SftpException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (inputSrr != null) {
                inputSrr.close();
            }
        }
        return true;
    }

    public boolean changeDir(String remotePath) throws Exception {
        try {
            command.cd(remotePath);
        } catch (SftpException e) {
            return false;
        }
        return true;
    }

    public boolean isARemoteDirectory(String path) {
        try {
            return command.stat(path).isDir();
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean removeRemoteFile(String remotePath) {

        try {

            command.rm(remotePath);

        } catch (SftpException e) {
            e.printStackTrace();

        }

        return true;

    }

    public String getWorkingDirectory() {
        try {
            return command.pwd();
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return null;
    }

}
