package Selenium;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/26 14:54
 */
public class FjSj {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        //打开浏览器
        WebDriver driver = new ChromeDriver();

        //登录系统
        driver.get("https://etax.fujian.chinatax.gov.cn/xxmh/html/index.html");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"qyLoginForm\"]/ul/li[1]/input")).sendKeys("91350504MA2YDG2C7J");
        driver.findElement(By.xpath("//*[@id=\"qyLoginForm\"]/ul/li[2]/input")).sendKeys("SJYX12sjyx12");

        Boolean flag = true;
        String result = null;
        while (flag){
            WebElement element = driver.findElement(By.xpath("//*[@id=\"yzmImg2\"]"));
            try{
                FileUtils.copyFile(captureElement(element),new File("E://NbJt/1.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(5000);
            File imageFile = new File("E://NbJt/1.png");
            ITesseract ins = new Tesseract();
            ins.setTessVariable("user_defined_dpi", "300");
            try {
                ins.setDatapath("E://NbJt/tessdata-master");
                result = ins.doOCR(imageFile);
                System.out.println(result);
            } catch (TesseractException e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"captchCode2\"]")).sendKeys(result);
            driver.findElement(By.xpath("//*[@id=\"yhmLogin\"]")).click();
            //如果验证码正确，跳出
            try{
                Thread.sleep(4000);
                driver.findElement(By.xpath("/html/body/div[17]/span/a")).click();
                flag = false;
            }catch (Exception e){
                e.printStackTrace();
                flag = false;
            }
        }

        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"jrbsryxzBtn\"]")).click();
        driver.findElement(By.xpath("//li[contains(text(),\"4、洪亚婷--办税员--158****9319\")]")).click();
        driver.findElement(By.xpath("//*[@id=\"jrbsBtn\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"sendSmgrCheckCode2\"]")).click();

    }

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
        Point p = element.getLocation();
        BufferedImage dest = img.getSubimage(p.getX(),p.getY(),rect.width,rect.height);
        //存为jpg格式
        ImageIO.write(dest,"png",screen);
        return screen;
    }
}
