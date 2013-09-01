package com.example.multithread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FinalResult extends Activity implements OnClickListener{
	
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.final_results);
		
		View back_button = findViewById(R.id.back_button);
		back_button.setOnClickListener(this);
		
		TextView factors_1 = (TextView) findViewById(R.id.factors_for_num_1);
		TextView factors_2 = (TextView) findViewById(R.id.factors_for_num_2);
		TextView factors_3 = (TextView) findViewById(R.id.factors_for_num_3);
		TextView factors_4 = (TextView) findViewById(R.id.factors_for_num_4);
		TextView factors_5 = (TextView) findViewById(R.id.factors_for_num_5);
		
		Intent previous_intent = getIntent();
		
		factors_1.setText(previous_intent.getStringExtra("result_1"));
		factors_2.setText(previous_intent.getStringExtra("result_2"));
		factors_3.setText(previous_intent.getStringExtra("result_3"));
		factors_4.setText(previous_intent.getStringExtra("result_4"));
		factors_5.setText(previous_intent.getStringExtra("result_5"));
        
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){
		//case R.id.exit_button:
			//System.exit(0);
			//break;
			
		case R.id.back_button:
			Intent mainIntent = new Intent(this, MainActivity.class);
			startActivity(mainIntent);
			this.finish();
    		break;
		}
	}
}
