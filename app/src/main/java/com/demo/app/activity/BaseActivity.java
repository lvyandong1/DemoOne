package com.demo.app.activity;

import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.demo.app.interfaces.ViewInit;
import com.demo.app.utils.HidenSoftKeyBoard;
import com.demo.app.utils.MLog;
import com.demo.app.utils.SystemBarTintManager;
import com.demo.app.R;


public abstract class BaseActivity extends FragmentActivity implements
		ViewInit, OnClickListener {

	public int deviceWidth;
	public int deviceHeight;

	private FrameLayout activity_base_titlebar = null;
	private FrameLayout activity_base_content = null;
	private FrameLayout activity_base_titleb = null;

	private Dialog loadingDialog;
	private AnimationDrawable loading = null;
	protected boolean is_hidKeyDown = true;

	private SystemBarTintManager mSystemBar = null;

	private View main = null;
	 

	@SuppressLint({ "InlinedApi", "NewApi" })
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		main = getLayoutInflater().inflate(R.layout.base_activity_layout, null);
		main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
		main.setOnClickListener(this);
		if (checkDeviceHasNavigationBar(BaseActivity.this)) {
			setContentView(main);
			Log.i("lyd", "�ֻ�����navigation bar");
		} else {
			setContentView(R.layout.base_activity_layout);
		}
		try {
			activity_base_titlebar = (FrameLayout) findViewById(R.id.activity_base_titlebar);
			activity_base_content = (FrameLayout) findViewById(R.id.activity_base_content);
			activity_base_titleb = (FrameLayout) findViewById(R.id.activity_base_titleb);

			deviceWidth = getWindowManager().getDefaultDisplay().getWidth();
			deviceHeight = getWindowManager().getDefaultDisplay().getHeight();
			// ��̬����״̬���ĸ߶�
			RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) activity_base_titleb
					.getLayoutParams(); // ȡ�ؼ�FrameLayout��ǰ�Ĳ��ֲ���
			relativeParams.height = getStatusBarHeight();// ���ؼ��ĸ�ǿ����ɻ�ȡ����״̬���ĸ߶�
			activity_base_titleb.setLayoutParams(relativeParams); // ʹ���úõĲ��ֲ���Ӧ�õ��ؼ�FrameLayout

			setTitleBar(R.layout.titlebar_base);
			showTitleBar();
			initViewFromXML();
			initData();
			fillCacheData();
			fillView();
			initListener();
		} catch (Exception e) {
			MLog.e("lyd", "����view�д�");
			e.printStackTrace();
		}
		// 4.4�汾���ϸı䴰����ɫ����
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// getWindow().addFlags(
			// WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			mSystemBar = new SystemBarTintManager(this);
			mSystemBar.setStatusBarTintEnabled(true);
			mSystemBar.setNavigationBarTintEnabled(true);
			mSystemBar.setTintColor(getResources().getColor(R.color.TextColorBlack));
		} else {
			activity_base_titleb.setVisibility(View.GONE);
		}
	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		int i = main.getSystemUiVisibility();
		if (i == View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) {// 2
			main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//		} else if (i == View.SYSTEM_UI_FLAG_VISIBLE) {// 0
//			main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
//		} else if (i == View.SYSTEM_UI_FLAG_LOW_PROFILE) {// 1
//			main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		}
	}

	private static boolean checkDeviceHasNavigationBar(Context context) {
		boolean hasNavigationBar = false;
		Resources rs = context.getResources();
		int id = rs
				.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) {
			hasNavigationBar = rs.getBoolean(id);
		}
		try {
			@SuppressWarnings("rawtypes")
			Class systemPropertiesClass = Class
					.forName("android.os.SystemProperties");
			@SuppressWarnings("unchecked")
			Method m = systemPropertiesClass.getMethod("get", String.class);
			String navBarOverride = (String) m.invoke(systemPropertiesClass,
					"qemu.hw.mainkeys");
			if ("1".equals(navBarOverride)) {
				hasNavigationBar = false;
			} else if ("0".equals(navBarOverride)) {
				hasNavigationBar = true;
			}
		} catch (Exception e) {
		}

		return hasNavigationBar;

	}

	/**
	 * ��ʾ��ʾ
	 * 
	 * @param content
	 */
	public void showToast(String content) {
		try {
			Toast.makeText(BaseActivity.this, content, Toast.LENGTH_LONG)
					.show();
		} catch (Exception e) {
			MLog.e("lyd", "Toast�д�");
			e.printStackTrace();
		}
	}

	/**
	 * ���ñ���������
	 * 
	 * @param layoutResID
	 */
	public void setTitleBar(int layoutResID) {
		activity_base_titlebar.removeAllViews();
		LayoutInflater.from(this).inflate(layoutResID, activity_base_titlebar);
	}

	/**
	 * ��ʾ������
	 */
	protected void showTitleBar() {
		activity_base_titlebar.setVisibility(View.VISIBLE);
	}

	/**
	 * ���ر�����
	 */
	protected void hideTitleBar() {
		activity_base_titlebar.setVisibility(View.GONE);
	}

	/**
	 * ���ñ�����
	 * 
	 * @param child
	 */
	public void setTitleBar(View child) {
		activity_base_titlebar.removeAllViews();
		activity_base_titlebar.addView(child);
	}

	/**
	 * ��ȡactivity������
	 * 
	 * @return
	 */
	public ViewGroup getTitleBar() throws Exception {
		return activity_base_titlebar;
	}

	/**
	 * ��仺������
	 */
	protected void fillCacheData() {

	}

	/**
	 * �������ݲ���
	 * 
	 * @param layoutResID
	 */
	protected void setContent(int layoutResID) {
		activity_base_content.removeAllViews();
		LayoutInflater.from(this).inflate(layoutResID, activity_base_content);
	}

	public void showLoadingDialog() {
		showLoadingDialog(this.getString(R.string.text_loading));
	}

	public void showLoadingDialog(String loadingStr) {
		dismissDialog();
		Builder builder = new Builder(BaseActivity.this);
		loadingDialog = builder.create();
		loadingDialog.show();
		LayoutInflater inflater = LayoutInflater.from(BaseActivity.this);
		View view = inflater.inflate(R.layout.loading_dialog_view, null);
		ImageView loading_mark_iv = (ImageView) view
				.findViewById(R.id.loading_mark_iv);
		loading = (AnimationDrawable) loading_mark_iv.getDrawable();

		loadingDialog.setContentView(view);
		Window dialogWindow = loadingDialog.getWindow();
		loadingDialog.setCanceledOnTouchOutside(false);
		dialogWindow.setGravity(Gravity.CENTER);
		WindowManager.LayoutParams layoutParams = dialogWindow
				.getAttributes();
		layoutParams.height = getWindowManager().getDefaultDisplay().getWidth() * 2 / 3;
		layoutParams.width = getWindowManager().getDefaultDisplay().getWidth() * 2 / 3;
		dialogWindow.setAttributes(layoutParams);
		loading.start();
		loadingDialog.show();
	}

	public void dismissDialog() {
		if (loadingDialog != null) {
			try {
				loading.stop();
				loadingDialog.dismiss();
				loadingDialog = null;
			} catch (Exception e) {
				MLog.e("lyd", "ȥ�����ؿ��д�");
			}
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (is_hidKeyDown) {
			if (ev.getAction() == MotionEvent.ACTION_DOWN) {

				View v = getCurrentFocus();
				if (HidenSoftKeyBoard.isShouldHideInput(v, ev)) {
					HidenSoftKeyBoard.hideSoftInput(v.getWindowToken(),
							getApplication());
				}
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * ��ȡ״̬���ĸ߶�
	 * 
	 * @return
	 */
	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height",
				"dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

}
