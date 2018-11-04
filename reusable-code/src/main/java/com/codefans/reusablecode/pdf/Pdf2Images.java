package com.codefans.reusablecode.pdf;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;


/**
 * @Author: ShengzhiCai
 * @Date: 2018-11-04 10:46
 */

public class Pdf2Images {

    /**
     * jpeg的图片要比png图片小很多
     */
    private static final String DEFAULT_IMG_SUFFIX = "jpeg";
    private static final int DEFAULT_DPI = 300;

    public static void main(String[] args) {
        String rootPath = "C:\\Users\\Administrator\\Downloads";
//        pdf2img(rootPath + File.separator + "违法记分制度-不同分值对应不同措施.pdf", rootPath + File.separator + "违法记分制度-不同分值对应不同措施.png");
        pdf2Image(rootPath + File.separator + "违法记分制度-不同分值对应不同措施.pdf", rootPath, "PNG", 300);
    }

    public static void pdf2img(String pdfFilePath, String imgFilePath) {
        File file = new File(pdfFilePath);
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for(int i=0;i<pageCount;i++){
                BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI);
                //BufferedImage image = renderer.renderImage(i, 2.5f);
                ImageIO.write(image, DEFAULT_IMG_SUFFIX, new File(imgFilePath));
            }
            System.out.println("PDF文档转PNG图片成功！");
        } catch (IOException e) {
        e.printStackTrace();
        }
    }

    /***
     * PDF文件转PNG图片，全部页数
     *
     * @param PdfFilePath pdf完整路径
     * @param dstImgFolder 图片存放的文件夹
     * @param dpi dpi越大转换后越清晰，相对转换速度越慢
     * @return
     */
    public static void pdf2Image(String PdfFilePath, String dstImgFolder, String imgSuffix, int dpi) {
        File file = new File(PdfFilePath);
        PDDocument pdDocument;
        try {

            String imgPDFPath = file.getParent();
            int dot = file.getName().lastIndexOf('.');
            String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
            String imgFolderPath = null;
            if (dstImgFolder.equals("")) {
                imgFolderPath = imgPDFPath;// 获取图片存放的文件夹路径
            } else {
                imgFolderPath = dstImgFolder;
            }

            if (createDirectory(imgFolderPath)) {

                pdDocument = PDDocument.load(file);
                PDFRenderer renderer = new PDFRenderer(pdDocument);
            /* dpi越大转换后越清晰，相对转换速度越慢 */
                int pages = pdDocument.getNumberOfPages();
                StringBuffer imgFilePath = null;
                for (int i = 0; i < pages; i++) {
                    String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
                    imgFilePath = new StringBuffer();
                    imgFilePath.append(imgFilePathPrefix);
                    imgFilePath.append("_");
                    imgFilePath.append(String.valueOf(i + 1));
                    imgFilePath.append(".").append(imgSuffix);
                    File dstFile = new File(imgFilePath.toString());
                    BufferedImage image = renderer.renderImageWithDPI(i, dpi);
                    ImageIO.write(image, imgSuffix, dstFile);
                }
                System.out.println("PDF文档转PNG图片成功！");

            } else {
                System.out.println("PDF文档转PNG图片失败：" + "创建" + imgFolderPath + "失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }


}
