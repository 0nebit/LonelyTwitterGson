package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.cs.lonelytwitter.data.GsonFileDataManager;
import ca.ualberta.cs.lonelytwitter.data.IDataManager;

public class LonelyTwitterActivity extends Activity {
	private Button button0;

	private IDataManager dataManager;

	private EditText bodyText;

	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets;

	private ArrayAdapter<Tweet> tweetsViewAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		dataManager = new GsonFileDataManager(this);

		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweets = dataManager.loadTweets();
		tweetsViewAdapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(tweetsViewAdapter);
		
		button0 = (Button) findViewById(R.id.summary_button);
		
		button0.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) {
				Intent intent = new Intent(LonelyTwitterActivity.this, SummaryActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("bundle", tweets);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	public void save(View v) {

		String text = bodyText.getText().toString();

		Tweet tweet = new Tweet(new Date(), text);
		tweets.add(tweet);

		tweetsViewAdapter.notifyDataSetChanged();

		bodyText.setText("");
		dataManager.saveTweets(tweets);
	}

	public void clear(View v) {

		tweets.clear();
		tweetsViewAdapter.notifyDataSetChanged();
		dataManager.saveTweets(tweets);
	}
}