package com.luoqing.validatecode;

import com.sun.javafx.iio.ImageStorage;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author: Qlo
 * @date: 2017-08-27 13:48
 * @explain:
 */
public class ValidateCode {
    private static final int[] STX = {0, 16, 34, 52};
    private static final int[] STY = {0, 0, 0, 0};
    private static final int[] EDX = {20, 38, 56, 74};
    private static final int[] EDY = {26, 26, 26, 26};

    protected void train() {

    }

    protected void test() {

    }

    protected void splitAllImages(String inputDir, String outputDir) throws IOException {
        File file = new File(ValidateCode.class.getClassLoader().getResource(inputDir).getPath());
        File[] files = file.listFiles();
        BufferedWriter wt = new BufferedWriter(
                new FileWriter(new File(outputDir + "/" + "label.txt")));
        for (int i = 0; i < files.length ; i++)//
        {
            String[] labels = splitFileName(files[i].getName());
            BufferedImage[] imgs = splitImage(inputDir, outputDir, files[i].getName(), labels, i);
            for (int j = 0; j < 4; j++) {
                wt.write(labels[j]);
                wt.write("\n");
            }
        }
        wt.close();
    }

    protected BufferedImage[] splitImage(String inputDir, String outputDir, String fileName, String[] labels, int index) throws IOException {
        int[] w = {EDX[0] - STX[0], EDX[1] - STX[1], EDX[2] - STX[2], EDX[3] - STX[3]};
        int[] h = {EDY[0] - STY[0], EDY[1] - STY[1], EDY[2] - STY[2], EDY[3] - STY[3]};
        BufferedImage img = readImage(inputDir + "/" + fileName);
        BufferedImage[] distImg = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            distImg[i] = new BufferedImage(w[i], h[i], BufferedImage.TYPE_INT_RGB);
            Graphics g = distImg[i].getGraphics();
            g.drawImage(img, 0, 0, distImg[i].getWidth(), distImg[i].getHeight(), STX[i], STY[i], EDX[i], EDY[i], null);
            ImageIO.write(distImg[i], "jpg", new File(outputDir + "/" + index + "-" + i + "-" + labels[i] + ".jpg"));
        }
        return distImg;

    }

    protected String[] splitFileName(String filename) {
        String[] labels = new String[4];
        String[] part = filename.split("-");
        for (int j = 0; j < 4; j++) {
            labels[j] = part[1].substring(j, j + 1);
        }
        return labels;
    }

    @Test
    public void test1() throws IOException {
        splitAllImages("train_notsplit","src/resources/train");
//        String input = "test_notsplit/1001-CztW.jpg";
//        System.out.println(input);
//        BufferedImage image = readImage(input);
//        int sWight = image.getWidth();
//        int sHight = image.getHeight();
//        System.out.println(sWight + "  " + sHight);
//
//        BufferedImage[] distImg = new BufferedImage[4];
//        for (int i = 0; i < 1; i++) {
//            distImg[i] = new BufferedImage(18, 26, image.getType());
//            Graphics g = distImg[i].getGraphics();
//            g.drawImage(image, 0, 0, 18, 26, 0, 0, 18, 26, null);
//            ImageIO.write(distImg[i], "jpg", new File("a.jpg"));
//        }
    }

    protected BufferedImage readImage(String imagePath) throws IOException {
        BufferedImage img = ImageIO.read(new FileImageInputStream(
                new File(ValidateCode.class.getClassLoader().getResource(imagePath).getPath())));
        int sWight = img.getWidth();
        int sHight = img.getHeight();
        BufferedImage newImage = new BufferedImage(sWight, sHight,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < sWight; x++) {
            for (int y = 0; y < sHight; y++) {
                int rgb = img.getRGB(x, y);
                newImage.setRGB(x, y, rgb);
            }
        }
        return newImage;
//        try {
//            ImageIO.write(newImage, "jpg", new File("aa.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
