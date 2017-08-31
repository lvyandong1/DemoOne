package com.demo.app.utils;

import android.util.Log;

public class MLog {

	/**Log Mode **/
	public static boolean DEBUG = true;
	/**Log priority of none*/
    public final static int PRIORITY_NONE = 0xFFFF;
	/**Log priority of verbose.*/
    public final static int PRIORITY_VERBOSE = 2;
    /**Log priority of debug.*/
    public final static int PRIORITY_DEBUG = 3;
    /**Log priority of info.*/
    public final static int PRIORITY_INFO = 4;
    /**Log priority of warning.*/
    public final static int PRIORITY_WARN = 5;
    /**Log priority of error.*/
    public final static int PRIORITY_ERROR = 6;
    /**Log priority of exception.*/
    public final static int PRIORITY_ASSERT = 7;
    
    //-------------------------------------------------------------------------
    // Private static members
    //-------------------------------------------------------------------------
    /**Log TAG ǰ�Y*/
	private final static String TAG = "LGS: ";
	
	/**��ӡ���Լ���*/
	private static int msLogPriority = PRIORITY_VERBOSE;

    
	static {
	    resetLogPriority();
	}
	
	
	/**
	 * �ж�ĳ������Ĵ�ӡ�Ƿ��
	 * @param priority
	 * @return
	 */
	public static boolean isLogEnable(int priority) {
		return (msLogPriority <= priority);
	}
    
    
    
    /**
     * ��ȡ��ӡ���Լ���
     * @return ��ǰ��ӡ���Լ���
     */
	public static int getLogPriority() {
		return msLogPriority;
	}
	
	/**
	 * ���ô�ӡ���Լ���
	 */
	public static void resetLogPriority(int pri) {
		msLogPriority = pri;
	}
	
	/**
	 * ���ô�ӡ���Լ���
	 */
	public static void resetLogPriority() {
		if(DEBUG){
			msLogPriority = PRIORITY_VERBOSE;
		}else{
			msLogPriority = PRIORITY_NONE;
		}
	}
	
    
	//-------------------------------------------------------------------------
    /**
     * ��ӡDEBUG
     */
    public static void d(String tag, String message) {
    	if (msLogPriority <= PRIORITY_DEBUG) {
    	    if(message == null) {
    	        message = "";
    	    }
            Log.d(TAG + tag, message);
    	}
    }
    
    /**
     * ��ӡDEBUG
     */
    public static void d(String tag, String message, Throwable tr) {
    	if (msLogPriority <= PRIORITY_DEBUG) {
    	    if(message == null) {
                message = "";
            }
            Log.d(TAG + tag, message, tr);
    	}
    }

    /**
     * ��ӡDEBUG
     */
    public static void d(String tag, String format, Object... args){
    	if (msLogPriority <= PRIORITY_DEBUG) {
    		String msg = String.format(format, args);
            Log.d(TAG + tag, msg);
    	}
    }
    
    //-------------------------------------------------------------------------
    /**
     * ��ӡERROR
     */
    public static void e(String tag, String message) {
    	if (msLogPriority <= PRIORITY_ERROR) {
    	    if(message == null) {
                message = "";
            }
            Log.e(TAG + tag, message);
    	}
    }
    
    /**
     * ��ӡERROR
     */
    public static void e(String tag, String format, Object... args){
        if (msLogPriority <= PRIORITY_ERROR) {
            String msg = String.format(format, args);
            Log.e(TAG + tag, msg);
        }
    }
    
    /**
     * ��ӡERROR
     */
    public static void e(String tag, String message, Throwable tr) {
    	if (msLogPriority <= PRIORITY_ERROR) {
    	    if(message == null) {
                message = "";
            }
            Log.e(TAG + tag, message, tr);
    	}
    }
    
    //-------------------------------------------------------------------------
    /**
     * ��ӡINFO
     */
    public static void i(String tag, String message) {
    	if (msLogPriority <= PRIORITY_INFO) {
    	    if(message == null) {
                message = "";
            }
            Log.i(TAG + tag, message);
    	}
    }
    
    /**
     * ��ӡINFO
     */
    public static void i(String tag, String format, Object... args){
        if (msLogPriority <= PRIORITY_INFO) {
            String msg = String.format(format, args);
            Log.i(TAG + tag, msg);
        }
    }
    
    /**
     * ��ӡINFO
     */
    public static void i(String tag, String message, Throwable tr){
        if (msLogPriority <= PRIORITY_INFO) {
            if(message == null) {
                message = "";
            }
            Log.i(TAG + tag, message, tr);
        }
    }

    //-------------------------------------------------------------------------
    /**
     * ��ӡWARN
     */
    public static void w(String tag, String message) {
    	if (msLogPriority <= PRIORITY_WARN) {
    	    if(message == null) {
                message = "";
            }
            Log.w(TAG + tag, message);
    	}
    }
    
    /**
     * ��ӡWARN
     */
    public static void w(String tag, String format, Object... args){
        if (msLogPriority <= PRIORITY_WARN) {
            String msg = String.format(format, args);
            Log.w(TAG + tag, msg);
        }
    }
    
    /**
     * ��ӡWARN
     */
    public static void w(String tag, String message, Throwable tr){
    	if (msLogPriority <= PRIORITY_WARN) {
    	    if(message == null) {
                message = "";
            }
            Log.w(TAG + tag, message, tr);
    	}
    }

    //-------------------------------------------------------------------------
    /**
     * ��ӡVERBOSE
     */
    public static void v(String tag, String message) {
    	if (msLogPriority <= PRIORITY_VERBOSE) {
    	    if(message == null) {
                message = "";
            }
            Log.v(TAG + tag, message);
    	}
    }
    
    /**
     * ��ӡVERBOSE
     */
    public static void v(String tag, String format, Object... args){
        if (msLogPriority <= PRIORITY_VERBOSE) {
            String msg = String.format(format, args);
            Log.v(TAG + tag, msg);
        }
    }
    
    /**
     * ��ӡVERBOSE
     */
    public static void v(String tag, String message, Throwable tr){
        if (msLogPriority <= PRIORITY_VERBOSE) {
            if(message == null) {
                message = "";
            }
            Log.v(TAG + tag, message, tr);
        }
    }
    //-------------------------------------------------------------------------
    /**
     * println
     */
    public static void println(int priority, String tag, String message) {
        if(message == null) {
            message = "";
        }
        Log.println(priority, TAG + tag, message);
    }
    
    /**
     * �õ���ǰλ�õĻص�ջ
     * @return
     */
    public static String getCurrStackMsg() {
        return getStackMsg(new Throwable());
    }
    
    /**
     * �ѻص���ջת�����ַ���
     * @param th
     * @return
     */
    public static String getStackMsg(Throwable th) {
        StringBuffer sb = new StringBuffer();  
        StackTraceElement[] stackArray = th.getStackTrace();  
        for (int i = 0; i < stackArray.length; i++) {  
            StackTraceElement element = stackArray[i];  
            sb.append("    " + element.toString() + "\n");  
        }
        return sb.toString();  
    }

}
