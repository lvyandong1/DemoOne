package com.demo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.app.R;

import java.util.List;

/**
 * @author njh
 * 
 */
public class PopAdapter extends BaseAdapter {

	private Context contexts;
	private List<String> list;
	private LayoutInflater inflater;
	public ViewHolder holder = null;
	private int[] colors = new int[] { 0x99f2f2f2, 0x99ffffff };

	public PopAdapter(Context context, List<String> list) {
		this.contexts = context;
		inflater = LayoutInflater.from(contexts);
		this.list = list;
	}

	public void updateView(List<String> list) {
		this.list = list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_pop, null);
			holder.tv1 = (TextView) convertView.findViewById(R.id.tv_pop);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv1.setText(list.get(position));

		int colorPos = position % colors.length;
		convertView.setBackgroundColor(colors[colorPos]);
		return convertView;
	}
	static class ViewHolder {
		TextView tv1;
	}
}
