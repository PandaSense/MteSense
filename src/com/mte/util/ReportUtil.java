package com.mte.util;

import com.lowagie.text.*;
import com.lowagie.text.rtf.RtfWriter2;
import com.lowagie.text.rtf.style.RtfFont;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Project :  mtesense
 * Created :  java
 * Date    :  4/2/15
 */
public class ReportUtil {

    private String reportFileName,reportFileFoler;

    private String reportFileFullPath;

    private String fontString = "Courier New";

    private boolean isFirst = true;

    RtfFont objectFont = new RtfFont(fontString, 12, RtfFont.STYLE_UNDERLINE);
    RtfFont titleFont = new RtfFont(fontString, 12, RtfFont.BOLD);
    RtfFont normalFont = new RtfFont(fontString, 12);

    Document doc = null;
    FileOutputStream steam = null;
    RtfWriter2 rtfWriter2 = null;


    public ReportUtil(String testCaseReportName,String reportFileFoler) {
        reportFileName = testCaseReportName;
        this.reportFileFoler=reportFileFoler;
        setReportFileFullPath();
        setReportFile();
    }

    private void setReportFileFullPath() {
        reportFileFullPath = reportFileFoler + "/" + reportFileName+".rtf";
    }

    private void setReportFile() {

        try {
            File report_file = new File(reportFileFullPath);

            if (!report_file.exists()) {
                report_file.createNewFile();
            }
            doc = new Document(PageSize.A4);
            steam = new FileOutputStream(reportFileFullPath,true);
            rtfWriter2 = RtfWriter2.getInstance(doc, steam);
            doc.open();
            rtfWriter2.importRtfDocument(new FileInputStream(report_file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printReport(String msg) {

        String curr_date_curr_zone = DateTimeUtil.formatedTime("yyyy-MM-dd HH:mm:ss:SSS");

        switch (msg.trim()) {
            case "START":
                try {
                    Paragraph p = new Paragraph();
                    p.setFont(objectFont);
                    Chunk c = new Chunk("Test Case Name :");
                    c.setFont(titleFont);
                    p.add(c);
                    Chunk c1 = new Chunk(reportFileName);
                    c1.setFont(titleFont);
                    p.add(c1);
                    doc.add(p);

                    p = new Paragraph();
                    doc.add(p);

                    p = new Paragraph();
                    c = new Chunk("Report Date :");
                    c.setFont(objectFont);
                    p.add(c);
                    c1 = new Chunk(curr_date_curr_zone);
                    c1.setFont(normalFont);
                    p.add(c1);
                    doc.add(p);

                    p = new Paragraph();
                    c = new Chunk("File Name :");
                    c.setFont(objectFont);
                    p.add(c);
                    c1 = new Chunk(reportFileFullPath);
                    c1.setFont(normalFont);
                    p.add(c1);
                    doc.add(p);

                    p = new Paragraph();
                    doc.add(p);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "END":
                try {
                    Paragraph p = new Paragraph();
                    doc.add(p);

                    p = new Paragraph();
                    Chunk c = new Chunk("End Date :");
                    c.setFont(objectFont);
                    p.add(c);
                    Chunk c1 = new Chunk(curr_date_curr_zone);
                    c1.setFont(normalFont);
                    p.add(c1);
                    doc.add(p);
                    doc.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    Paragraph p = new Paragraph();
                    doc.add(p);

                    p = new Paragraph();
                    Chunk c = new Chunk(msg);
                    c.setFont(normalFont);
                    p.add(c);
                    doc.add(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public void printImageIntoReport(File imageFile) {

        try {
            Image img = Image.getInstance(imageFile.getAbsolutePath());
            Paragraph p = new Paragraph();
            Chunk c = new Chunk(img, 0, 0);
            p.add(c);
            doc.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printImageIntoReport(String imageFileFullPath) {
        try {
            Image img = Image.getInstance(imageFileFullPath);
            Paragraph p = new Paragraph();
            Chunk c = new Chunk(img, 0, 0);
            p.add(c);
            doc.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTestObject(String message) {

        try {
            Paragraph p = new Paragraph();
            p.setFont(objectFont);
            Chunk c = new Chunk("Test Objectives:");
            c.setFont(titleFont);
            p.add(c);
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(" ");
            p.add(c);
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(message);
            c.setFont(normalFont);
            p.add(c);
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(" ");
            p.add(c);
            doc.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void printStep(String message,int stepNumber) {

        try {
            Paragraph p = new Paragraph();
            Chunk c = new Chunk(" ");
            doc.add(p);

            p = new Paragraph();
            p.setFont(objectFont);
            c = new Chunk("Test Step " + stepNumber);
            c.setFont(titleFont);
            p.add(c);
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(" ");
            doc.add(p);

            p = new Paragraph();
            p.setFont(objectFont);
            c = new Chunk("Description (Actions)");
            c.setFont(titleFont);
            p.add(c);
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(" ");
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(message);
            c.setFont(normalFont);
            p.add(c);
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(" ");
            doc.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void printExpectResult(String message) {

        try {
            Paragraph p = new Paragraph();
            Chunk c = new Chunk(" ");
            doc.add(p);

            p = new Paragraph();
            p.setFont(objectFont);
            c = new Chunk("Expected Results:");
            c.setFont(titleFont);
            p.add(c);
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(message);
            doc.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printActualResult(String message) {

        try {
            Paragraph p = new Paragraph();
            Chunk c = new Chunk(" ");
            doc.add(p);

            p = new Paragraph();
            p.setFont(objectFont);
            c = new Chunk("Actual Results:");
            c.setFont(titleFont);
            p.add(c);
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(" ");
            doc.add(p);

            p = new Paragraph();
            c = new Chunk(message);
            c.setFont(normalFont);
            p.add(c);
            doc.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
