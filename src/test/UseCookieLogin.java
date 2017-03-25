package test;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;

import selrcdemo.Rcdemo;

public class UseCookieLogin {

    /**
     * @author Young
     * @param args
     */
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//        Cookies.addCookies();
//        WindowsUtils.tryToKillByName("chrome.exe");
//        WindowsUtils.getProgramFilesPath();
        WebDriver driver=Rcdemo.main();
        driver.get("https://www.dajie.com/?isLogin=true");
        try 
        {
            File file=new File("broswer.data");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!= null)
            {
                StringTokenizer str=new StringTokenizer(line,";");
                while(str.hasMoreTokens())
                {
//                	System.out.println(str);
                    String name=str.nextToken(",");
                   System.out.println(name);
                     String value=str.nextToken(",");
                     System.out.println(value);
                    String domain=str.nextToken(",");
                    String path=str.nextToken(",");  
                    Date expiry=null;
//                    System.out.println(name);
//                    String dt;
//                    if(!(dt=str.nextToken()).equals(null))
//                    {
                    Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。   
                    cal.add(Calendar.DAY_OF_MONTH, +365);//取当前日期的前一天.   
                        expiry = cal.getTime();
                        
//                        System.out.println();
//                    }
                    boolean isSecure=new Boolean(str.nextToken(",")).booleanValue();
        
                  System.out.println(name+"="+value+","+domain+","+path+","+expiry+","+isSecure);
                    Cookie ck=new Cookie(name,value,domain,path,expiry,isSecure);
                    driver.manage().addCookie(ck);
                    break;
                }
            }
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        driver.get("https://www.dajie.com/home");
        
    }

}