package Utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/27 9:33
 */
public class GetVcode {
    public String vcode(File imageFile) throws IOException, TesseractException {
        BufferedImage img = ImageIO.read(imageFile);
        // 这里对图片黑白处理,增强识别率.这里先通过截图,截取图片中需要识别的部分
        img = ImageHelper.convertImageToGrayscale(img);

        ITesseract ins = new Tesseract();
        ins.setTessVariable("user_defined_dpi", "300");
        ins.setDatapath("src/main/resources/tessdata-master");

        String code = ins.doOCR(img);
        //存在识别J识别成），需转换
        code = code.replaceAll("\\)", "j");
        return code;
    }
}
