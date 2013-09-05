package com.example.multithread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * Class implements the activity which displays the factorization result
 */
public class ResultDisplayActivity extends Activity implements OnClickListener {

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.final_results);

		View back_button = findViewById(R.id.back_button);
		back_button.setOnClickListener(this);

		TextView factors[] = new TextView[] {
				(TextView) findViewById(R.id.factors_for_num_1),
				(TextView) findViewById(R.id.factors_for_num_2),
				(TextView) findViewById(R.id.factors_for_num_3),
				(TextView) findViewById(R.id.factors_for_num_4),
				(TextView) findViewById(R.id.factors_for_num_5) };

		Intent previous_intent = getIntent();
		for (int i = 0; i < factors.length; i++) {
			factors[i]
					.setText(previous_intent
							.getStringExtra(CommunicationConstants.extraDescriptions[i]));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.back_button:
			// go back to main activity
			Intent mainIntent = new Intent(this, MainActivity.class);
			startActivity(mainIntent);
			this.finish();
			break;
		}
	}
}