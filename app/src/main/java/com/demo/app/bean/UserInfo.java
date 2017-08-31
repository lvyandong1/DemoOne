package com.demo.app.bean;

import com.demo.app.Constants;
import com.demo.app.utils.FileHelper;

import java.io.Serializable;

public class UserInfo implements Serializable{
	
	/**
	 * ���л�
	 */
	private static final long serialVersionUID = 624918509912789954L;
	
	private String user_id = null;
	
	private static UserInfo userinfo = null;
	public static UserInfo getInstance() {
		if (userinfo == null) {
			synchronized (UserInfo.class) {
				if (userinfo == null) {
					userinfo = new UserInfo();
				}
			}
		}
		return userinfo;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	public void commit(){
		FileHelper.saveEntity(userinfo,Constants.USERINFO_FILE);
	}
	
	public void clear(){
		userinfo = new UserInfo();
		commit();
	}
	
	/**
	 * ��ȡ����
	 */
	public void restData() {
		UserInfo userInfoTemp = (UserInfo) FileHelper.getEntity(Constants.USERINFO_FILE);
		if (userInfoTemp != null)
			userinfo = userInfoTemp;
	}
	
}
