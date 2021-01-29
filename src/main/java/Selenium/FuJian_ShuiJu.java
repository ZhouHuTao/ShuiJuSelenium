package Selenium;

import Utils.GetPcode;
import Utils.GetVcode;
import Utils.Screenshots;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/27 11:57
 */
public class FuJian_ShuiJu {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        //打开浏览器
        WebDriver driver = new ChromeDriver();

        //登录系统
        driver.get("https://etax.fujian.chinatax.gov.cn/xxmh/html/index.html");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"qyLoginForm\"]/ul/li[1]/input")).sendKeys("913505036603731879");
        driver.findElement(By.xpath("//*[@id=\"qyLoginForm\"]/ul/li[2]/input")).sendKeys("xyKZ31879");

        boolean flag = true;
        while (flag){
            //获取页面验证码图片
            WebElement element = driver.findElement(By.xpath("//*[@id=\"yzmImg2\"]"));
            //截取验证码图片
            File imageFile = new File("src/main/resources/image/vcode.png");

            FileUtils.copyFile(Screenshots.captureElement(element),imageFile);
            //获取验证码
            //获取为空的情况点击图片重新获取，控制在30次
            String vcode = "";
            for(int i=0;i<30;i++){
                vcode = new GetVcode().vcode(imageFile);
               if("".equals(vcode)){
                   driver.findElement(By.xpath("//*[@id=\"yzmImg2\"]")).click();
                   //获取页面验证码图片
                   element = driver.findElement(By.xpath("//*[@id=\"yzmImg2\"]"));
                   //截取验证码图片
                   imageFile = new File("src/main/resources/image/vcode.png");
                   FileUtils.copyFile(Screenshots.captureElement(element),imageFile);
                   Thread.sleep(200);
               }else {
                   i = 30;
               }
            }


            //填写验证码
            driver.findElement(By.xpath("//*[@id=\"captchCode2\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"captchCode2\"]")).sendKeys(vcode);
            String dl = "document.evaluate('//*[@id=\"yhmLogin\"]', document).iterateNext().click()";
            ((JavascriptExecutor) driver).executeScript(dl);
            //如果验证码正确，跳出
            try{
                Thread.sleep(500);
                String js = "document.evaluate('/html/body/div[17]/div[3]/a', document).iterateNext().click()";
                ((JavascriptExecutor) driver).executeScript(js);
                driver.findElement(By.xpath("//*[@id=\"yzmImg2\"]")).click();
            }catch (Exception e){
                flag = false;
            }
        }
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"jrbsryxzBtn\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[contains(text(),\"洪亚婷--办税员--158****9319\")]")).click();
        driver.findElement(By.xpath("//*[@id=\"jrbsBtn\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"sendSmgrCheckCode2\"]")).click();
        Thread.sleep(2000);
        String dl = "document.evaluate('/html/body/div[17]/div[3]/a', document).iterateNext().click()";
        ((JavascriptExecutor) driver).executeScript(dl);
        //获取手机验证码
        Thread.sleep(1000);
        String pcode = new GetPcode().code();
        driver.findElement(By.xpath("//*[@id=\"loginYzm2\"]")).sendKeys(pcode);
        driver.findElement(By.xpath("//*[@id=\"checkSmgrCode2\"]")).click();
        //进入按期应申报页面
        driver.get("https://etax.fujian.chinatax.gov.cn/xxmh/html/index_origin.html?gopage=true&url=/xxmh/html/" +
                "gnjsz.html%3FcdId%3D2%26amp%3BgnDm%3Dgndm-2%26amp%3Bqxkzsx%3D2%26amp%3Bydur_50%3Db0f9806eab35a8" +
                "37fb458e291f44f12d_0538ab86ba1f02305da016a380a05949_nsr_false%26amp%3BtabTitle%3Dundefined&m1=sbjs&m2=&fromWhere=&qxkzsx=2#none");
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/table/tbody/tr/td[1]/div/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"mainContent\"]/table/tbody/tr/td[1]/div/ul/li[2]/ul/li[1]/a")).click();
        Thread.sleep(2000);
        File srcFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File("src/main/resources/image/913505036603731879.jpg"));
        driver.close();
    }
}
