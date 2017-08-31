package com.demo.app.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.demo.app.MyApplication;

public class FileHelper {

	/**
	 * �ж�path·�����Ƿ�����ļ�
	 * 
	 * @param path
	 * @param name
	 * @return
	 */
	public static boolean checkFileExists(String path, String name) {
		boolean status = false;
		if (!name.equals("")) {
			File newPath = new File(path + name);
			status = newPath.exists();
		}
		return status;
	}

	/**
	 * �ж�path·�����Ƿ�����ļ�
	 *
	 * @return
	 */
	public static boolean checkFileExists(String filepath) {
		boolean status = false;
		if (!TextUtils.isEmpty(filepath)) {
			File newPath = new File(filepath);
			status = newPath.exists();
		}
		return status;
	}

	/**
	 * �����ļ���
	 * 
	 * @param dirName
	 */
	public static void creatDir(String dirName) {
		File file = new File(dirName);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * ��ȡ�ļ����ص���
	 * 
	 * @param filepath
	 * @param fileName
	 * @return
	 */
	public static InputStream getfileInputStream(String filepath, String fileName) {
		File file = new File(filepath, fileName);
		if (file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				return fis;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * ���ַ���д���ļ�����
	 * 
	 * @param path
	 * @param FileName
	 * @param string
	 */
	public static void saveStringToFile(String path, String FileName, String string) {
		File file = new File(path, FileName);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(string.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ����
	 */
	public static Object getEntity(String fileName) {
		Object object = null;
		try {
			FileInputStream fs = MyApplication.getInstance().openFileInput(fileName);
			ObjectInputStream in = new ObjectInputStream(fs);
			object = in.readObject();
			in.close();
			fs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return object;
	}

	/**
	 * ����ʵ�建���ļ�
	 */
	public static void saveEntity(Object object, String fileName) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fs;
			fs = MyApplication.getInstance().openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fs);
			out.writeObject(object);
			out.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�ļ��е��ַ���
	 * 
	 * @param path
	 * @param FileName
	 * @return
	 */
	public static String getStringFromFile(String path, String FileName) {
		File file = new File(path, FileName);
		if (checkFileExists(path, FileName)) {
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[512];
				int length = -1;
				while ((length = fileInputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, length);
				}
				outStream.close();
				fileInputStream.close();
				return outStream.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * ����bitmap
	 * 
	 * @param path
	 * @param Filename
	 * @param bitmap
	 */
	public static void saveBitmap(String path, String Filename, Bitmap bitmap) {
		File file = new File(path, Filename);
		if (!checkFileExists(path, Filename)) {
			try {
				FileOutputStream out = new FileOutputStream(file);
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void saveInputStreamToFile(InputStream is, String filepath, String fileName) {
		File file = new File(filepath, fileName);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = is.read(data)) != -1) {
				fos.write(data, 0, len);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String readInStream(InputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
			Log.i("FileTest", e.getMessage());
		}
		return null;
	}

	public static byte[] toByteArray(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = is.read()) != -1) {
			bytestream.write(ch);
		}
		byte[] imgdata = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

	public static void copyFile(String sourePath, String derPath) throws FileNotFoundException {
		try {
			File oldfile = new File(sourePath);
			if (oldfile.exists()) { // �ļ�����ʱ
				InputStream inStream = new FileInputStream(sourePath); // ����ԭ�ļ�
				FileOutputStream fs = new FileOutputStream(derPath);
				byte[] buffer = new byte[1024];
				int byteread = 0;
				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * ��������ͼƬ
	 * 
	 * @param path
	 * @param Filename
	 * @param imagePath
	 */
	public static void saveBitmap(String path, String Filename, String imagePath) {
		File file = new File(path, Filename);
		if (!checkFileExists(path, Filename)) {
			try {
				URL url = new URL(imagePath);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(6 * 1000); // ע��Ҫ���ó�ʱ������ʱ�䲻Ҫ����10�룬���ⱻandroidϵͳ����
				if (conn.getResponseCode() != 200)
					throw new RuntimeException("����urlʧ��");
				InputStream inSream = conn.getInputStream();
				// ��ͼƬ���浽��Ŀ�ĸ�Ŀ¼
				readAsFile(inSream, file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void readAsFile(InputStream inSream, File file) throws Exception {
		FileOutputStream outStream = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inSream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		outStream.close();
		inSream.close();
	}
}
