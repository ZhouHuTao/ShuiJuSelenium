package Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author ZhouHuTao
 * @version 1.0
 * @description
 * @date 2021/1/27 14:31
 */
public class GetPcode {
    public String code() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        //打开浏览器
        WebDriver driver = new ChromeDriver();

        //登录系统
        driver.get("http://192.168.2.200/login_en.html");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id=\"accountID\"]")).sendKeys("root");
        driver.findElement(By.xpath("//*[@id=\"passwordID\"]")).sendKeys("kungeek2015");
        driver.findElement(By.xpath("//*[@id=\"btnLoginID\"]")).click();
        Thread.sleep(2000);
        driver.get("http://192.168.2.200/goip_sms_inbox_en.html");
        String code = driver.findElement(By.xpath("//*[@id=\"ID_TabCmdResp\"]/tbody/tr[9]/td[5]/textarea")).getText();
        driver.close();
        code = code.substring(14,20);
        return code;
    }
}
