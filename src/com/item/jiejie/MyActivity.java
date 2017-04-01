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
 * ʵ�ַ�������ҳ���������ַ�ҳЧ�� 
 * @author Administrator
 *
 */
public class MyActivity extends Activity {
	private ViewPager viewPager;
	private LinearLayout group;//Բ��ָʾ��
	private ImageView[] ivPoints;//СԲ��ͼƬ�ļ���
	private int totalPage; //�ܵ�ҳ��
	private int mPageSize = 8; //ÿҳ��ʾ����������
	private List<ProdctBean> listDatas;//�ܵ�����Դ
	private List<View> viewPagerList;//GridView��Ϊһ��View������ӵ�ViewPager������
	//private int currentPage;//��ǰҳ
	private MyGridViewAdpter gAdapter;
	
	private String[] proName = {"����0","����1","����2","����3","����4","����5",
            "����6","����7","����8","����9","����10","����11","����12","����13",
            "����14","����15","����16","����17","����18","����19"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		//��ʼ���ؼ�
		initView();
		//���ҵ���߼�
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
		//�ܵ�ҳ������ȡ��
		totalPage = (int) Math.ceil(listDatas.size() * 1.0 / mPageSize);
		viewPagerList = new ArrayList<View>();
		
		for(int i = 0; i < totalPage; i++){
			//ÿ��ҳ�涼��inflate��һ����ʵ��
			final GridView gridView = (GridView)View.inflate(this, R.layout.item_gridview, null);
			gAdapter=new MyGridViewAdpter(this, listDatas, i, mPageSize);
			gridView.setAdapter(gAdapter);
			//���item�������
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
			//ÿһ��GridView��Ϊһ��View������ӵ�ViewPager������			
			viewPagerList.add(gridView);
		}
		//����ViewPager������
		viewPager.setAdapter(new MyViewPagerAdapter(viewPagerList));
		
		//���СԲ��
		ivPoints = new ImageView[totalPage];
		for(int i = 0; i < totalPage; i++){
			//ѭ��������ͼƬ��
			ivPoints[i] = new ImageView(this);
			if(i==0){
				ivPoints[i].setImageResource(R.drawable.page_focuese);
			}else {
				ivPoints[i].setImageResource(R.drawable.page_unfocused);
			}
			ivPoints[i].setPadding(8, 8, 8, 8);
			group.addView(ivPoints[i]);
		}
		//����ViewPager�Ļ�����������Ҫ�����õ��ı�����ɫ�ĸı�
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
