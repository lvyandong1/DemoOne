package com.demo.app.utils;

import java.io.File;

import android.os.Environment;

public class EnvironmentStateUtils {
	
	/**
	 * �ж��豸�Ƿ�����ⲿ�洢�豸
	 * 
	 * @return
	 */
	public static boolean ExternalStorageIsAvailable() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * ��ȡ�ⲿ�洢�豸��·��
	 * 
	 * @return
	 */
	public static File getExternalStorageDirectory() {
		return Environment.getExternalStorageDirectory();
	}
}
