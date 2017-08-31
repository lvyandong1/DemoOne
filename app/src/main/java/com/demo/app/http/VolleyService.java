package com.demo.app.http;

import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyService {
	private String Url = null;

	public VolleyService() {
		super();
	}

	public VolleyService(String url) {
		super();
		Url = url;
	}

	public void LoadResultPOST(final Context context,final Map<String, String> map,final GetVolleyInterface getVolleyInterface,final ErrorResponse errorResponse) {
		// 1.����requsetQueue
		RequestQueue request = Volley.newRequestQueue(context);

		
		StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Listener<String>() {
		           @Override
		         public void onResponse(String result) {
		        	 //����ɹ�
						Log.i("", "--------1------->"+result);
						System.out.println("Call Successful!");
						System.out.println(Url);
						getVolleyInterface.volleyServiceCallback(result);
		            }
		       }, new ErrorListener() {
			            @Override
		          public void onErrorResponse(VolleyError error) {
			            	// ����ʧ��
							System.out.println("Exception:  "+error);
							System.out.println(Url);
							errorResponse.onErrorResponse(error);
		            }
			       }) {
			            @Override
			            protected Map<String, String> getParams() throws AuthFailureError {

		               return map;
			           }
		        };

		//3.��ӵ�request
		request.add(stringRequest);
	}
	public void LoadResultGet(final Context context,final GetVolleyInterface getVolleyInterface,final ErrorResponse errorResponse) {
		// 1.����requsetQueue
		RequestQueue request = Volley.newRequestQueue(context);
		// 2.����StringRequset
		StringRequest stringRequest = new StringRequest(Request.Method.GET, Url, new Listener<String>() {

			@Override
			public void onResponse(String result) {
				//����ɹ�
				Log.i("", "--------1------->"+result);
				System.out.println("Call Successful!");
				System.out.println(Url);
				getVolleyInterface.volleyServiceCallback(result);
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				// ����ʧ��
				System.out.println("Exception:  "+error);
				System.out.println(Url);
				errorResponse.onErrorResponse(error);
			}
		});
		//3.��ӵ�request
		request.add(stringRequest);
	}
	/***
	 * ��ȡ���volley�Ļص��ӿ�
	 */
	public interface GetVolleyInterface{
		public void volleyServiceCallback(String result); // ע���ûص��������Դ����κ�����Ҫ�Ĳ���
	}

	public interface ErrorResponse{
		public void onErrorResponse(Exception error);
	}
}
