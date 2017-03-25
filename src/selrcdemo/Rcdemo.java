package selrcdemo;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Rcdemo {
	public static WebDriver main() {
		System.setProperty("webdriver.firefox.bin","./Mozilla Firefox/firefox.exe");

		String pro_Path = System.getProperty("user.dir");

		File file = new File(".\\firebug@software.joehewitt.com.xpi");
		 File file2 = new File(".\\FireXPath@pierre.tholence.com.xpi");

		 FirefoxProfile profile = new FirefoxProfile();
		
		 try {
			 profile.addExtension(file);
			 profile.addExtension(file2);
			  //profile.setPreference("network.proxy.type", 1);
			 // profile.setPreference("network.proxy.http", "192.168.11.254");
			 // profile.setPreference("network.proxy.http_port", 8080);
			 // profile.setPreference("network.proxy.share_proxy_settings", true);
			 // profile.setPreference("network.proxy.no_proxies_on", "localhost");
			 //profile.addExtension(file3);
			 profile.setPreference("network.websocket.auto-follow-http-redirects", true);			 
			 profile.setPreference("network.http.redirection-limit", 100);
			 profile.setPreference("services.sync.prefs.sync.accessibility.blockautorefresh", false);
			 profile.setPreference("accessibility.blockautorefresh", false);
			 profile.setPreference("network.websocket.auto-follow-http-redirects", true);
			 profile.setPreference("network.http.prompt-temp-redirect", false);
		 } catch (IOException e) {
			
		 }
		 profile.setPreference("extensions.firebug.allPagesActivation", "on");
		 WebDriver driver = new FirefoxDriver(profile);
		 return driver;
		
	}
}
