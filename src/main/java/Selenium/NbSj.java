package Selenium;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/22 17:24
 */
public class NbSj {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        //打开浏览器
        WebDriver driver = new ChromeDriver();

        //登录系统
        driver.get("https://etax.ningbo.chinatax.gov.cn/nbdzswj-web/apps/views/beforeLogin/indexBefore/pageIndex.html#/?_t=1611307512306");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("(//*[@id=\"0\"])[2]")).click();
        driver.findElement(By.xpath("//div[2]/div[2]/div[1]/div[2]/a/div")).click();
        driver.findElement(By.xpath("//*[@id=\"userName\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"userName\"]")).sendKeys("91330201MA2GQK191T");
        driver.findElement(By.xpath("//*[@id=\"passWord\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"passWord\"]")).sendKeys("Ct624411");
        driver.findElement(By.xpath("//*[@id=\"sjhm\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"sjhm\"]")).sendKeys("18892615983");
        driver.findElement(By.xpath("//*[@id=\"code\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"code\"]")).sendKeys("566981");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div/section/section/div/section/div[2]/section/button")).click();

        driver.get("https://etax.ningbo.chinatax.gov.cn/sbzx-web/apps/views/sbjgcx/sbjgcx.html?id=150&code=150&_=1611309862709");
        File srcFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(srcFile,new File("E://NbJt/sbjg.jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
