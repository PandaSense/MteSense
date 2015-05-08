package com.mte.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by java on 3/24/15.
 */
public class HttpUtils {

    private URL url = null;
    private HttpURLConnection connection = null;
    private Logger logger = Logger.getLogger(HttpUtils.class);

    public BufferedReader sendHttpGetReq(String urlStr, String reqParams,
                                         String cookie, String writerCharset) {

        if (urlStr.startsWith("http://")) {
            try {

                // Send data
                if (reqParams != null && reqParams.length() > 0) {
                    urlStr += "?"
                            + new String(reqParams.getBytes("ISO-8859-1"),
                            writerCharset);
                }

                url = new URL(urlStr);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("GET");
                if (cookie != null && cookie.length() > 0) {
                    connection.addRequestProperty("Cookie", cookie);
                }
                connection.setUseCaches(false);
                connection.connect();

                // Get the response
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(),
                                "ISO-8859-1"));
                return reader;
            } catch (Exception e) {
                logger.error("HttpUtils sendHttpGetReq() error!", e);
            }
        }
        return null;
    }

    public BufferedReader sendHttpPostReq(String urlStr, String reqParams,
                                          String writerCharset) {

        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setRequestProperty("Connection", "close");
            connection.setUseCaches(false);
            connection.connect();

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    connection.getOutputStream(), writerCharset));
            out.write(reqParams);
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "ISO-8859-1"));
            return reader;
        } catch (Exception e) {
            logger.error("HttpUtils sendHttpPostReq() error!", e);
        }
        return null;
    }

    public String getReqParamsNoCharset(String fileName) {
        String reqParams = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader()
                            .getResourceAsStream(fileName)));
            reqParams = this.getReqDataNoCharset(reader);
            reader.close();
            return reqParams;
        } catch (Exception e) {
            logger.error("HttpUtils getReqParamsNoCharset() error!", e);
        }
        return reqParams;
    }

    public String getReqParamsByCharset(String fileName, String readerCharset) {
        String reqParams = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    this.getClass().getClassLoader()
                            .getResourceAsStream(fileName)));
            reqParams = this.getReqDataByCharset(reader, readerCharset);
            reader.close();
            return reqParams;
        } catch (Exception e) {
            logger.error("HttpUtils getReqParamsByCharset() error!", e);
        }

        return reqParams;
    }

    public String getReqDataNoCharset(BufferedReader reader) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            line = new String(buffer.toString().getBytes("ISO-8859-1"));
            return line;
        } catch (Exception e) {
            logger.error("HttpUtils getReqDataNoCharset() error!", e);
        }
        return line;
    }

    public String getReqDataByCharset(BufferedReader reader,
                                      String readerCharset) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            line = new String(buffer.toString().getBytes("ISO-8859-1"),
                    readerCharset);
            return line;
        } catch (Exception e) {
            logger.error("HttpUtils getReqDataByCharset() error!", e);
        }
        return line;
    }

    public String getRespDataNoCharset(BufferedReader reader) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            line = buffer.toString();
            return line;
        } catch (Exception e) {
            logger.error("HttpUtils getRespDataNoCharset() error!", e);
        }
        return line;
    }

    public String getRespDataByCharset(BufferedReader reader,
                                       String readerCharset) {
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");
            }
            line = new String(buffer.toString().getBytes("ISO-8859-1"),
                    readerCharset);
            return line;
        } catch (Exception e) {
            logger.error("HttpUtils getRespDataByCharset() error!", e);
        }
        return line;
    }

    public void closed(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public String get_node_text(String respContent, String nodeName) {

        String dest_string = "<" + nodeName;
        int start = respContent.indexOf(dest_string);
        // System.out.println("start=" + start);
        if (-1 == start)
            return "";
        start = respContent.indexOf('>', start + 1);
        // System.out.println("start=" + start);
        if (-1 == start)
            return "";
        start += 1;
        // System.out.println("start=" + start);

        dest_string = "</" + nodeName + ">";
        int end = respContent.indexOf(dest_string, start);
        // System.out.println("end=" + end);
        if (-1 == end)
            return "";

        return respContent.substring(start, end);

    }

    public String getVercode(String url, String reqParams, String cookie,
                             String charset) {
        logger.debug("getVercode URL : " + url);
        BufferedReader reader = sendHttpGetReq(url, reqParams, cookie, charset);
        String respContent = getRespDataByCharset(reader, charset);
        String vercode = get_node_text(respContent, "h1");
        logger.debug("vercode is : " + vercode);
        closed(reader);
        return vercode;
    }
}
