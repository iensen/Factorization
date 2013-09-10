package cs.ttu.vvclass.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TextView;
import ttu.cs.vvclass.R;

/**
 * Class implements the activity which displays the factorization results
 */
public class ResultDisplayActivity extends Activity implements OnClickListener {

	
	/** 
	 *The method is called when the activity is created
	 */
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		
		// initialize the window
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.final_results);

		//Initialize the button which sends the user to main activity
		View back_button = findViewById(R.id.back_button);
		back_button.setOnClickListener(this);

		//Initialize the TextViews where factorization results will be
		TextView factors[] = new TextView[] {
				(TextView) findViewById(R.id.factors_for_num_1),
				(TextView) findViewById(R.id.factors_for_num_2),
				(TextView) findViewById(R.id.factors_for_num_3),
				(TextView) findViewById(R.id.factors_for_num_4),
				(TextView) findViewById(R.id.factors_for_num_5) };

		//Get the intent containing the factorization results in its extras
		Intent previous_intent = getIntent();
		//update the text views
		for (int i = 0; i < factors.length; i++) {
			factors[i]
					.setText(previous_intent
							.getStringExtra(CommunicationConstants.extraDescriptions[i]));
		}
	}
/**
 * Handle click event
 * @param View contains the id of the button which was clicked
 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
        // there is only one button - back button!
		case R.id.back_button:
			// go back to main activity
			Intent mainIntent = new Intent(this, MainActivity.class);
			startActivity(mainIntent);
			this.finish();
			break;
		}
	}
}
