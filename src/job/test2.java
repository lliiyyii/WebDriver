package job;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import selrcdemo.Rcdemo;

public class test2 {
	public static void main(String[] args) throws InterruptedException {
		int max=40;
		// TODO Auto-generated method stub
		WebDriver driver = Rcdemo.main();

//		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//		driver.navigate().to("https://www.weibo.com");
//		driver.manage().window().maximize();
//		driver.findElement(By.xpath(".//*[@id='plc_frame']/div[1]")).sendKeys(Keys.DOWN);
//		
//		Actions action = new Actions(driver); 
//		
//	
//		action.sendKeys(Keys.DOWN);//用下箭头实现滚动条下拉
//		 Thread.sleep(2000);
////		 action.keyDown(Keys.DOWN)
//		driver.navigate().to("http://www.zhihu.com");
		 String baseUrl = "http://www.baidu.com/";
		   driver.get(baseUrl);
		   driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
		 
		   ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		   driver.switchTo().window(tabs.get(0)); //switches to new tab
		   driver.navigate().to("https://www.weibo.com");
		 Thread.sleep(100);
		   driver.switchTo().window(tabs.get(0)); // switch back to main screen        
		   driver.get("https://www.yahoo.com");
	
	}
}
