package UTILS;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/icons/fpt.png");
        return new ImageIcon(url).getImage();
    }
    public static void save(File src){
        File dst = new File("logos", src.getName());
        if (!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ImageIcon read(String fileName, Component com){
        File path = new File("logos", fileName);      
        return new ImageIcon(fitImage(path.getAbsolutePath(), com));
    }
    public static Image fitImage(String file, Component component){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(file));
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        Image image = img.getScaledInstance(component.getWidth(), component.getHeight(), Image.SCALE_SMOOTH);
        return image;
    }
    public static ImageIcon readQrCode(String fileName, Component com){
        File path = new File("qr Code", fileName);
        return new ImageIcon(fitImage(path.getAbsolutePath(), com));
    }
    public static String getQrCode(String path){
        try {
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                                                            new BufferedImageLuminanceSource(ImageIO.read(
                                                                new FileInputStream(path)))));

            Result result = new MultiFormatReader().decode(binaryBitmap);
            return result.getText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
