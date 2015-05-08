package com.mte.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by java on 3/26/15.
 */
public class FileUtil {
    /**
     * appends the data to the specified filename with file locking.
     * Uses the java.nio.channel package to implement the locking
     *
     * @param filename
     * @param data
     */
    public static void append(String filename, String data) {
        append_or_overwrite(filename, data, true);
    }

    /**
     * Copies src file to dst file. If the dst file does not exist, it is created.
     * Locking is put onto the dst file during the copy.
     * Uses the java.nio.channel package to implement the locking
     *
     * @return <Code>true</Code> if the copy had no exception<br>
     *         <Code>false</Code> if the copoy had exception
     */
    public static boolean copy(String src_filename, String dst_filename) {
        try {
            // Create channel on the source
            File src_file = new File(src_filename);
            FileChannel srcChannel = new FileInputStream(src_file).getChannel();

            // Create channel on the destination
            @SuppressWarnings("unused")
            File dst_file = new File(dst_filename);
            // create a overwrite channel (with false)
            FileChannel dstChannel = new FileOutputStream(dst_filename, false).getChannel();
            //FileLock lock = dstChannel.lock();
            try {

                // Copy file contents from source to destination
                dstChannel.transferFrom(srcChannel, 0, srcChannel.size());

                // Close the channels
                srcChannel.close();
                dstChannel.close();
            } finally {
                //    lock.release();
            }

        } catch (IOException e) {
//            System.out.println(e.getClass() + ", " + e.getMessage());
        } catch (Exception e) {
//        	System.out.println(e.getMessage());
        }

        return true;
    }

    public static boolean appendImage(String filename, BufferedImage image) {

        try {
            File file = new File(filename);
            String format = "JPEG";
            ImageIO.write(image, format, file);


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    /**
     * test for the existence of the given filename.
     *
     * @param filename full pathname of the file
     * @return true if the file exists<br>
     *         otherwise false
     */
    public static boolean exists(String filename) {
        return new File(filename).exists();

    }

    /**
     * Overwrites the given file with the data with file locking.
     * Uses the java.nio.channel package to implement the locking and
     * will create the file if file doesn't exist.
     *
     * @param filename
     * @param data
     */
    public static void overwrite(String filename, String data) {
        append_or_overwrite(filename, data, false);
    }

    /**
     * reads the specified file and returns the content ps a StringBuffer
     *
     * @param filename
     * @return content of the file ps a StringBuffer
     */
    public static String read(String filename) {
        String content = null;

        try {
            @SuppressWarnings("unused")
            boolean copy = false;
            @SuppressWarnings("unused")
            FileChannel in_channel;
            @SuppressWarnings("unused")
            FileLock lock;

            // Get a file channel for the file
            File file = new File(filename);
            FileInputStream input = new FileInputStream(file);

            byte[] result = new byte[(int) file.length()];

            final int length = result.length;
            int offset = 0;
            long byte_read = 0;

            while (byte_read != -1 && offset < file.length()) {
                try {
                    byte_read = input.read(result, offset, length - offset);
                    if (byte_read >= 0)   // bytesRead == -1 when end-of-file is reached
                        offset += byte_read;

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            // copy the result into content to return
            // this is because we must have result declared inside the try/catch clause
            content = new String(result);
            input.close();    // close file
        } catch (Exception e) {
            return e.getMessage();
        }

        return content;
    }

    //--------------------------- private methods ---------------------------------

    /**
     * appends or overwrite the specified file with the data
     *
     * @param filename
     * @param data
     * @param append   - true for append, false for overwrite
     */
    private static void append_or_overwrite(String filename, String data, boolean append) {
        try {
            // Get a file channel for the file
            File file = new File(filename);
            FileChannel channel = new FileOutputStream(file, append).getChannel();

            // Use the file channel to create a lock on the file.
            // This method blocks until it can retrieve the lock.
            FileLock lock = channel.lock();

            try {
                // position the file pointer at the end of the file
                //channel.position(channel.size());

                ByteBuffer bb = ByteBuffer.wrap(data.getBytes());

                int byte_written = channel.write(bb);

                channel.close();
            } finally {
                lock.release();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
