package com.test;

import java.awt.Color;  
import java.awt.Graphics2D;  
import java.awt.Image;  
import java.awt.geom.AffineTransform;  
import java.awt.image.AffineTransformOp;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.io.UnsupportedEncodingException;  
import java.util.Hashtable;  
import javax.imageio.ImageIO;  
import com.google.zxing.BarcodeFormat;  
import com.google.zxing.EncodeHintType;  
import com.google.zxing.MultiFormatWriter;  
import com.google.zxing.WriterException;  
import com.google.zxing.common.BitMatrix;  
  
  
/**  
 * @author tangb  
 *   
 */  
public class QRCode {  
    // ͼƬ��ȵ�һ��  
    private static final int IMAGE_WIDTH = 100;  
    private static final int IMAGE_HEIGHT = 100;  
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;  
    private static final int FRAME_WIDTH = 2;  
    // ��ά��д����  
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();  
  
    public static void encode(String content, int width, int height,  
            String srcImagePath, String destImagePath) {  
        try {  
            //����ͼƬ�ļ�  
            ImageIO.write(genBarcode(content, width, height, srcImagePath),  
                    "jpg", new File(destImagePath));  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (WriterException e) {  
            e.printStackTrace();  
        }  
    }  
  
    private static BufferedImage genBarcode(String content, int width,  
            int height, String srcImagePath) throws WriterException,  
            IOException {  
        // ��ȡԴͼ��  
        BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH,  
                IMAGE_HEIGHT, true);  
        int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];  
        for (int i = 0; i < scaleImage.getWidth(); i++) {  
            for (int j = 0; j < scaleImage.getHeight(); j++) {  
                srcPixels[i][j] = scaleImage.getRGB(i, j);//ͼƬ�����ص�  
            }  
        }  
       //����  
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        // ���ɶ�ά��  
        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,  
                width, height, hints);  
        // ��ά����תΪһά��������  
        int halfW = matrix.getWidth() / 2;  
        int halfH = matrix.getHeight() / 2;  
        int[] pixels = new int[width * height];  
  
        for (int y = 0; y < matrix.getHeight(); y++) {  
            for (int x = 0; x < matrix.getWidth(); x++) {  
                // ���Ͻ���ɫ,�����Լ���Ҫ������ɫ��Χ����ɫ  
            	 if (x > 0 && x <140 && y > 0 && y <  140) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();  
                     pixels[y * width + x] = matrix.get(x, y) ? 0x00CED1  
                             : 0x6495ED; //ǰ��ɫ������ɫ�ı����Ͻ�������ɫ
                 }
            	 else if (x > matrix.getWidth()-140 && x <matrix.getWidth() && y > 0 && y <  140) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();  
                     pixels[y * width + x] = matrix.get(x, y) ? 0x00CED1  
                             : 0x6495ED; //�ı����Ͻ�������ɫ
                 }
            	 else if (x > 0 && x <140 && y > matrix.getHeight()-140 && y <  matrix.getHeight()) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();  
                     pixels[y * width + x] = matrix.get(x, y) ? 0x00CED1  
                             : 0x6495ED; //�ı����½�������ɫ
                 }
            	 else if (x > matrix.getWidth()/3.5 && x < matrix.getWidth()/2 && y > matrix.getHeight()/3.5 && y < matrix.getHeight()- matrix.getWidth()/2) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();  
                     pixels[y * width + x] = matrix.get(x, y) ? 0x2691E  
                             : 0x6495ED; //�ı����Ͻ�4/1��ɫ
                 }
            	 else if ( x < matrix.getWidth()-140&&x >(matrix.getWidth()- matrix.getWidth()/2 ) && y > matrix.getHeight()/2 &&y <  (matrix.getHeight()-matrix.getHeight()/3.5)) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();
                     pixels[y * width + x] = matrix.get(x, y) ? 0x000000  
                             : 0x6495ED; //�ı����½�4/1��ɫ
                 }
            	 
            	 else if (x > matrix.getWidth()/2 && x < matrix.getWidth() && y > matrix.getHeight()/2 && y <  matrix.getHeight()) {  
                    Color color = new Color(231, 144, 56);  
                    int colorInt = color.getRGB();  
                    pixels[y * width + x] = matrix.get(x, y) ? 0xADFF2F  
                            : 0x6495ED; //�ı������Ͻ���ɫ
                }
                else if (x > 0 && x < matrix.getHeight()/2 && y > 0 && y <  matrix.getWidth()/2) {  
                    Color color = new Color(231, 144, 56);  
                    int colorInt = color.getRGB();  
                    pixels[y * width + x] = matrix.get(x, y) ? 0xFF69B4  
                            : 0x6495ED; //�ı����Ͻ���ɫ
                }
                // ��ȡͼƬ  
//                else if (x > halfW - IMAGE_HALF_WIDTH  
//                        && x < halfW + IMAGE_HALF_WIDTH  
//                        && y > halfH - IMAGE_HALF_WIDTH  
//                        && y < halfH + IMAGE_HALF_WIDTH) {  
//                    pixels[y * width + x] = srcPixels[x - halfW  
//                            + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];  //��ʾlogo
//                }  
                // ��ͼƬ�����γɱ߿�  
//                else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
//                        && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH  
//                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
//                        + IMAGE_HALF_WIDTH + FRAME_WIDTH)  
//                        || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH  
//                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
//                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
//                                + IMAGE_HALF_WIDTH + FRAME_WIDTH)  
//                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
//                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
//                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
//                                - IMAGE_HALF_WIDTH + FRAME_WIDTH)  
//                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH  
//                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH  
//                                && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH  
//                                + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {  
//                    pixels[y * width + x] = 0x0000FF;//����logo���  
//                }
            	else {  
                    // ��ά����ɫ��RGB��  
                    int num1 = (int) (50 - (50.0 - 13.0) / matrix.getHeight()  
                            * (y + 1));  
                    int num2 = (int) (165 - (165.0 - 72.0) / matrix.getHeight()  
                            * (y + 1));  
                    int num3 = (int) (162 - (162.0 - 107.0)  
                            / matrix.getHeight() * (y + 1));  
                    Color color = new Color(num1, num2, num3,255);
                    System.out.println(color);
                    int colorInt = color.getRGB();  
                    // �˴������޸Ķ�ά�����ɫ�����Էֱ��ƶ���ά��ͱ�������ɫ��  
                    pixels[y * width + x] = matrix.get(x, y) ? 0x9932CC
                            : 0x6495ED;// 0x000000:0xffffff  16777215 
                }  
            }  
        }  
  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        image.getRaster().setDataElements(0, 0, width, height, pixels);  
  
        return image;  
    }  
  
    /**  
     * �Ѵ����ԭʼͼ�񰴸߶ȺͿ�Ƚ������ţ����ɷ���Ҫ���ͼ��  
     *   
     * @param srcImageFile  
     *            Դ�ļ���ַ  
     * @param height  
     *            Ŀ��߶�  
     * @param width  
     *            Ŀ����  
     * @param hasFiller  
     *            ��������ʱ�Ƿ���Ҫ���ף�trueΪ����; falseΪ������;  
     * @throws IOException  
     */  
    private static BufferedImage scale(String srcImageFile, int height,  
            int width, boolean hasFiller) throws IOException {  
        double ratio = 0.0; // ���ű���  
        File file = new File(srcImageFile);  
        BufferedImage srcImage = ImageIO.read(file);  
        Image destImage = srcImage.getScaledInstance(width, height,  
                BufferedImage.SCALE_SMOOTH);  
        // �������  
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {  
            if (srcImage.getHeight() > srcImage.getWidth()) {  
                ratio = (new Integer(height)).doubleValue()  
                        / srcImage.getHeight();  
            } else {  
                ratio = (new Integer(width)).doubleValue()  
                        / srcImage.getWidth();  
            }  
            AffineTransformOp op = new AffineTransformOp(  
                    AffineTransform.getScaleInstance(ratio, ratio), null);  
            destImage = op.filter(srcImage, null);  
        }  
        if (hasFiller) {// ����  
            BufferedImage image = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics2D graphic = image.createGraphics();  
            graphic.setColor(Color.white);  
            graphic.fillRect(0, 0, width, height);  
            if (width == destImage.getWidth(null))  
                graphic.drawImage(destImage, 0,  
                        (height - destImage.getHeight(null)) / 2,  
                        destImage.getWidth(null), destImage.getHeight(null),  
                        Color.white, null);//��ͼƬ  
            else  
                graphic.drawImage(destImage,  
                        (width - destImage.getWidth(null)) / 2, 0,  
                        destImage.getWidth(null), destImage.getHeight(null),  
                        Color.white, null);  
            graphic.dispose();  
            destImage = image;  
        }  
        return (BufferedImage) destImage;  
    }
    public static void main(String[] args) throws UnsupportedEncodingException {  
        // ����Ϊ����(��֧������),��,��,logoͼ��·��,����·��  
        QRCode.encode("http://a.app.qq.com/o/simple.jsp?pkgname=com.font&fromcase=40003", 500, 500, "E:\\����\\6608.jpg","E:\\����\\���־.jpg");  
    }  
}  
