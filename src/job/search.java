package job;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import selrcdemo.Rcdemo;
import selrcdemo.WaitElement;
import job.SaveDatabase;

public class search {
	static Logger log =Logger.getLogger("job.search"); 
	static LinkedList<jobmsg> jobname = new LinkedList<jobmsg>();
	static LinkedList<String> urllist = new LinkedList<String>();
	public static void main(String[] args) throws InterruptedException, SQLException {
	int maxpage=110;
		jobmsg msg = new jobmsg();
		// TODO Auto-generated method stub
		//打开浏览器并基本设置
		WebDriver driver = Rcdemo.main();
		WebDriver condriver = Rcdemo.main();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.navigate().to("https://campus.dajie.com/progress/index");
		driver.manage().window().maximize();
		WaitElement.holdon(1);
		//关闭提示框
			if(WaitElement.isElementExsit(driver,By.xpath(" .//*[@id='J_loginDialog_school_1']/div/div/div/div[3]/span"))==true)
			driver.findElement(By.xpath(".//*[@id='J_loginDialog_school_1']/div/div/div/div[3]/span")).click();
		//登录网站 填写信息的方法,没有解决有时输验证码的问题
		driver.findElement(By.xpath(".//*[@id='J-header-login']")).click();
		driver.findElement(By.xpath(".//*[@id='J_loginName']")).sendKeys("2386312873@qq.com");
		driver.findElement(By.xpath(".//*[@id='J_loginDialogForm']/div[2]/input")).sendKeys("123456a");
		driver.findElement(By.xpath(".//*[@id='J_loginDialogForm']/div[4]/button")).click();
		//获得最大页码
		try{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		WebElement  max_page= driver.findElement(By.xpath(".//*[@id='jp-paging']/div/a[11]"));
		maxpage = Integer.parseInt(max_page.getText());
		WaitElement.holdon(1);
		}
		catch (Exception e){
		driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		WebElement  max_page= driver.findElement(By.xpath(".//*[@id='jp-paging']/div/a[11]"));
		maxpage = Integer.parseInt(max_page.getText());
		log.warn("获取页码 "+e.getMessage());
		}
	int i=1;
		while(i<=maxpage)
		{	
			i++;
			//页面向下滚动
		try{	
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
		driver.findElement(By.xpath(".//*[@id='jp-paging']/div"));
//		 driver.findElement(By.className("next")).click();
//  	   WaitElement.holdon(1);
  	   }
		catch(Exception e)
		{
//			driver.navigate().refresh();
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
//			 driver.findElement(By.className("next")).click();
//	    	   WaitElement.holdon(1);
			log.warn("页面全部加载"+e.getMessage());
		}
		
	//获得所有的工作名称并存入数据库
		WebElement jobpage = driver.findElement(By.id("jp-list-wrap"));
		List<WebElement> joburl = null;
		joburl = jobpage.findElements(By.tagName("a"));
        //获得职位的url
        for(WebElement s:joburl){  
        	try{
        	urllist.add(s.getAttribute("href"));        	}
        	catch(Exception e){
        		log.warn("获得信息 "+e.getMessage());
        	}
        	 
      }
        //获取内容
       while(urllist.size()>0)
        
        {
        	String page = urllist.pollFirst();
        	condriver.navigate().to(page);
        	//url
        	msg.setUrl(page);
        	//标题
        	try{
        	if(WaitElement.isElementExsit(condriver, By.xpath(".//*[@id='J_topHeader']/h2"))==true);
        	{msg.setJobname(condriver.findElement(By.xpath(".//*[@id='J_topHeader']/h2")).getText());
        	System.out.println(msg.getJobname());}}
        	catch(Exception e)
        	{
        		msg.setJobname(null);
        		log.warn("找不到标题  "+e.getMessage());
        	}
        	//内容
//        	try{
////        	if(WaitElement.isElementExsit(driver, By.className("pro-detail"))==true);
//        	String content = driver.findElement(By.className("pro-detail")).getText();
//        	msg.setContent(content);
//        	 WaitElement.holdon(1);
//        	}
//        	catch(Exception e)
//        	{
//        		msg.setContent(null);
//        		log.warn("找不到内容  "+e.getMessage());
//        	}
        	
        	 jobname.add(msg);
        	 
        	if (jobname.size() > 0) 
		{
			try {SaveDatabase.save(jobname);}
			catch (SQLException e) {
				  System.out.println("数据库"+e.getMessage());
			  } 
			jobname.clear();
        }
        
        }
        
       if(i!=maxpage)//点击下一页
       {
    	   ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
   		Thread.sleep(1000);
   		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
   		Thread.sleep(1000);
   		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)"); 
   		driver.findElement(By.xpath(".//*[@id='jp-paging']/div"));
   		 driver.findElement(By.className("next")).click();
     	   WaitElement.holdon(1);
       }
       
       }
		
		driver.close();
			
	}
}