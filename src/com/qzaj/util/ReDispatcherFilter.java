package com.qzaj.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;


public class ReDispatcherFilter extends StrutsPrepareAndExecuteFilter {

	    public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {  
	        HttpServletRequest request = (HttpServletRequest) req;  
	        //判断是webservice还是struts
	        if (request.getRequestURI().contains("/ws")) {  
	            chain.doFilter(req, res);  
	        }else{
//	    		TbAuthUser user=(TbAuthUser) request.getSession().getAttribute("user");
//	    		if(user!=null){
//	    			TbAuthLog log=new TbAuthLog();
//	    			log.setUserid(user.getUserid());
//		    		log.setLoginname(user.getLoginname());
//		    		log.setUuid(new Uuid().toString()+"1111");
//		    		log.setLoginip(request.getRemoteAddr());
//		    		log.setUrl(request.getRequestURI());
//		    		log.setCreatetime(new Date());
//		    		ApplicationContext ac = new FileSystemXmlApplicationContext("F:/Workspaces/.metadata/.me_tcat/webapps/qzproject/WEB-INF/applicationContext.xml");   
//		    		Service service=(Service) ac.getBean("service"); 
//		    		service.setEntity(TbAuthLog.class);
//		    		service.save(log);
//	    		}
	            super.doFilter(request, res, chain); 
	        }  
	    }  

}
