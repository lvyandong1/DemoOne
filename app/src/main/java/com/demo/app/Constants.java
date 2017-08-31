package com.demo.app;


import com.demo.app.utils.EnvironmentStateUtils;

public class Constants {

	// 外部存储设备的根路径
	public static final String ExternalStorageRootPath = EnvironmentStateUtils.getExternalStorageDirectory().getPath();

	// 用户信息存放地
	public static final String USERINFO_FILE = "userinfo.txt";

	/*发送请求的常量 */
	public static final String BASE_URL = "http://www.rozelife.com/api/";

		/*发送请求的方法名 */
	/**短信验证*/
	public static final String SMS_VERIFY = "sms_code";
	/**业主手机号码绑定*/
	public static final String BIND_USER = "bind_user";
}
