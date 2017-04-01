package com.item.jiejie;

import com.item.jiejie.view.MyListView;
import com.item.jiejie.view.MyScrollView;
import com.item.jiejie.view.MyScrollView.ScrollViewLine;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

/**
 * 仿qq空间顶！d=====(￣▽￣*)b部渐变
 * 这样写只能实现渐变但是不能实现ListView的顶部停留 
 * 处理不了了
 * 
 * @author Administrator
 * 
 */
public class MainActivity extends Activity {
	private MyScrollView myScrollView;
	private LinearLayout title;
	private MyListView myListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);  
		
		setContentView(R.layout.activity_main);
		setTitleLayout(findViewById(R.id.title));
		title = (LinearLayout)findViewById(R.id.title);
		//头部获取焦点
		title.setFocusable(true);
		title.setFocusableInTouchMode(true);
		title.requestFocus();
		title.getBackground().setAlpha(0);//设置开始的透明度为0
		myScrollView = (MyScrollView)findViewById(R.id.scrollview);
		
		myScrollView.setScrollViewLine(new ScrollViewLine() {
			
			@Override
			public void onScrollChanged(int l, int t, int oldl, int oldt) {
				// TODO Auto-generated method stub
				System.out.println(l + "  " +t + "  " + oldl + "  " + oldt);
				if (oldt < 316 && oldt > 10) {
					title.getBackground().setAlpha((int) (oldt / 3));
				} else if (oldt < 20) {
					title.getBackground().setAlpha(0);
				} else if (oldt >= 316) {
					title.getBackground().setAlpha(255);
				}
			}
		});
		
		myListView = (MyListView)findViewById(R.id.mylistview);
		View headView = View.inflate(MainActivity.this, R.layout.item_text, null);
		TextView textView = (TextView)headView.findViewById(R.id.item_text);
		textView.setText("我是头布局");
		textView.setTextColor(getResources().getColor(R.color.action_bar_back));
		myListView.addHeaderView(headView);
		myListView.setAdapter(new MyAdapter(this));
		myListView.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				// TODO Auto-generated method stub
				Log.d("jiejie", "onScrollStateChanged" + arg1);
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				Log.d("jiejie", "firstVisibleItem" + firstVisibleItem +"visibleItemCount" +visibleItemCount + "totalItemCount" + totalItemCount );
			}
		});
	}
	
	
	/**
	 * MyListView的适配器
	 * @author Administrator
	 *
	 */
	class MyAdapter extends BaseAdapter{
		private Context context;
		
		public MyAdapter(Context context) {
			this.context = context;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 50;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if(arg1 == null){
				arg1 = View.inflate(context, R.layout.item_text, null);
				holder = new ViewHolder();
				holder.tView = (TextView)arg1.findViewById(R.id.item_text);
				arg1.setTag(holder);
			}else {
				holder = (ViewHolder)arg1.getTag();
			}
			holder.tView.setText("随便写的        " +  arg0);
			return arg1;
		}
		
	}
	static class ViewHolder{
		TextView tView;
	}
	protected void setTitleLayout(View view) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			Window window = getWindow();
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			int statusBarHeight = getStatusBarHeight(this.getBaseContext());
			view.setPadding(0, statusBarHeight, 0, 0);

		}
	}

	/**
	 * 获取状态栏高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier(
				"status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}
