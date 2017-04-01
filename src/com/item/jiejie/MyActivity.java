package com.item.jiejie;

import java.util.ArrayList;
import java.util.List;
import com.item.jiejie.adapter.MyGridViewAdpter;
import com.item.jiejie.adapter.MyViewPagerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 实现仿美团首页导航栏布局分页效果 
 * @author Administrator
 *
 */
public class MyActivity extends Activity {
	private ViewPager viewPager;
	private LinearLayout group;//圆点指示器
	private ImageView[] ivPoints;//小圆点图片的集合
	private int totalPage; //总的页数
	private int mPageSize = 8; //每页显示的最大的数量
	private List<ProdctBean> listDatas;//总的数据源
	private List<View> viewPagerList;//GridView作为一个View对象添加到ViewPager集合中
	//private int currentPage;//当前页
	private MyGridViewAdpter gAdapter;
	
	private String[] proName = {"名称0","名称1","名称2","名称3","名称4","名称5",
            "名称6","名称7","名称8","名称9","名称10","名称11","名称12","名称13",
            "名称14","名称15","名称16","名称17","名称18","名称19"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		//初始化控件
		initView();
		//添加业务逻辑
		initData();
	}
	
	private void initView() {
		// TODO Auto-generated method stub
		viewPager = (ViewPager)findViewById(R.id.viewpager);
		group = (LinearLayout)findViewById(R.id.points);
		listDatas = new ArrayList<ProdctBean>();
		for(int i =0 ; i < proName.length; i++){
			listDatas.add(new ProdctBean(proName[i], R.drawable.iv_driving));
		}
	}
	private void initData() {
		// TODO Auto-generated method stub
		//总的页数向上取整
		totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
		viewPagerList = new ArrayList<View>();
		
		for(int i = 0; i < totalPage; i++){
			//每个页面都是inflate出一个新实例
			final GridView gridView = (GridView)View.inflate(this, R.layout.item_gridview, null);
			gAdapter=new MyGridViewAdpter(this, listDatas, i, mPageSize);
			gridView.setAdapter(gAdapter);
			//添加item点击监听
			gridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
					// TODO Auto-generated method stub
					
					gAdapter.changeState(position);
					
					Object obj = gridView.getItemAtPosition(position);
					if(obj != null && obj instanceof ProdctBean){
						System.out.println(obj);
						Toast.makeText(MyActivity.this, ((ProdctBean)obj).getName(), Toast.LENGTH_SHORT).show();
					}
				}
			});
			//每一个GridView作为一个View对象添加到ViewPager集合中			
			viewPagerList.add(gridView);
		}
		//设置ViewPager适配器
		viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));
		
		//添加小圆点
		ivPoints = new ImageView[totalPage];
		for(int i = 0; i < totalPage; i++){
			//循坏加入点点图片组
			ivPoints[i] = new ImageView(this);
			if(i==0){
				ivPoints[i].setImageResource(R.drawable.page_focuese);
			}else {
				ivPoints[i].setImageResource(R.drawable.page_unfocused);
			}
			ivPoints[i].setPadding(8, 8, 8, 8);
			group.addView(ivPoints[i]);
		}
		//设置ViewPager的滑动监听，主要是设置点点的背景颜色的改变
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				//currentPage = position;
				for(int i=0 ; i < totalPage; i++){
					if(i == position){
						ivPoints[i].setImageResource(R.drawable.page_focuese);
					}else {
						ivPoints[i].setImageResource(R.drawable.page_unfocused);
					}
				}
			}
		});
	}
}
