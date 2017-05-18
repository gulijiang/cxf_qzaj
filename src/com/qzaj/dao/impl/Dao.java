package com.qzaj.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.qzaj.dao.IDao;
import com.qzaj.util.ResultSetTool;

@Component("dao")
@SuppressWarnings("all")
public  class Dao  implements IDao{
	private Class oClass;
    private HibernateTemplate hibernateTemplate;
    private SessionFactory sessionFactory;
    private HibernateTemplate hibernateTemplate1;
    private SessionFactory sessionFactory1;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
    @Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public HibernateTemplate getHibernateTemplate1() {
		return hibernateTemplate1;
	}
	@Resource
	public void setHibernateTemplate1(HibernateTemplate hibernateTemplate1) {
		this.hibernateTemplate1 = hibernateTemplate1;
	}
	public SessionFactory getSessionFactory1() {
		return sessionFactory1;
	}
	@Resource
	public void setSessionFactory1(SessionFactory sessionFactory1) {
		this.sessionFactory1 = sessionFactory1;
	}
	
	public void setHibernate(){
		hibernateTemplate=hibernateTemplate1;
		sessionFactory=sessionFactory1;
	}
	
	public Class getoClass() {
		return oClass;
	}
	public void setoClass(Class oClass) {
		this.oClass = oClass; 
	} 
	public Object save(Object object) { 
		return hibernateTemplate.merge(object);
    }
	public boolean saveSome(List list) {
		SessionFactory sf = hibernateTemplate.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			for (Object object : list) {
				save(object);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			session.clear();
			session.close();
			sf.close();
		}
		return true;
	}
    public List findAll() {
        List result = hibernateTemplate.find("FROM " +oClass.getSimpleName() );
        System.out.println(result.size());
        if (result == null || result.size() == 0)
            return new ArrayList<Object>(0);
        return result;
    }

   
	public Object findById(String id) {
        return (Object) hibernateTemplate.get(oClass.getSimpleName(), id);
    }
	

    public boolean delete(Object object) {
            hibernateTemplate.delete(object);
            return true;
    }
    public boolean deleteByIds(String ids) throws Exception {
        	String[] id=ids.split(",");
        	ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(oClass);
        	String entity = meta.getEntityName();
        	//主键名称   
        	String pkName = meta.getIdentifierPropertyName();  
	        String sql="";
	        if(id.length==1){
	        	if(!ids.contains("'")){
	        		ids="'"+ids+"'";
	        	}
	        	sql="delete  from "+oClass.getSimpleName()+" t where t."+pkName+" ="+ids;
	        }
	        else{
	        	sql="delete  from "+oClass.getSimpleName()+" t where t."+pkName+" in ( "+ids+")";
	        } 
	        System.out.println(sql);
	        hibernateTemplate.bulkUpdate(sql);
         return true;
        	
    }
    public void delete(HashMap<String,Object> map)  {
    	ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(oClass);
    	 String sql="delete  from "+oClass.getSimpleName()+" t where 1=1";
    	if(map!=null&&!map.isEmpty()){
        	Set<String> set=map.keySet();
        	for (String str : set) {
        		if(map.get(str)!=null&&!"".equals(map.get(str))){
        			if(str.contains("_in")){
        				sql+=" and t." +str.split("_")[0]+" in (" +map.get(str)+") ";
        			}else{
        			sql+=" and t." +str+"='" +map.get(str)+"'";
        			}
        		}
        	}
        }
    	
        hibernateTemplate.bulkUpdate(sql);
    	
}
    public List getListByProperty(HashMap<String,Object> map,HashMap<String,Object> orderMap,HashMap<String,Object> pageMap) {
        String queryStr = "FROM "+oClass.getSimpleName()+" o where 1=1 ";
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        if(map!=null&&!map.isEmpty()){
        	Set<String> set=map.keySet();
        	for (String str : set) {
        		if(map.get(str)!=null&&!"".equals(map.get(str))){
        			String s[]=str.split("_");
        			if(str.contains("_date")){
        				if("date1".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')>='"+map.get(str)+"'";
        				}
        				if("date2".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')<='"+map.get(str)+"'";
        				}
        				if("dateequals".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')='"+map.get(str)+"'";
        				}
        			}else if(str.contains("_like")){
        				if("like".equals(s[1])){
        					queryStr+=" and o."+s[0]+" like '%"+map.get(str)+"%'";
        				}
        			}else if(str.contains("_in")){
        				if("in".equals(s[1])){
        					queryStr+=" and o."+s[0]+" in ("+map.get(str)+")";
        				}
        			}else if(str.contains("_notin")){
        				if("notin".equals(s[1])){
        					queryStr+=" and o."+s[0]+"not in ("+map.get(str)+")";
        				}
        			}else if(str.contains("_null")){
        				if("null".equals(s[1])){
        					queryStr+=" and (o."+s[0]+" is "+map.get(str)+" or o."+s[0]+"='')";
        				}else if("not null".equals(s[1])){
        					queryStr+=" and (o."+s[0]+" is "+map.get(str)+" and o."+s[0]+"<>'')";
        				}
        			}else if(str.contains("_notequals")){
        				queryStr+=" and (o."+s[0]+" <> '"+map.get(str)+"' or o."+s[0]+" is null)";
        			}else if(str.contains("_yj")){
        				queryStr+=" and "+map.get(str)+"";
        			}else{
        				queryStr+=" and o."+s[0]+"='"+map.get(str)+"'";
        			}
        		}
			}
        }
        
        if(orderMap!=null&&!orderMap.isEmpty()){
        	Set<String> set=orderMap.keySet();
        	String s="";
        	for (String str : set) {
        		if(orderMap.get(str)!=null&&!"".equals(orderMap.get(str))){
        			s+="  o."+str+"  "+orderMap.get(str)+",";
        		}
        	}
        	if(!"".equals(s)){
        		queryStr+=" order by"+s.substring(0, s.length()-1);
        	}
        }
        System.out.println(queryStr);
        if(pageMap==null){
        	 return hibernateTemplate.find(queryStr);
        }else{
        	Session session = hibernateTemplate.getSessionFactory().openSession();
			Query query = session.createQuery(queryStr);
			query.setReadOnly(true);
			query.setFirstResult((Integer) pageMap.get("start"));
			query.setMaxResults((Integer) pageMap.get("end"));
			session.clear();
			List list = query.list();
			//千万别忘记关闭会话，否则数据库在执行一定操作后，将无法连接;
			session.close();
			return list;
        }
    }
    
    
    public Object getObjectByProperty(HashMap<String,Object> map) {
        String queryStr = "FROM "+oClass.getSimpleName()+" o where 1=1 ";
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        if(map!=null&&!map.isEmpty()){
        	Set<String> set=map.keySet();
        	for (String str : set) {
        		if(map.get(str)!=null&&!"".equals(map.get(str))){
        			String s[]=str.split("_");
        			if(str.contains("_date")){
        				if("date1".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')>='"+map.get(str)+"'";
        				}
        				if("date2".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')<='"+map.get(str)+"'";
        				}
        				if("dateequals".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')='"+map.get(str)+"'";
        				}
        			}else if(str.contains("_like")){
        				if("like".equals(s[1])){
        					queryStr+=" and o."+s[0]+" like '%"+map.get(str)+"%'";
        				}
        			}else if(str.contains("_in")){
        				if("in".equals(s[1])){
        					queryStr+=" and o."+s[0]+" in ("+map.get(str)+")";
        				}
        			}else if(str.contains("_notin")){
        				if("notin".equals(s[1])){
        					queryStr+=" and o."+s[0]+"not in ("+map.get(str)+")";
        				}
        			}else if(str.contains("_null")){
        				if("null".equals(s[1])){
        					queryStr+=" and (o."+s[0]+" is "+map.get(str)+" or o."+s[0]+"='')";
        				}else if("not null".equals(s[1])){
        					queryStr+=" and (o."+s[0]+" is "+map.get(str)+" and o."+s[0]+"<>'')";
        				}
        			}else if(str.contains("_notequals")){
        				queryStr+=" and (o."+s[0]+" <> '"+map.get(str)+"' or o."+s[0]+" is null)";
        			}else if(str.contains("_yj")){
        				queryStr+=" and "+map.get(str)+"";
        			}else{
        				queryStr+=" and o."+s[0]+"='"+map.get(str)+"'";
        			}
        		}
			}
        }
        
        List list= hibernateTemplate.find(queryStr);
        if(list!=null&&!list.isEmpty()){
        	return list.get(0);
        }else{
        	return null;
        }
    }
    
    public List getListBySql(String queryStr) throws Exception{
    	Class oC=oClass;
    	Session session=hibernateTemplate.getSessionFactory().openSession();
    	Connection con=session.connection();
        Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
        ResultSet rs=stm.executeQuery(queryStr);
        List list= ResultSetTool.result2Bean(rs, oC);
        if(rs!=null){
        	rs.close();
        }
        if(stm!=null){
        	stm.close();
        }
        if(con!=null){
        	con.close();
        }
        if(session!=null){
        	session.close();
        }
        return list;
    }
    public HashMap<String, Object> getHashMapBySql(String queryStr) throws Exception{
    	Class oC=oClass;
    	Session session=hibernateTemplate.getSessionFactory().openSession();
    	Connection con=session.connection();
        Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
        ResultSet rs=stm.executeQuery(queryStr);
        HashMap<String, Object> map= ResultSetTool.result2map(rs);
        if(rs!=null){
        	rs.close();
        }
        if(stm!=null){
        	stm.close();
        }
        if(con!=null){
        	con.close();
        }
        if(session!=null){
        	session.close();
        }
        return map;
    }
    public List<HashMap<String, Object>> getResultSetBySql(String queryStr) throws Exception{
    	Class oC=oClass;
    	Session session=hibernateTemplate.getSessionFactory().openSession();
    	Connection con=session.connection();
    	Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE );
    	ResultSet rs=stm.executeQuery(queryStr);
    	List<HashMap<String, Object>> list= ResultSetTool.result2List(rs);
    	if(rs!=null){
        	rs.close();
        }
        if(stm!=null){
        	stm.close();
        }
        if(con!=null){
        	con.close();
        }
        if(session!=null){
        	session.close();
        }
    	return list;
    }

	public void saveListAfterDelete(HashMap<String,Object> map,List list){
		SessionFactory sf = hibernateTemplate.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(oClass);
    	String sql="delete  from "+oClass.getSimpleName()+" t where 1=1";
    	if(map!=null&&!map.isEmpty()){
        	Set<String> set=map.keySet();
        	for (String str : set) {
        		if(map.get(str)!=null&&!"".equals(map.get(str))){
         sql+=" and t." +str+"='" +map.get(str)+"'";
        		}
        	}
        }
        
        try {
        	hibernateTemplate.bulkUpdate(sql);
			for (Object object : list) {
				hibernateTemplate.save(object);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.clear();
			session.close();
			sf.close();
		}
	}
	public void excutesql(HashMap<String,Object> map,String id){
		ClassMetadata meta = hibernateTemplate.getSessionFactory().getClassMetadata(oClass);
   	 String sql="update "+oClass.getSimpleName()+" t";
   	if(map!=null&&!map.isEmpty()){
   		sql +=" set ";
       	Set<String> set=map.keySet();
       	for (String str : set) {
       		if(map.get(str)!=null&&!"".equals(map.get(str))){
       			sql+=" t." +str+"='" +map.get(str)+"' ,";
       		}
       	}
       }
   	sql = sql.substring(0, sql.length()-1);
   	sql += "where t."+meta.getIdentifierPropertyName()+" in ("+id+")"; 
   	System.out.println(sql);
       hibernateTemplate.bulkUpdate(sql);
	}
	public void excutesql(String sql){
		try {
			hibernateTemplate.bulkUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
       
	}
	public List getListByPropertyForMysql(HashMap<String,Object> map,HashMap<String,Object> orderMap,HashMap<String,Object> pageMap) {
        String queryStr = "FROM "+oClass.getSimpleName()+" o where 1=1 ";
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        if(map!=null&&!map.isEmpty()){
        	Set<String> set=map.keySet();
        	for (String str : set) {
        		if(map.get(str)!=null&&!"".equals(map.get(str))){
        			String s[]=str.split("_");
        			if(str.contains("_date")){
        				if("date1".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')>='"+map.get(str)+"'";
        				}
        				if("date2".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')<='"+map.get(str)+"'";
        				}
        				if("dateequals".equals(s[1])){
        					queryStr+=" and to_char(o."+s[0]+",'yyyy-MM-dd')='"+map.get(str)+"'";
        				}
        			}else if(str.contains("_like")){
        				if("like".equals(s[1])){
        					queryStr+=" and o."+s[0]+" like '%"+map.get(str)+"%'";
        				}
        			}else if(str.contains("_in")){
        				if("in".equals(s[1])){
        					queryStr+=" and o."+s[0]+" in ("+map.get(str)+")";
        				}
        			}else if(str.contains("_notin")){
        				if("notin".equals(s[1])){
        					queryStr+=" and o."+s[0]+" not in ("+map.get(str)+")";
        				}
        			}else if(str.contains("_null")){
        				if("null".equals(s[1])){
        					queryStr+=" and (o."+s[0]+" is "+map.get(str)+" or o."+s[0]+"='')";
        				}else if("not null".equals(s[1])){
        					queryStr+=" and (o."+s[0]+" is "+map.get(str)+" and o."+s[0]+"<>'')";
        				}
        			}else if(str.contains("_notequals")){
        				queryStr+=" and (o."+s[0]+" <> '"+map.get(str)+"' or o."+s[0]+" is null)";
        			}else if(str.contains("_yj")){
        				queryStr+=" and "+map.get(str)+"";
        			}else{
        				queryStr+=" and o."+s[0]+"='"+map.get(str)+"'";
        			}
        		}
			}
        }
        
        if(orderMap!=null&&!orderMap.isEmpty()){
        	Set<String> set=orderMap.keySet();
        	String s="";
        	for (String str : set) {
        		if(orderMap.get(str)!=null&&!"".equals(orderMap.get(str))){
        			s+="  o."+str+"  "+orderMap.get(str)+",";
        		}
        	}
        	if(!"".equals(s)){
        		queryStr+=" order by"+s.substring(0, s.length()-1);
        	}
        }
        System.out.println(queryStr);
        if(pageMap==null){
        	List list=new ArrayList();
        	try {
        		list=hibernateTemplate1.find(queryStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
        }else{
        	Session session = hibernateTemplate1.getSessionFactory().openSession();
			Query query = session.createQuery(queryStr);
			query.setReadOnly(true);
			query.setFirstResult((Integer) pageMap.get("start"));
			query.setMaxResults((Integer) pageMap.get("end"));
			session.clear();
			List list = query.list();
			//千万别忘记关闭会话，否则数据库在执行一定操作后，将无法连接;
			session.close();
			return list;
        }
    }
}
