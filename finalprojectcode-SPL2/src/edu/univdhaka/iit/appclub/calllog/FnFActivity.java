package edu.univdhaka.iit.appclub.calllog;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FnFActivity extends Activity {

	private TextView askforfnf;
	Button okbutton;
	private Button cancelButton;
	private Button findbutton;
	private Button helpbutton;
	Button sendmsgbutton;
	private BaseAdapter adapter1;
	private List<CallHistory> receiveArrayList;
	private TextView ctv1;
	private TextView ctv2;

	public static ArrayList<CallHistory> sgList;
	MyCustomAdapter dataAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fn_f);
		TextView t = (TextView) findViewById(R.id.textViewforfnf);
		okbutton = (Button) findViewById(R.id.okbutton);
		helpbutton= (Button) findViewById(R.id.helpbutton);
		// findbutton = (Button) findViewById(R.id.findSelected);
		sendmsgbutton = (Button) findViewById(R.id.SendMsgButton);
		sgList = new ArrayList<CallHistory>();

		
		okbutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				displayListView();

				// checkButtonClick();
			}

		});

		
		sendmsgbutton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String num;
				TextView t;
				StringBuffer msgformat = new StringBuffer();
				TelephonyManager telephonyManager = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
				String operatorName = telephonyManager.getNetworkOperatorName();
				t = (TextView) findViewById(R.id.ctextView);
				t.setText(operatorName);
				sgList = dataAdapter.suggestionlist;
				if (operatorName.contains("Airtel")) {
					msgformat.append("ADD");

					for (int i = 0; i < sgList.size(); i++) {
						CallHistory c = sgList.get(i);
						if (c.isSelected()) {
							num = c.getNumber();
							if (num.length() > 11) {
								num = num.substring(3, 14);
							}
							msgformat.append(" " + num);
						}

					}
					String str = msgformat.toString();

					sendSMS(Operators.AIRTEL_PHONE, str);

					Toast.makeText(getApplicationContext(),
							"Message is send successfully", Toast.LENGTH_LONG)
							.show();

				}
				
				else if(operatorName.contains("Grameenphone")){

					for (int i = 0; i < sgList.size(); i++) {
						CallHistory c = sgList.get(i);
						if (c.isSelected()) {
							num = c.getNumber();
							if (num.length() > 11) {
								num = num.substring(3, 14);
							}
							msgformat.append(num + " ");
						}

					}
					String str = msgformat.toString();
					//String str = "hi";
					sendSMS(Operators.GRAMEEN_PHONE, str);

					Toast.makeText(getApplicationContext(),
							"Message is send successfully", Toast.LENGTH_LONG)
							.show();

				}

			}
		});
		
		helpbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent hi = new Intent(FnFActivity.this, HelpActivity.class);
			startActivity(hi);
				
			}
		});

	}

	public void sendSMS(String phoneNumber, String message) {
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, null, message, null, null);
	}

	private void displayListView() {

		dataAdapter = new MyCustomAdapter(this, R.layout.suggestion_info,
				SuggestionActivity.arraylist4);
		ListView lv2 = (ListView) findViewById(R.id.listView2);
		lv2.setAdapter(dataAdapter);

		lv2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				CallHistory c = (CallHistory) parent
						.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(),
						"Clicked on Row: " + c.getNumber(), Toast.LENGTH_LONG)
						.show();
			}
		});

	}

	private class MyCustomAdapter extends ArrayAdapter<CallHistory> {

		private ArrayList<CallHistory> suggestionlist;

		public MyCustomAdapter(Context context, int textViewResourceId,
				ArrayList<CallHistory> rcvlist) {
			super(context, textViewResourceId, rcvlist);
			this.suggestionlist = new ArrayList<CallHistory>();
			this.suggestionlist.addAll(rcvlist);
		}

		private class ViewHolder {
			// TextView code;
			CheckBox number;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			Log.v("ConvertView", String.valueOf(position));

			if (convertView == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.suggestion_info, null);

				holder = new ViewHolder();
				holder.number = (CheckBox) convertView
						.findViewById(R.id.checkBox1);
				convertView.setTag(holder);

				holder.number.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						CallHistory c = (CallHistory) cb.getTag();
						Toast.makeText(
								getApplicationContext(),
								"Clicked on Checkbox: " + cb.getText() + " is "
										+ cb.isChecked(), Toast.LENGTH_LONG)
								.show();
						c.setSelected(cb.isChecked());
					}
				});
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			CallHistory c = suggestionlist.get(position);
			// holder.code.setText(" (" + country.getCode() + ")");
			holder.number.setText(c.getNumber());
			holder.number.setChecked(c.isSelected());
			holder.number.setTag(c);

			return convertView;

		}

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fn, menu);
		return true;
	}
}