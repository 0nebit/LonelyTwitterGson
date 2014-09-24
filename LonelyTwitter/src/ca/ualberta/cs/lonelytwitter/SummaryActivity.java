package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class SummaryActivity extends Activity {
	private ListView oldTweetsList;
	private ArrayList<Tweet> tweets;
	
	private ArrayList<String> display;
	private ArrayAdapter<String> adapter;
	
	private Summary sum;
	
	private Button button0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.summary);

		oldTweetsList = (ListView) findViewById(R.id.summary_list);
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		sum = new Summary(0, 0, 0);
		
		display = new ArrayList<String>();
		
		display.add("Number of tweets: " + Integer.valueOf(sum.get_num()));
		display.add("Average latency of tweets: " + Float.valueOf(sum.get_lat()));
		display.add("Average length of tweets: " + Integer.valueOf(sum.get_len()));

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, display);
		
		oldTweetsList.setAdapter(adapter);
		
		button0 = (Button) findViewById(R.id.button_back);
		
		button0.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) {
				Intent intent = new Intent(SummaryActivity.this, LonelyTwitterActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("bundle", tweets);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
}
