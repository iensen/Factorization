package com.example.multithread;

import java.math.BigInteger;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity implements OnClickListener{
	
	private String test_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        setContentView(R.layout.activity_main);
        
        View reset_button = findViewById(R.id.reset_button);
        reset_button.setOnClickListener(this);
        
        View exit_button = findViewById(R.id.exit_button);
        exit_button.setOnClickListener(this);
        
        View submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		final EditText input_1 = (EditText) findViewById(R.id.number_1);
		EditText input_2 = (EditText) findViewById(R.id.number_2);
		EditText input_3 = (EditText) findViewById(R.id.number_3);
		EditText input_4 = (EditText) findViewById(R.id.number_4);
		EditText input_5 = (EditText) findViewById(R.id.number_5);
		
		Spinner spinner_algorithm_1 = (Spinner) findViewById(R.id.algorithm_1);
		Spinner spinner_algorithm_2 = (Spinner) findViewById(R.id.algorithm_2);
		Spinner spinner_algorithm_3 = (Spinner) findViewById(R.id.algorithm_3);
		Spinner spinner_algorithm_4 = (Spinner) findViewById(R.id.algorithm_4);
		Spinner spinner_algorithm_5 = (Spinner) findViewById(R.id.algorithm_5);
		
		switch (v.getId()){
		
			case R.id.exit_button:
				
				System.exit(0);
				
				break;
				
			case R.id.reset_button:
				
        		input_1.setText("");
        		input_2.setText("");
        		input_3.setText("");
        		input_4.setText("");
        		input_5.setText("");
        		
        		spinner_algorithm_1.setSelection(0);
        		spinner_algorithm_2.setSelection(0);
        		spinner_algorithm_3.setSelection(0);
        		spinner_algorithm_4.setSelection(0);
        		spinner_algorithm_5.setSelection(0);
        		
        		break;
			
			case R.id.submit_button:
				//Intent final_results_intent = new Intent(this, FinalResult.class);
        		//startActivity(final_results_intent);
        		
        		//Thread
        		Thread thread_one = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						test_string = "hello" + input_1.getText().toString();
						Log.v("Test", test_string);
					}
        			
        		});
        		thread_one.start();
        		
        		Thread thread_two = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.v("Test", "hello cpu");
					}
        			
        		});
        		thread_two.start();
        		
        		Thread thread_three = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.v("Test", "hello cpu");
					}
        			
        		});
        		thread_three.start();
        		
        		Thread thread_four = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.v("Test", "hello cpu");
					}
        			
        		});
        		thread_four.start();
        		
        		Thread thread_five = new Thread(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.v("Test", "hello cpu");
					}
        			
        		});
        		thread_five.start();
        		//Thread
        		
				Intent final_results_intent = new Intent();
				final_results_intent.putExtra("result_1", test_string);
				final_results_intent.putExtra("result_2", input_2.getText().toString());
				final_results_intent.putExtra("result_3", input_3.getText().toString());
				final_results_intent.putExtra("result_4", input_4.getText().toString());
				final_results_intent.putExtra("result_5", input_5.getText().toString());
				final_results_intent.setClass(MainActivity.this, FinalResult.class);
				startActivity(final_results_intent);
		}
	}
}
