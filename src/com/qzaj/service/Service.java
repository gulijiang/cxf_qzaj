package com.qzaj.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.qzaj.dao.IDao;
import com.qzaj.dao.impl.Dao;

@Component("service")
public class Service implements IDao{
		
		private Dao dao;

		public Dao getDao() {
			return dao;
		}
		@Resource
		public void setDao(Dao dao) {
			this.dao = dao;
		}
		public void setEntity(Class obClass) {
			dao.setoClass(obClass);
		}
		
		public Object save(Object object) {
			 return dao.save(object);
		}
		public boolean saveSome(List list) {
			 return dao.saveSome(list);
		}
		public boolean delete(Object object) {
			return dao.delete(object);
		}
		public void delete(HashMap<String,Object> map) {
			dao.delete(map);
		}
		public boolean deleteByIds(String ids)throws Exception {
			return dao.deleteByIds(ids);
		}
		public List findAll() {
			return dao.findAll();
		}
		public Object findById(String id) {
			return dao.findById(id);
		}
		public Object getObjectByProperty(HashMap<String,Object> map){
			return dao.getObjectByProperty(map);
		}
		public List getListByProperty(HashMap<String,Object> map,HashMap<String,Object> orderMap,HashMap<String,Object> pageMap) {
			return dao.getListByProperty(map,orderMap,pageMap);
		}
		public List getListByPropertyForMysql(HashMap<String,Object> map,HashMap<String,Object> orderMap,HashMap<String,Object> pageMap) {
			return dao.getListByPropertyForMysql(map,orderMap,pageMap);
		}
		public List getListBySql(String queryStr) throws Exception{
			return dao.getListBySql(queryStr);
		}
		 public HashMap<String, Object> getHashMapBySql(String queryStr) throws Exception{
			 return dao.getHashMapBySql(queryStr);
		 }
		 public  List<HashMap<String, Object>> getResultSetBySql(String queryStr) throws Exception{
			 return dao.getResultSetBySql(queryStr);
		 }
		public void saveListAfterDelete(HashMap<String, Object> map, List list)
				throws Exception {
			// TODO Auto-generated method stub
			dao.saveListAfterDelete(map, list);
		}
		public void excutesql(HashMap<String,Object> map,String id) {
			// TODO Auto-generated method stub
			dao.excutesql(map, id);
		}
		public void excutesql(String sql) {
			// TODO Auto-generated method stub
			dao.excutesql(sql);
		}
		
}
