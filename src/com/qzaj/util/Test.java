package com.qzaj.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  

public class Test {
	public static void main(String[] args) {
		HttpClient httpclient = new DefaultHttpClient();  
        String smsUrl="http://htcfkj.6655.la:10170/cxf_qzaj/getRealdataFacetAction?tags=ZTFG:PT-09501";  
        HttpPost httppost = new HttpPost(smsUrl);  
        String strResult = "";  
        try {  
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
                JSONObject jobj = new JSONObject();  
              //  nameValuePairs.add(new BasicNameValuePair("msg","222");  
                httppost.addHeader("Content-type", "application/x-www-form-urlencoded");  
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));  
                HttpResponse response = httpclient.execute(httppost); 
                System.out.println(response);
                if (response.getStatusLine().getStatusCode() == 200) {  
                    /*读返回数据*/  
                    String conResult = EntityUtils.toString(response  
                            .getEntity());  
                    System.out.println(conResult);
                    JSONArray sobj = JSONArray.fromObject(conResult);
                    for (int i = 0; i < sobj.size(); i++) {
                    	JSONObject re = JSONObject.fromObject(sobj.get(i)) ;
                    	System.out.println(re.get("stamp"));
					}
                } else {  
                    String err = response.getStatusLine().getStatusCode()+"";  
                    strResult += "发送失败:"+err;  
                }  
        } catch (ClientProtocolException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
}
