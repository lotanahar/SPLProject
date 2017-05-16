package edu.univdhaka.iit.appclub.calllog;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

public class HelpActivity extends Activity {
private CheckBox g;
private CheckBox a;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		g = (CheckBox) findViewById(R.id.grameenphone);
		a = (CheckBox) findViewById(R.id.airtel);
		
		g.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(g.isChecked()){
					a.setSelected(false);
					Intent ginIntent= new Intent(HelpActivity.this, GrameenphoneActivity.class);
					startActivity(ginIntent);
				}
			}
			
		});
		
a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(a.isChecked()){
					g.setSelected(false);
					Intent ainIntent= new Intent(HelpActivity.this, AirtelActivity.class);
					startActivity(ainIntent);
				}
			}
			
		});
		
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
		return true;
	}

}
