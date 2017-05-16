package edu.univdhaka.iit.appclub.calllog;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.Character.UnicodeBlock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SuggestionActivity extends Activity implements
		LoaderManager.LoaderCallbacks<Cursor> {
	private static final String TAG = "CallLog";
	private static final int URL_LOADER = 1;

	public ListView listView;
	private ArrayList<CallHistory> arraylist;
	private ArrayList<CallHistory> arraylist2;
	private ArrayList<CallHistory> arraylist3;
	public static ArrayList<CallHistory> arraylist4;
	private BaseAdapter adapter;
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private Button next;
	private Button speed;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate()");

		setContentView(R.layout.main);
		initialize();
	}

	private void initialize() {
		Log.d(TAG, "initialize()");

		Button btnCallLog = (Button) findViewById(R.id.btn_call_log);
		next = (Button) findViewById(R.id.fnfbutton);
		speed = (Button) findViewById(R.id.buttonspeed);
		btnCallLog.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.d(TAG, "initialize() >> initialise loader");
				getLoaderManager().initLoader(URL_LOADER, null,
						SuggestionActivity.this);
				listView = (ListView) findViewById(R.id.listView1);
				arraylist = new ArrayList<CallHistory>();
				arraylist2 = new ArrayList<CallHistory>();
				arraylist3 = new ArrayList<CallHistory>();
				arraylist4 = new ArrayList<CallHistory>();
				adapter = new BaseAdapter() {
					LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

					@Override
					public View getView(int position, View view,
							ViewGroup viewGroup) {
						if (view == null) {
							view = inflater.inflate(R.layout.liststyle, null);
						}

						
						tv1 = (TextView) view.findViewById(R.id.textView1);
						tv2 = (TextView) view.findViewById(R.id.textView2);
						tv3 = (TextView) view.findViewById(R.id.textView3);
						
						tv1.setText(arraylist4.get(position).getName());
						tv2.setText(arraylist4.get(position).getNumber());
						tv3.setText(arraylist4.get(position).getDuration());

						return view;
					}

					@Override
					public long getItemId(int position) {
						return 0;
					}

					@Override
					public Object getItem(int position) {
						return arraylist4.get(position);
					}

					@Override
					public int getCount() {
						return arraylist4.size();
					}
				};
				listView.setAdapter(adapter);
			}
		});

		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// next.setVisibility(View.INVISIBLE);
				Intent intent = new Intent(SuggestionActivity.this,
						FnFActivity.class);
				// intent.putParcelableArrayListExtra("sendlist", arraylist4);
				startActivity(intent);
			}
		});

		speed.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// next.setVisibility(View.INVISIBLE);
				Intent intent = new Intent(SuggestionActivity.this,
						FnFActivity.class);
				// intent.putParcelableArrayListExtra("sendlist", arraylist4);
				startActivity(intent);
			}
		});

		// callLogsTextView = (TextView) findViewById(R.id.call_logs);

	}

	@Override
	public Loader<Cursor> onCreateLoader(int loaderID, Bundle args) {
		Log.d(TAG, "onCreateLoader() >> loaderID : " + loaderID);

		switch (loaderID) {
		case URL_LOADER:
			// Returns a new CursorLoader
			return new CursorLoader(this, // Parent activity context
					CallLog.Calls.CONTENT_URI, // Table to query
					null, // Projection to return
					null, // No selection clause
					null, // No selection arguments
					null // Default sort order
			);
		default:
			return null;
		}

	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor managedCursor) {
		Log.d(TAG, "onLoadFinished()");

		int name = managedCursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
		int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
		// int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
		// int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
		int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
		CallHistory callHistory;
		while (managedCursor.moveToNext()) {
			String Name = managedCursor.getString(name);
			String phNumber = managedCursor.getString(number);
			// String callType = managedCursor.getString(type);
			// String callDate = managedCursor.getString(date);
			// Date callDayTime = new Date(Long.valueOf(callDate));
			String callDuration = managedCursor.getString(duration);
			int cd = Integer.parseInt(callDuration);
			/*
			 * String dir = null; int callTypeCode = Integer.parseInt(callType);
			 * switch (callTypeCode) { case CallLog.Calls.OUTGOING_TYPE: dir =
			 * "Outgoing"; break;
			 * 
			 * case CallLog.Calls.INCOMING_TYPE: dir = "Incoming"; break;
			 * 
			 * case CallLog.Calls.MISSED_TYPE: dir = "Missed"; break; }
			 */

			callHistory = new CallHistory( phNumber, cd);
			arraylist.add(callHistory);

		}

		// int flag= 0;
		/*CallHistory x = null, y;
		String dur1 = null, dur2, phno1 = null, phno2 = null, cname = null;
		int d1, d2, res;
		// managedCursor.close();
		// Collections.sort(arraylist,new CallHistory());
		for (int i = 0; i < arraylist.size(); i++) {

			// if (x.getFlag() == 1)
			// continue;
			for (int j = 0; j < arraylist.size(); j++) {
				x = arraylist.get(i);
				y = arraylist.get(j);
				dur1 = x.getDuration();
				dur2 = y.getDuration();
				phno1 = x.getNumber();
				phno2 = y.getNumber();
				cname= x.getName();

				if (phno1.equals(phno2)) {
					// dur1=dur1+100;
					d1 = Integer.parseInt(dur1);
					d2 = Integer.parseInt(dur2);
					res = d1 + d2;
					// y.setFlag(1);
					//
					dur1 = Integer.toString(res);
				}

			}

			CallHistory callHistory2 = new CallHistory(cname, phno1, dur1);
			arraylist2.add(callHistory2);

		}
		Set<CallHistory> set = new HashSet<CallHistory>(arraylist2);
		arraylist3 = new ArrayList<CallHistory>(set);
		Collections.sort(arraylist3, new CallHistory());
		
		CallHistory abc;
		//List<Integer> length = new 
		for (int i = arraylist3.size() - 1; i >= 0; i--) {
			abc = arraylist3.get(i);
			
			if (abc.getDuration().length() == 3) {
				arraylist4.add(arraylist3.get(i));
			}
		}*/

		adapter.notifyDataSetChanged();
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		Log.d(TAG, "onLoaderReset()");
		// do nothing
	}
}
