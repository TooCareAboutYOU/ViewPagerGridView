package com.item.jiejie.adapter;

import java.util.List;
import java.util.Vector;

import com.item.jiejie.ProdctBean;
import com.item.jiejie.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * GridView�������ݵ�������
 * @author Administrator
 *
 */
public class MyGridViewAdpter extends BaseAdapter{
	
	private Context context;
	private List<ProdctBean> lists;//����Դ
	private int mIndex; // ҳ���±꣬��ʾ�ڼ�ҳ����0��ʼ
	private int mPargerSize;// ÿҳ��ʾ����������
	
	private Vector<Boolean> vector=new  Vector<Boolean>();  // ����һ��������Ϊѡ���������
	private int lastPosition = -1;   //lastPosition ��¼��һ��ѡ�е�ͼƬλ�ã�-1��ʾδѡ��
	
	
	
	public MyGridViewAdpter(Context context, List<ProdctBean> lists, int mIndex, int mPargerSize) {
		this.context = context;
		this.lists = lists;
		this.mIndex = mIndex;
		this.mPargerSize = mPargerSize;
		
		for(int i = 0 ; i<lists.size() ; i++){
			vector.add(false);
		}
	}

	/**
	 * ���ж����ݼ��Ĵ�С�Ƿ���ʾ����ҳlists.size() > (mIndex + 1)*mPagerSize
	 * ������㣬���ҳ����ʾ�������lists�ĸ���
	 * ���������ʾÿҳ�������������ôʣ�¼�������ʾ����
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size() > (mIndex + 1) * mPargerSize ? 
				mPargerSize : (lists.size() - mIndex*mPargerSize);
	}

	@Override
	public ProdctBean getItem(int arg0) {
		// TODO Auto-generated method stub
		return lists.get(arg0 + mIndex * mPargerSize);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0 + mIndex * mPargerSize;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_view, null);
			holder.tv_name = (TextView)convertView.findViewById(R.id.item_name);
			holder.iv_nul = (ImageView)convertView.findViewById(R.id.item_image);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder)convertView.getTag();
		}
		//����ȷ��position��Ϊ�õ�����������Դ������Դ�Ƿ�ҳ���ص�ÿҳ��GridView�ϵ�
		final int pos = position + mIndex * mPargerSize;//����mPageSiez
		//����mPagerSize=8�����������ǵڶ�ҳ����mIndex=1���ϵĵڶ���λ��item(position=1),��ô���item��ʵ��λ�þ���pos=9
		holder.tv_name.setText(lists.get(pos).getName() + "");
		holder.iv_nul.setImageResource(lists.get(pos).getUrl());
		
		if(vector.elementAt(position) == true){
			Toast.makeText(context, "�������"  + lists.get(pos).getName(), Toast.LENGTH_SHORT).show();
		}else{
			//Toast.makeText(context, "��û�����"  + lists.get(pos).getName(), Toast.LENGTH_SHORT).show();
		}
		
		//���item����
//		convertView.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				Toast.makeText(context, "�������"  + lists.get(pos).getName(), Toast.LENGTH_SHORT).show();
//			}
//		});
		return convertView;
	}
	static class ViewHolder{
		private TextView tv_name;
		private ImageView iv_nul;
	}
	
	/**
     * �޸�ѡ��ʱ��״̬
     * @param position
     */
    public void changeState(int position){    
        if(lastPosition != -1)    
            vector.setElementAt(false, lastPosition);                   //ȡ����һ�ε�ѡ��״̬    
        vector.setElementAt(!vector.elementAt(position), position);     //ֱ��ȡ������    
        lastPosition = position;                                        //��¼����ѡ�е�λ��    
        notifyDataSetChanged();                                         //֪ͨ���������и���    
    }    
}
