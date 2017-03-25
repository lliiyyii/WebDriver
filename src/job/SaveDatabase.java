package job;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;



public class SaveDatabase {
	/**
	 * @param args
	 * @throws SQLException
	 */
	public static void save(LinkedList<jobmsg> jobname) throws SQLException  {
		Connection conn = new SetDatabase().getConnection("jdbc:mysql://localhost:3306/mysql_test?useUnicode=true&characterEncoding=utf-8","root","root");
		try {
		for(jobmsg job:jobname){
			 
		String sql = "insert into jobsql(name,url,content) values('"+job.getJobname()+"','"+job.getUrl()+"','"+job.getContent()+"')";
//		String sql = "insert into mysql(title,time,content,url) values('1','1','1','1')";
//		String sql2 ="select * from TalentHire";
		java.sql.Statement sm;
		
			sm = conn.createStatement();
			sm.execute(sql);
//			sm.execute("DELETE FROM mysql;");
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally
		{	
			if(conn!= null) 
			 conn.close(); 
		}

	
	}
}
