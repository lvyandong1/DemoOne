package com.demo.app.activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.demo.app.R;
import com.demo.app.adapter.PopAdapter;
import com.demo.app.utils.LineGraphicView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    LineGraphicView tu;
    ArrayList<Double> yList;
    private TextView tv_tou1,tv_tou2,tv_tou3,tv_more_1;
    List<String> list = new ArrayList<String>();
    @Override
    public void initViewFromXML() throws Exception {
        hideTitleBar();
        setContent(R.layout.activity_main);
        init();
//        findViewById(R.id.tv_more_1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this,Main1Activity.class));
//            }
//        });
        tv_tou1 = (TextView)findViewById(R.id.tv_tou1);
        tv_tou2 = (TextView)findViewById(R.id.tv_tou2);
        tv_tou3 = (TextView)findViewById(R.id.tv_tou3);
        tv_more_1 = (TextView)findViewById(R.id.tv_more_1);
        list.add("qwe");
        list.add("qwe1");
        list.add("qwe2");
        list.add("qwe3");
        list.add("qwe4");
        list.add("qwe5");
    }
    private void init() {
        tu = (LineGraphicView) findViewById(R.id.line_graphic);

        yList = new ArrayList<Double>();
        yList.add((double) 21.03);
        yList.add(40.5);
        yList.add(66.0);
        yList.add(30.8);
        yList.add(43.2);
        yList.add(20.0);
        yList.add(50.0);

        ArrayList<String> xRawDatas = new ArrayList<String>();
        xRawDatas.add("-30");
        xRawDatas.add("-20");
        xRawDatas.add("-10");
        xRawDatas.add("0");
        xRawDatas.add("10");
        xRawDatas.add("20");
        xRawDatas.add("30");
        xRawDatas.add("40");
        tu.setData(yList, xRawDatas, 200, 50);
    }

    @Override
    public void initData() throws Exception {

    }

    @Override
    public void initListener() throws Exception {
        tv_more_1.setOnClickListener(this);
        tv_tou1.setOnClickListener(this);
        tv_tou2.setOnClickListener(this);
        tv_tou3.setOnClickListener(this);
    }

    @Override
    public void fillView() throws Exception {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_tou1:

                break;
            case R.id.tv_tou2:

                showPopupWindow(v,list,tv_tou2);
                break;
            case R.id.tv_tou3:
                showPopupWindow(v,list,tv_tou3);
                break;
            case R.id.tv_more_1:
                showPopupWindow(v,list,tv_more_1);
                break;
        }
    }

    private PopupWindow pWindow;
    private void showPopupWindow(View view,final List<String> list, final TextView textView) {
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.pop_window, null);
        pWindow = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ;
        pWindow.setOutsideTouchable(true);
        pWindow.setFocusable(true);
        pWindow.update();
        pWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        pWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.bg_white));
//        pWindow.showAtLocation(all_title_bg, Gravity.RIGHT|Gravity.TOP,0,105);
        pWindow.showAsDropDown(view);
        ListView pop_list = (ListView)v.findViewById(R.id.pop_list);
        PopAdapter adapter = new PopAdapter(MainActivity.this,list);
        pop_list.setAdapter(adapter);
        pop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(list.get(position));
                pWindow.dismiss();
            }
        });

    }
}
