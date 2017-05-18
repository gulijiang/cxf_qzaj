package com.qzaj.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import com.qzaj.entity.TbAlarm;
import com.qzaj.entity.TbPoint;
import com.qzaj.entity.TbProduct;
import com.qzaj.entity.TbProductHis;
import com.qzaj.entity.TbSign;
import com.qzaj.entity.TbTag;
import com.qzaj.entity.TbTagHis;
import com.qzaj.entity.TbTarget;
import com.qzaj.mysql.entity.VcTag;
import com.qzaj.service.Service;

public class EvaluateActivityTimer extends TimerTask{
	public Service service;
	private long jgdate;
	public static Boolean stop=false;
	public EvaluateActivityTimer(Service serv,long jg) {
		service=serv;
		jgdate=jg;
	}
	public void run(){
		if(stop){
			cancel();
		}
		service.setEntity(TbPoint.class);
		List<TbPoint> pointlist=service.findAll();
		
		service.setEntity(TbSign.class);
		List<TbSign> signtype=service.findAll();
		service.setEntity(VcTag.class);
		//先测试浙江衢州巨新氟化工有限公司
		HashMap<String, Object> mytagMap = new HashMap<String, Object>();
		mytagMap.put("tagPoint_notin", "12,13");
		List<VcTag> mysqltaglist=service.getListByPropertyForMysql(mytagMap, null, null);
		
		List<TbTag> taglist=new ArrayList<TbTag>();
		List<TbTagHis> hislist=new ArrayList<TbTagHis>();
		List<TbProduct> pdtlist=new ArrayList<TbProduct>();
		List<TbProductHis> pdhistlist=new ArrayList<TbProductHis>();
		service.setEntity(TbTarget.class);
		List<TbTarget> tarlist=service.findAll();
		List<TbAlarm> alarmtlist=new ArrayList<TbAlarm>();
		boolean zhgflag = false;//停车信号
		int zhgcount = 0;
		for (VcTag tag : mysqltaglist) {
			if(tag.getTagId() >= 142 && tag.getTagId() <= 164){
				continue;
			}
			if(tag.getTagId() == 71 || tag.getTagId() == 72){
				zhgcount++;
				if(tag.getTagValue() > 0){
					zhgflag = true;
				}
				continue;
			}
//			if((new Date().getTime()-tag.getTagStamp()*1000L)>jgdate){
//				continue;
//			}
			String desc=tag.getTagDesc();
			TbSign sign=contype(signtype, desc);
			if(sign==null){
				continue;
			}
			//glj update int compId = getcompid(pointlist, tag.getTagPoint());
			
			TbPoint point= mysqlpointTransOraclepoint(pointlist, tag.getTagPoint());
			if(point == null){
				continue;
			}
			int compId = point.getCompId();
			
			String str[]=desc.split(sign.getSignName());
			/*if(tag.getTagId() == 21){
				System.out.println("------121");
			}*/
			if(1==sign.getSignState()){
				service.setEntity(TbProduct.class);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("productId", tag.getTagId());
				List<TbProduct> list = service.getListByProperty(map, null, null);
				TbProduct pdt;
				if(list == null || list.size() == 0){
					pdt = new TbProduct();
					pdt.setProductId(tag.getTagId());
					pdt.setCompId(compId);
					pdt.setSignDesc(tag.getTagDesc());
				}else{
					pdt = list.get(0);
				}
				String de = tag.getTagDesc();
				//如果有特殊情况进行特殊处理,元立企业是开关量信号进行特殊处理，0停车，其他表示开车
				if("煤气柜运行信号".equals(de)){
					if(tag.getTagValue() == 0){
						pdt.setProductState(0);
					}else{
						pdt.setProductState(1);
					}
				}else{
					if(tag.getTagValue() > 0){
						pdt.setProductState(1);
					}else{
						pdt.setProductState(0);
					}
				}
				
				pdt.setValue(tag.getTagValue());
				pdt.setProductName(tag.getTagName());
				//pdt.setProductDesc(tag.getTagDesc());
				pdt.setProductTime(new Date());
				pdtlist.add(pdt);
				
				TbProductHis his = new TbProductHis();
				listcast(pdt, his);
				his.setProductId(null);
				pdhistlist.add(his);
			}else{
				//根据名称去查下就可以了
				service.setEntity(TbTarget.class);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("targetName", str[0]);
				map.put("pointId",point.getPointId());
				int targetId = 0;
				List<TbTarget> targetList = service.getListByProperty(map, null, null);
				if(targetList == null || targetList.size() == 0){
					continue;
				}else{
					targetId = targetList.get(0).getTargetId();
				}
				
//				TbTarget tar=new TbTarget();
//				tar.setTargetId(tag.getTagId());
//				tar.setPointId(tag.getTagPoint());
//				tar.setTargetMatter("硫酸");
//				tar.setCompId(compId);
//				tar.setTargetName(str[0]);
//				tarlist.add(tar);
				
				TbTag ttag =new TbTag();
				listcast(tag, ttag);
				ttag.setCompId(compId);
				ttag.setTagTank(str[0]);
				ttag.setTagSign(sign.getSignName());
				ttag.setTagTime(new Date());
				ttag.setPointId(point.getPointId());
				ttag.setTargetId(targetId);
				taglist.add(ttag);
				
				//插入报警表
				if(1 == tag.getTagAlarm() || 2 == tag.getTagAlarm()){
					//根据tagname查询，跟原先的进行比较
					StringBuffer buf = new StringBuffer();
					buf.append("SELECT to_char(a.tag_time,'yyyy-MM-dd HH24:mi:ss') as tag_time,a.tag_name,a.tag_alarm")
							.append(" FROM tb_tag_his a")
									.append("	 WHERE a.tag_time =  (SELECT MAX(a.tag_time) FROM tb_tag_his a WHERE a.tag_name = '"+tag.getTagName()+"')")
											.append(" AND a.tag_name = '"+tag.getTagName()+"'")
													.append(" AND ROWNUM = 1");
					try {
						List<HashMap<String, Object>> bufList = service.getResultSetBySql(buf.toString());
						if(bufList.size() == 1){
							Map<String, Object> gmap = bufList.get(0);
							int tag_alarm = Integer.valueOf(gmap.get("tag_alarm").toString());
							if(tag.getTagAlarm() != tag_alarm){
								TbAlarm alarm=new TbAlarm();
								alarm.setAlarmClear(new Date());
								alarm.setAlarmStamp(new Date());
								alarm.setAlarmTagname(ttag.getTagName());
								alarm.setAlarmType(tag.getTagAlarm());
								alarm.setAlarmValue(ttag.getTagValue());
								//glj add
								if(ttag.getTagSign().contains("气体报警")){
									alarm.setAlarmMode(1);
								}else{
									alarm.setAlarmMode(0);
								}
								alarm.setCompId(ttag.getCompId());
								alarm.setTagSign(ttag.getTagSign());
								alarm.setTagTank(ttag.getTagTank());
								alarm.setAlamOrigin(0);
								alarm.setSendState(0);
								alarmtlist.add(alarm);
							}
//							else{
//								//如果相等的话就看时间，如果已经超过24小时则需要插入一条有效的报警数据
//								if(DateUtil.judgmentDate(gmap.get("tag_time").toString())){
//									TbAlarm alarm=new TbAlarm();
//									alarm.setAlarmClear(new Date());
//									alarm.setAlarmStamp(new Date());
//									alarm.setAlarmTagname(ttag.getTagName());
//									alarm.setAlarmType(tag.getTagAlarm());
//									alarm.setAlarmValue(ttag.getTagValue());
//									//glj add
//									if(ttag.getTagSign().contains("气体报警")){
//										alarm.setAlarmMode(1);
//									}else{
//										alarm.setAlarmMode(0);
//									}
//									alarm.setCompId(ttag.getCompId());
//									alarm.setTagSign(ttag.getTagSign());
//									alarm.setTagTank(ttag.getTagTank());
//									alarm.setAlamOrigin(0);
//									alarm.setSendState(0);
//									alarmtlist.add(alarm);
//								}
//							}
						}else{
							TbAlarm alarm=new TbAlarm();
							alarm.setAlarmClear(new Date());
							alarm.setAlarmStamp(new Date());
							alarm.setAlarmTagname(ttag.getTagName());
							alarm.setAlarmType(tag.getTagAlarm());
							alarm.setAlarmValue(ttag.getTagValue());
							//glj add
							if(ttag.getTagSign().contains("气体报警")){
								alarm.setAlarmMode(1);
							}else{
								alarm.setAlarmMode(0);
							}
							alarm.setCompId(ttag.getCompId());
							alarm.setTagSign(ttag.getTagSign());
							alarm.setTagTank(ttag.getTagTank());
							alarm.setAlamOrigin(0);
							alarm.setSendState(0);
							alarmtlist.add(alarm);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
//				if(ttag.getTagValue()>ttag.getTagHalarm()){
//					int t=1;
//					if(ttag.getTagValue()>ttag.getTagHlimit()){
//						 t=2;
//					}
//					TbAlarm alarm=new TbAlarm();
//					alarm.setAlarmClear(new Date());
//					alarm.setAlarmStamp(new Date());
//					alarm.setAlarmTagname(ttag.getTagName());
//					alarm.setAlarmType(t);
//					alarm.setAlarmValue(ttag.getTagValue());
//					//glj add
//					if(ttag.getTagSign().contains("气体报警")){
//						alarm.setAlarmMode(1);
//					}else{
//						alarm.setAlarmMode(0);
//					}
//					alarm.setCompId(ttag.getCompId());
//					alarm.setTagSign(ttag.getTagSign());
//					alarm.setTagTank(ttag.getTagTank());
//					alarmtlist.add(alarm);
//				}
				
				TbTagHis his =new TbTagHis();
				listcast(ttag, his);
				his.setTagId(null);
				hislist.add(his);
			}
		}
		//正和硅
		if(zhgcount > 0){
			if(zhgflag){
				//开车
				service.setEntity(TbProduct.class);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("productId",42);
				List<TbProduct> list = service.getListByProperty(map, null, null);
				TbProduct pdt = list.get(0);
				pdt.setProductState(1);
				pdt.setValue(1.0);
				pdt.setProductTime(new Date());
				pdtlist.add(pdt);
				TbProductHis his = new TbProductHis();
				listcast(pdt, his);
				his.setProductId(null);
				pdhistlist.add(his);
			}else{
				//停车
				service.setEntity(TbProduct.class);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("productId",42);
				List<TbProduct> list = service.getListByProperty(map, null, null);
				TbProduct pdt = list.get(0);
				pdt.setProductState(0);
				pdt.setValue(0.0);
				pdt.setProductTime(new Date());
				pdtlist.add(pdt);
				TbProductHis his = new TbProductHis();
				listcast(pdt, his);
				his.setProductId(null);
				pdhistlist.add(his);
			}
		}
		
		//先测试浙江衢州巨新氟化工有限公司
		service.setEntity(VcTag.class);
		HashMap<String, Object> jhfxMap = new HashMap<String, Object>();
		jhfxMap.put("tagPoint_in", "11,12,13");
		jhfxMap.put("tagDesc_like", "电流");
		List<VcTag> jhfxtaglist = service.getListByPropertyForMysql(jhfxMap, null, null);
		boolean jx_2R134a = false,jx_705 = false,jx_R125 = false,jx_3CM = false,jx_3_703 = false,
				jx_R134a = false,qhfh_701 = false,qhfh_4CM = false,qhfh_703 = false,qhfh_1CM = false,qhfh_2CM = false;
		Map<String, TbProduct> fhStopMap = new HashMap<String, TbProduct>();
		for (VcTag tag : jhfxtaglist) {
			TbPoint point= mysqlpointTransOraclepoint(pointlist, tag.getTagPoint());
			if(point == null){
				continue;
			}
			int compId = point.getCompId();
			//浙江衢州巨新氟化工有限公司 开停车状态
			if(tag.getTagId() == 156 || tag.getTagId() == 157){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(156);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("705液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(jx_705){
					if(tag.getTagValue() > 0){
						jx_705 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("156",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						jx_705 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("156",tbProduct);
				}
			}
			
			if(tag.getTagId() == 161 || tag.getTagId() == 162){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(161);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("2R134a液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(jx_2R134a){
					if(tag.getTagValue() > 0){
						jx_2R134a = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("161",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						jx_2R134a = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("161",tbProduct);
				}
			}
			
			if(tag.getTagId() == 163 || tag.getTagId() == 164){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(163);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("R125液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(jx_R125){
					if(tag.getTagValue() > 0){
						jx_R125 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("163",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						jx_R125 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("163",tbProduct);
				}
			}
			
			//浙江巨化股份有限公司有机氟厂 148,149,154,155,158,159,160
			if(tag.getTagId() == 148 || tag.getTagId() == 149){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(148);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("3CM液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(jx_3CM){
					if(tag.getTagValue() > 0){
						jx_3CM = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("148",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						jx_3CM = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("148",tbProduct);
				}
			}
			
			
			if(tag.getTagId() == 154 || tag.getTagId() == 155){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(154);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("3-703液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(jx_3_703){
					if(tag.getTagValue() > 0){
						jx_3_703 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("154",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						jx_3_703 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("154",tbProduct);
				}
			}
			
			
			if(tag.getTagId() == 158 || tag.getTagId() == 159 || tag.getTagId() == 160){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(158);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("R134a液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(jx_R134a){
					if(tag.getTagValue() > 0){
						jx_R134a = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("158",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						jx_R134a = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("158",tbProduct);
				}
			}
			
			//浙江衢化氟化学有限公司
			if(tag.getTagId() == 142 || tag.getTagId() == 143){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(142);

				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("701液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(qhfh_701){
					if(tag.getTagValue() > 0){
						qhfh_701 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("142",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						qhfh_701 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("142",tbProduct);
				}
			}
			
			if(tag.getTagId() == 150 || tag.getTagId() == 151){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(150);

				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("4CM液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(qhfh_4CM){
					if(tag.getTagValue() > 0){
						qhfh_4CM = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("150",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						qhfh_4CM = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("150",tbProduct);
				}
			}
			
			if(tag.getTagId() == 152 || tag.getTagId() == 153){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(152);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("2-703液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(qhfh_703){
					if(tag.getTagValue() > 0){
						qhfh_703 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("152",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						qhfh_703 = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("152",tbProduct);
				}
			}
			
			
			if(tag.getTagId() == 144 || tag.getTagId() == 145){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(144);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("1CM液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(qhfh_1CM){
					if(tag.getTagValue() > 0){
						qhfh_1CM = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("144",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						qhfh_1CM = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("144",tbProduct);
				}
			}
			
			if(tag.getTagId() == 146 || tag.getTagId() == 147){
				TbProduct tbProduct = new TbProduct();
				tbProduct.setCompId(compId);
				tbProduct.setProductId(146);
				tbProduct.setProductName(tag.getTagName());
				tbProduct.setProductDesc("2CM液化烃生产装置");
				tbProduct.setProductTime(new Date());
				tbProduct.setSignDesc(tag.getTagDesc());
				if(qhfh_2CM){
					if(tag.getTagValue() > 0){
						qhfh_2CM = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
						fhStopMap.put("146",tbProduct);
					}
				}else{
					if(tag.getTagValue() > 0){
						qhfh_2CM = true;
						tbProduct.setProductState(1);
						tbProduct.setValue(tag.getTagValue());
					}else{
						tbProduct.setProductState(0);
						tbProduct.setValue(tag.getTagValue());
					}
					fhStopMap.put("146",tbProduct);
				}
			}
			
			
		}
		
		 for (String key : fhStopMap.keySet()) {  
	           // System.out.println("key = " + key + " and value = " + map.get(key));  
			    service.setEntity(TbProduct.class);
				HashMap<String, Object> jxmap = new HashMap<String, Object>();
				jxmap.put("productId",key);
				List<TbProduct> list = service.getListByProperty(jxmap, null, null);
				if(list == null || list.size() == 0){
					TbProduct product = fhStopMap.get(key);
					pdtlist.add(product);
					TbProductHis his = new TbProductHis();
					listcast(product, his);
					his.setProductId(null);
					pdhistlist.add(his);
				}else{
					TbProduct product = fhStopMap.get(key);
					TbProduct pdt = list.get(0);
					pdt.setProductState(product.getProductState());
					pdt.setValue(product.getValue());
					pdt.setProductTime(new Date());
					pdtlist.add(pdt);
					TbProductHis his = new TbProductHis();
					listcast(pdt, his);
					his.setProductId(null);
					pdhistlist.add(his);
				}
	     }  
		
//		service.setEntity(TbTarget.class);
//		service.saveSome(tarlist);
		
		service.setEntity(TbTag.class);
		service.saveSome(taglist);
		
		service.setEntity(TbTagHis.class);
		service.saveSome(hislist);
		
		service.setEntity(TbProduct.class);
		service.saveSome(pdtlist);
		
		service.setEntity(TbProductHis.class);
		service.saveSome(pdhistlist);
		
		service.setEntity(TbAlarm.class);
		service.saveSome(alarmtlist);
		
    }
	public void listcast(Object o,Object ob){
		Method[] meths = o.getClass().getMethods();
		for (Method meth : meths) {
			String methName = meth.getName();
			if (methName.indexOf("get") < 0) {
				continue;
			}
			try {
			if(("set"+ methName.substring(3, methName.length())).equals("setClass")){
				continue;
			}
			Method meth1 = ob.getClass().getMethod("set"+ methName.substring(3, methName.length()),meth.getReturnType());
			meth1.invoke(ob,meth.invoke(o, new Object[] {}));
			} catch (Exception e) {
			}
		}
		
	}

	
	public void splitdesc(List<TbSign> signtype,List<TbTagHis> hislist){
		for (TbTagHis his : hislist) {
			String desc=his.getTagSign();
			TbSign type=contype(signtype, desc);
			if(type==null){
				continue;
			}
			String str[]=desc.split(type.getSignName());
			
		}
	}
	public TbSign contype(List<TbSign> signtype,String desc){
		for (TbSign sign : signtype) {
			if(desc.contains(sign.getSignName())){
				return sign;
			}
		}
		return null;
	}
	public Integer getcompid(List<TbPoint> pointlist,Integer pointid){
		for (TbPoint point : pointlist) {
			if(point.getTagPoint().equals(pointid)){
				return point.getCompId();
			}
		}
		return 0;
	}
	
	public TbPoint mysqlpointTransOraclepoint(List<TbPoint> pointlist,Integer pointid){
		for (TbPoint point : pointlist) {
			if(point.getTagPoint().equals(pointid)){
				return point;
			}
		}
		return null;
	}
	
	public Integer gettargetid(List<TbTarget> tarlist,String tank,Integer pointid){
		for (TbTarget tar : tarlist) {
			if(tar.getTargetName().equals(tank)&&tar.getPointId().equals(pointid)){
				return tar.getTargetId();
			}
		}
		return 0;
	}
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	

}
