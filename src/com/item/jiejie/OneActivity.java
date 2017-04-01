package com.item.jiejie;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * 主Activity
 * 
 * @author Administrator
 * 
 */
public class OneActivity extends Activity implements OnClickListener {

	private Button btn_one;
	private Button btn_gridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		btn_one = (Button) findViewById(R.id.btn_one);
		btn_gridView = (Button)findViewById(R.id.btn_gridview);
		btn_one.setOnClickListener(this);
		btn_gridView.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btn_one:
			startActivity(new Intent(OneActivity.this, MainActivity.class));
			break;
		case R.id.btn_gridview:
			startActivity(new Intent(OneActivity.this,MyActivity.class));
			break;

		default:
			break;
		}
	}
	
	private void setDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(OneActivity.this);
		builder.setTitle("这是一个标题");
		builder.setMessage("这是一个普通的对话框");
		builder.setIcon(R.drawable.ic_launcher);
		//builder.setCancelable(false);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				arg0.cancel();
			}
		});
		builder.setNegativeButton("取消", null);
		builder.create().show();
	}

}
