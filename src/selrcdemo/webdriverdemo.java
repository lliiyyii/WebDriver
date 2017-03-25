package selrcdemo;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;



public class webdriverdemo {
	public static void main(String[] args) throws InterruptedException, IOException
	  {
	WebDriver driver = Rcdemo.main();

	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	driver.navigate().to("http://baidu.com");
	//自动截屏
	File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE) ;
	FileUtils.copyFile(screenshot, new File("D:screenshotsscreenshots1.jpg"));
	
	driver.close();
}
}
