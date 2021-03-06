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
    // 图片宽度的一半  
    private static final int IMAGE_WIDTH = 100;  
    private static final int IMAGE_HEIGHT = 100;  
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;  
    private static final int FRAME_WIDTH = 2;  
    // 二维码写码器  
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();  
  
    public static void encode(String content, int width, int height,  
            String srcImagePath, String destImagePath) {  
        try {  
            //生成图片文件  
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
        // 读取源图像  
        BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH,  
                IMAGE_HEIGHT, true);  
        int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];  
        for (int i = 0; i < scaleImage.getWidth(); i++) {  
            for (int j = 0; j < scaleImage.getHeight(); j++) {  
                srcPixels[i][j] = scaleImage.getRGB(i, j);//图片的像素点  
            }  
        }  
       //编码  
        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        // 生成二维码  
        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,  
                width, height, hints);  
        // 二维矩阵转为一维像素数组  
        int halfW = matrix.getWidth() / 2;  
        int halfH = matrix.getHeight() / 2;  
        int[] pixels = new int[width * height];  
  
        for (int y = 0; y < matrix.getHeight(); y++) {  
            for (int x = 0; x < matrix.getWidth(); x++) {  
                // 左上角颜色,根据自己需要调整颜色范围和颜色  
            	 if (x > 0 && x <140 && y > 0 && y <  140) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();  
                     pixels[y * width + x] = matrix.get(x, y) ? 0x00CED1  
                             : 0x6495ED; //前景色：背景色改变左上角码眼颜色
                 }
            	 else if (x > matrix.getWidth()-140 && x <matrix.getWidth() && y > 0 && y <  140) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();  
                     pixels[y * width + x] = matrix.get(x, y) ? 0x00CED1  
                             : 0x6495ED; //改变右上角码眼颜色
                 }
            	 else if (x > 0 && x <140 && y > matrix.getHeight()-140 && y <  matrix.getHeight()) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();  
                     pixels[y * width + x] = matrix.get(x, y) ? 0x00CED1  
                             : 0x6495ED; //改变左下角码眼颜色
                 }
            	 else if (x > matrix.getWidth()/3.5 && x < matrix.getWidth()/2 && y > matrix.getHeight()/3.5 && y < matrix.getHeight()- matrix.getWidth()/2) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();  
                     pixels[y * width + x] = matrix.get(x, y) ? 0x2691E  
                             : 0x6495ED; //改变左上角4/1颜色
                 }
            	 else if ( x < matrix.getWidth()-140&&x >(matrix.getWidth()- matrix.getWidth()/2 ) && y > matrix.getHeight()/2 &&y <  (matrix.getHeight()-matrix.getHeight()/3.5)) {  
                     Color color = new Color(231, 144, 56);  
                     int colorInt = color.getRGB();
                     pixels[y * width + x] = matrix.get(x, y) ? 0x000000  
                             : 0x6495ED; //改变右下角4/1颜色
                 }
            	 
            	 else if (x > matrix.getWidth()/2 && x < matrix.getWidth() && y > matrix.getHeight()/2 && y <  matrix.getHeight()) {  
                    Color color = new Color(231, 144, 56);  
                    int colorInt = color.getRGB();  
                    pixels[y * width + x] = matrix.get(x, y) ? 0xADFF2F  
                            : 0x6495ED; //改变右下上角颜色
                }
                else if (x > 0 && x < matrix.getHeight()/2 && y > 0 && y <  matrix.getWidth()/2) {  
                    Color color = new Color(231, 144, 56);  
                    int colorInt = color.getRGB();  
                    pixels[y * width + x] = matrix.get(x, y) ? 0xFF69B4  
                            : 0x6495ED; //改变左上角颜色
                }
                // 读取图片  
//                else if (x > halfW - IMAGE_HALF_WIDTH  
//                        && x < halfW + IMAGE_HALF_WIDTH  
//                        && y > halfH - IMAGE_HALF_WIDTH  
//                        && y < halfH + IMAGE_HALF_WIDTH) {  
//                    pixels[y * width + x] = srcPixels[x - halfW  
//                            + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];  //显示logo
//                }  
                // 在图片四周形成边框  
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
//                    pixels[y * width + x] = 0x0000FF;//绘制logo外框  
//                }
            	else {  
                    // 二维码颜色（RGB）  
                    int num1 = (int) (50 - (50.0 - 13.0) / matrix.getHeight()  
                            * (y + 1));  
                    int num2 = (int) (165 - (165.0 - 72.0) / matrix.getHeight()  
                            * (y + 1));  
                    int num3 = (int) (162 - (162.0 - 107.0)  
                            / matrix.getHeight() * (y + 1));  
                    Color color = new Color(num1, num2, num3,255);
                    System.out.println(color);
                    int colorInt = color.getRGB();  
                    // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；  
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
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标  
     *   
     * @param srcImageFile  
     *            源文件地址  
     * @param height  
     *            目标高度  
     * @param width  
     *            目标宽度  
     * @param hasFiller  
     *            比例不对时是否需要补白：true为补白; false为不补白;  
     * @throws IOException  
     */  
    private static BufferedImage scale(String srcImageFile, int height,  
            int width, boolean hasFiller) throws IOException {  
        double ratio = 0.0; // 缩放比例  
        File file = new File(srcImageFile);  
        BufferedImage srcImage = ImageIO.read(file);  
        Image destImage = srcImage.getScaledInstance(width, height,  
                BufferedImage.SCALE_SMOOTH);  
        // 计算比例  
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
        if (hasFiller) {// 补白  
            BufferedImage image = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
            Graphics2D graphic = image.createGraphics();  
            graphic.setColor(Color.white);  
            graphic.fillRect(0, 0, width, height);  
            if (width == destImage.getWidth(null))  
                graphic.drawImage(destImage, 0,  
                        (height - destImage.getHeight(null)) / 2,  
                        destImage.getWidth(null), destImage.getHeight(null),  
                        Color.white, null);//画图片  
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
        // 依次为内容(不支持中文),宽,长,logo图标路径,储存路径  
        QRCode.encode("http://a.app.qq.com/o/simple.jsp?pkgname=com.font&fromcase=40003", 500, 500, "E:\\测试\\6608.jpg","E:\\测试\\许成志.jpg");  
    }  
}  
