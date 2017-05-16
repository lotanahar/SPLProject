package edu.univdhaka.iit.appclub.calllog;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class GrameenphoneActivity extends Activity {
private TextView gtextview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grameenphone);
		gtextview = (TextView) findViewById(R.id.grtextView);
		StringBuffer gstrinBuffer= new StringBuffer();
		gstrinBuffer.append("Package Name    Quantity\n\n");
		gstrinBuffer.append("Xplore                 N/A\n");
		gstrinBuffer.append("Nischinto            N/A\n");
		gstrinBuffer.append("Bondhu                15\n");
		gstrinBuffer.append("Amontron             1\n");
		gstrinBuffer.append("Shohoj                 N/A\n");
		gstrinBuffer.append("Spondon              N/A\n");
		gstrinBuffer.append("Apon                    N/A\n");
		gstrinBuffer.append("Smile                   3\n");
		gstrinBuffer.append("Djuice                 10\n\n");
		gstrinBuffer.append("You can select at most 3 numbers for FnF");
		gtextview.setText(gstrinBuffer);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grameenphone, menu);
		return true;
	}

}
