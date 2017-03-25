package selrcdemo;
import org.openqa.selenium.*;
import java.util.NoSuchElementException;
import org.apache.log4j.Logger;
public class WaitElement {
	static Logger log =Logger.getLogger("dajie"); 
	// 刷新判断
	public static boolean waitPageRefresh(WebElement trigger) {
		int refreshTime = 0;
		boolean isRefresh = false;
		try {
			for (int i = 1; i < 60; i++) {
				refreshTime = i;
				trigger.getTagName();
				Thread.sleep(1000);
			}
		} catch (StaleElementReferenceException e) {
			isRefresh = true;
			log.warn("Page refresh time is:" + refreshTime + " seconds!");
			return isRefresh;
		} catch (WebDriverException e) {
			log.warn(e.getMessage());
		} catch (InterruptedException e) {
			log.warn(e.getMessage());
		}
		log.warn("Page didnt refresh in 60 seconds!");
		return isRefresh;
	}

	// 等待函数
	public static void holdon(int second) {
		try {
			Thread.sleep((second * 1000));
		} catch (InterruptedException e1) {
			log.warn(e1.getMessage());
		}
	}

	// 判断元素是否存在
	public static boolean isElementExsit(WebDriver driver, By locator) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			flag = null != element;
		} catch (NoSuchElementException e) {
			log.warn("Element:" + locator.toString() + " is not exsit!");
		}
		return flag;
	}
}
