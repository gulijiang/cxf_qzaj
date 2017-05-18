package com.qzaj.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


public class Common {
	
	public static void write(String str){
		try {
			str=str.replaceAll("null", "-");
		HttpServletResponse response = ServletActionContext.getResponse();  
	    response.setDateHeader("Expires", 0);  
	    response.addHeader("Pragma", "no-cache");  
	    response.setHeader("Cache-Control", "no-cache");  
	    response.setContentType("text/plain;charset=UTF-8");  
	    response.getWriter().write(str);  
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	/**
	 * BigDecimal 转成int 获取数量
	 * @param o，是个BigDecimal的类
	 * @return
	 */
	public static int getCount(Object o){
		return ((BigDecimal)o).intValue();
	}
	
	public static List<HashMap<String, Object>> getOracleJdbc(String sql){
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	     Class.forName("oracle.jdbc.driver.OracleDriver");//实例化oracle数据库驱动程序(建立中间件)
	     String url = "jdbc:oracle:thin:@192.168.11.101:1521:orcl";//@localhost为服务器名，sjzwish为数据库实例名
	     conn = DriverManager.getConnection(url, "qzaj", "qzaj");//连接数据库，a代表帐户,a代表密码
	     stmt = conn.createStatement();//提交sql语句,创建一个Statement对象来将SQL语句发送到数据库
	    
	     rs = stmt.executeQuery(sql);//执行查询,(ruby)为表名
	     return  ResultSetTool.result2List(rs);
	    
	    } catch (Exception e) {
	     e.printStackTrace();
	    } finally{
	     try {
	      //关闭数据库，结束进程
	      rs.close();
	      stmt.close();
	      conn.close();
	     } catch (SQLException e) {
	      e.printStackTrace();
	     }
	    }
		return null;
	}
	public static List<HashMap<String, Object>> getMysqlJdbc(String sql){
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	     Class.forName("com.mysql.jdbc.Driver");//实例化oracle数据库驱动程序(建立中间件)
	     String url = "jdbc:mysql://127.0.0.1:3306/qzaj";//@localhost为服务器名，sjzwish为数据库实例名
	     conn = DriverManager.getConnection(url, "root", "123");//连接数据库，a代表帐户,a代表密码
	     stmt = conn.createStatement();//提交sql语句,创建一个Statement对象来将SQL语句发送到数据库
	    
	     //查询数据用executeQuery
	     rs = stmt.executeQuery(sql);//执行查询,(ruby)为表名
	     return  ResultSetTool.result2List(rs);
	    
	    } catch (Exception e) {
	     e.printStackTrace();
	    } finally{
	     try {
	      //关闭数据库，结束进程
	      rs.close();
	      stmt.close();
	      conn.close();
	     } catch (SQLException e) {
	      e.printStackTrace();
	     }
	    }
		return null;
	}
	
	public static List<HashMap<String, Object>> getJdbc(String sqltype,String ip,String database,String username,String password,String sql){
		Connection conn = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	    	
	    	if(sqltype.equals("mysql")){
	    		 Class.forName("com.mysql.jdbc.Driver");//实例化oracle数据库驱动程序(建立中间件)
	    	     String url = "jdbc:mysql://"+ip+":3306/"+database;//@localhost为服务器名，sjzwish为数据库实例名
	    	     conn = DriverManager.getConnection(url, username, password);//连接数据库，a代表帐户,a代表密码
	    	}else if(sqltype.equals("oracle")){
	    		 Class.forName("oracle.jdbc.driver.OracleDriver");//实例化oracle数据库驱动程序(建立中间件)
	    	     String url = "jdbc:oracle:thin:@"+ip+":1521:"+database;//@localhost为服务器名，sjzwish为数据库实例名
	    	     conn = DriverManager.getConnection(url, username, password);//连接数据库，a代表帐户,a代表密码
	    	}
	    
	     stmt = conn.createStatement();//提交sql语句,创建一个Statement对象来将SQL语句发送到数据库
	    
	     //查询数据用executeQuery
	     rs = stmt.executeQuery(sql);//执行查询,(ruby)为表名
	     return  ResultSetTool.result2List(rs);
	    
	    } catch (Exception e) {
	     e.printStackTrace();
	    } finally{
	     try {
	      //关闭数据库，结束进程
	      rs.close();
	      stmt.close();
	      conn.close();
	     } catch (SQLException e) {
	      e.printStackTrace();
	     }
	    }
		return null;
	}
}
