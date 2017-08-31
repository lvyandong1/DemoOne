package com.demo.app.interfaces;

public interface ViewInit {
	
	/**
	 * 从布�?文件初始化界�?
	 */
	void initViewFromXML()throws Exception;
	
	/**
	 * 初始化数�?
	 */
	void initData()throws Exception;
	
	/**
	 * 填充数据
	 */
	void fillView()throws Exception;
	
	/**
	 * 初始化监�?
	 */
	void initListener()throws Exception;
}
