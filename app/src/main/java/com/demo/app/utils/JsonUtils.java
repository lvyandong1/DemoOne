package com.demo.app.utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {


	/**
	 * @param result
	 * @return
	 * �����������Ƿ�ɹ�
	 */
	public static boolean isSuccess (String result){
		try {
			JSONObject jsonObject = new JSONObject(result);
			int status=jsonObject.optInt("code",1);
			if(status == 1){
				return true;
			}else {
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * @param result
	 * @return
	 * ��ȡ�ɹ����֮���data�ڵ�����
	 */
	public static String getSuccessData(String result,String feild){
		try {
			JSONObject jsonObject=new JSONObject(result);
			return jsonObject.optString(feild,"");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @param result
	 * @return
	 * ��ȡ����ʧ�ܵ���Ϣ
	 */
	public static String getResponseMessage(String result){
		try {
			JSONObject jsonObject=new JSONObject(result);
			return jsonObject.optString("error_text","");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * @param result
	 * @return
	 * ��ȡ�ɹ����֮���data�ڵ�����
	 */
	public static String getFiledData(String result,String feild){
		try {
			JSONObject jsonObject=new JSONObject(result);
			return jsonObject.optString(feild,"");
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}

}
