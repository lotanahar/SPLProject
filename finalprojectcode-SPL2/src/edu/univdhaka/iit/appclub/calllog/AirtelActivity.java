package edu.univdhaka.iit.appclub.calllog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class AirtelActivity extends Activity {
	private TextView atextview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grameenphone);
		atextview = (TextView) findViewById(R.id.grtextView);
		StringBuffer gstrinBuffer= new StringBuffer();
		gstrinBuffer.append("Package Name    Quantity\n\n");
		gstrinBuffer.append("Bondhu            11\n");
		gstrinBuffer.append("Superadda       29\n");
		gstrinBuffer.append("Adda                8\n");
		gstrinBuffer.append("Shobai             10\n");
		gstrinBuffer.append("Hoichoi            2\n");
		gstrinBuffer.append("Dosti                5\n\n");
		gstrinBuffer.append("You can select all the numbers for FnF");
		atextview.setText(gstrinBuffer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.airtel, menu);
		return true;
	}

}
