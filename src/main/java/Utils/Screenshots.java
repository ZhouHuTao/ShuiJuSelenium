package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/27 9:26
 */
public class Screenshots {
    public static File captureElement(WebElement element) throws Exception{
        WrapsDriver wrapsDriver = (WrapsDriver) element;
        //截图整个页面
        File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
        BufferedImage img = ImageIO.read(screen);
        //获得元素宽度、高度
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
        //使用上面的宽度和高度创建一个矩形
        Rectangle rect = new Rectangle(width,height);
        //得到元素的坐标
        org.openqa.selenium.Point p = element.getLocation();
        BufferedImage dest = img.getSubimage(p.getX(),p.getY(),rect.width,rect.height);
        //存为jpg格式
        ImageIO.write(dest,"png",screen);
        return screen;
    }
}
