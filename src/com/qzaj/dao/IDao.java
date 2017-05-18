package com.qzaj.dao;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
@SuppressWarnings("all")
public interface IDao {
	Object save(Object object);
	public boolean saveSome(List list) ;
    boolean delete(Object object);
    public void delete(HashMap<String,Object> map) ;
    boolean deleteByIds(String ids)throws Exception ;
    List findAll();
    Object findById(String id);
    public Object getObjectByProperty(HashMap<String,Object> map);
    public List getListByProperty(HashMap<String,Object> map,HashMap<String,Object> orderMap,HashMap<String,Object> pageMap);
    public List getListByPropertyForMysql(HashMap<String,Object> map,HashMap<String,Object> orderMap,HashMap<String,Object> pageMap);
    public List getListBySql(String queryStr) throws Exception;
    public HashMap<String, Object> getHashMapBySql(String queryStr) throws Exception;
    public  List<HashMap<String, Object>> getResultSetBySql(String queryStr) throws Exception;
    public void saveListAfterDelete(HashMap<String,Object> map,List list) throws Exception;
    public void excutesql(HashMap<String,Object> map,String id);
    public void excutesql(String sql);
}    
    
