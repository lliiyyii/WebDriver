package job;

import java.sql.Connection;
import java.sql.DriverManager;

public class SetDatabase {

	/**
	 * @param args
	 */

	// TODO Auto-generated method stub
	Connection conn = null;
	// 定义MySQL的数据库驱动程序
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
	// 定义MySQL数据库的连接地址
	public static final String DBURL = "jdbc:mysql://localhost:3306/mysql_test?useUnicode=true&characterEncoding=utf-8"; // 和本机mysql中名为t的数据库取得连接
	// MySQL数据库的连接用户名
	public static final String DBUSER = "root";
	// MySQL数据库的连接密码
	public static final String DBPASS = "root";

	public Connection getConnection(String DBURL,String DBUSER,String DBPASS) {
		try {
			// 加载驱动
			Class.forName(DBDRIVER).newInstance();
			// 取得连接
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库加载失敗");
		}
		return conn;
	}

}
