package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.DriverFactory;

import selrcdemo.Rcdemo;

import org.openqa.selenium.Cookie;

public class Cookies {
    /**
     * @author Young
     * 
     */
	//先登录，保存cookie
    public static void addCookies() {

        WebDriver driver = Rcdemo.main();
        driver.get("https://www.dajie.com/?isLogin=true");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement user = driver
                .findElement(By.xpath(".//*[@id='J_loginName']"));
        user.clear();
        user.sendKeys("2386312873@qq.com");
        WebElement password = driver.findElement(By
                .xpath(".//*[@id='J_signinForm']/div[2]/input"));
        password.clear();
        password.sendKeys("123456a");

        WebElement submit = driver.findElement(By
                .xpath(".//*[@id='J_signinForm']/div[4]/button"));
        submit.submit();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        File file = new File("broswer.data");
        try {
            // delete file if exists
            file.delete();
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Cookie ck : driver.manage().getCookies()) {
                bw.write(ck.getName() + "," + ck.getValue() + ","
                        + ck.getDomain() +","+ ck.getPath() + ","
                        + ck.getExpiry() +"," + ck.isSecure()+";");
                bw.newLine();
            }
            bw.flush();
            bw.close();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("cookie write to file");
        }
    }
}