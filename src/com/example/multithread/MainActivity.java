package com.example.multithread;

import java.math.BigInteger;
import java.util.ArrayList;

import com.example.algorithms.FactorizationAlgo;
import com.example.algorithms.FermatMethod;
import com.example.algorithms.PollardRo;
import com.example.algorithms.PrimitiveDivision;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Class implements main activity which will be running when the application
 * starts
 */

public class MainActivity extends Activity implements OnClickListener {

	/**
	 * Array of input boxes which store the numbers to be factored
	 */
	private EditText inputs[];

	/**
	 * Array of spinners which store the selections of the algorithms to be
	 * used. algorithmsSelections[i] contains the algorithm id which will be
	 * used to factor the number stored in inputs[i]
	 */
	private Spinner algorithmSelections[];

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		// set up listeners:
		View reset_button = findViewById(R.id.reset_button);
		reset_button.setOnClickListener(this);

		View exit_button = findViewById(R.id.exit_button);
		exit_button.setOnClickListener(this);

		View submit_button = findViewById(R.id.submit_button);
		submit_button.setOnClickListener(this);

		// initialize array of inputs (numbers to be factored)
		inputs = new EditText[] { (EditText) findViewById(R.id.number_1),
				(EditText) findViewById(R.id.number_2),
				(EditText) findViewById(R.id.number_3),
				(EditText) findViewById(R.id.number_4),
				(EditText) findViewById(R.id.number_5) };

		// initialize array of the spinners containing algorithm selections:
		algorithmSelections = new Spinner[] {
				(Spinner) findViewById(R.id.algorithm_1),
				(Spinner) findViewById(R.id.algorithm_2),
				(Spinner) findViewById(R.id.algorithm_3),
				(Spinner) findViewById(R.id.algorithm_4),
				(Spinner) findViewById(R.id.algorithm_5),
				};
	}

	private FactorizationAlgo getAlgorithmById(int id) {
		switch (id) {
		case 0:
			return new PrimitiveDivision();
		case 1:
			return new PollardRo();
		case 2:
			return new FermatMethod();
		default:
			return null;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {

		case R.id.exit_button:
			System.exit(0);
			break;

		case R.id.reset_button:
			// clear edit boxes
			for (EditText input : inputs) {
				input.setText("");
			}

			// clear algorithm selections
			for (Spinner algorithmSelection : algorithmSelections) {
				algorithmSelection.setSelection(0);
			}
			break;

		case R.id.submit_button:
			final String[] results = new String[inputs.length];
			try {
				final BigInteger[] integerInputs = parseInputs();
				for (int i = 0; i < inputs.length; i++) {
					final int index = i;
					final int algorithmId = algorithmSelections[i]
							.getSelectedItemPosition();
					FactorizationTask task = new FactorizationTask(
							new OnFactorizationTaskCompleteListener() {
								@Override
								public void onTaskComplete(String result) {
									results[index] = result;
								}
							});
					Pair<BigInteger, FactorizationAlgo> taskParams = new Pair<BigInteger, FactorizationAlgo>(
							integerInputs[i], getAlgorithmById(algorithmId));

					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
						task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
								taskParams);
					} else {
						task.execute(taskParams);
					}
				}
			} catch (NumberFormatException ex) {
				showAlertDialog("You should input numbers into all the edit boxes");
			}

			// Thread

			Intent final_results_intent = new Intent();
			for (int i = 0; i < inputs.length; i++) {
				final_results_intent
						.putExtra(CommunicationConstants.extraDescriptions[i],
								results[i]);
			}
			final_results_intent.setClass(MainActivity.this,
					ResultDisplayActivity.class);
			startActivity(final_results_intent);
		}

	}

	private void showAlertDialog(String message) {
		AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
		dlgAlert.setMessage(message);
		dlgAlert.setTitle("Factorization");
		dlgAlert.setPositiveButton("OK", null);
		dlgAlert.setCancelable(true);
		dlgAlert.create().show();
	}

	private BigInteger[] parseInputs() {
		BigInteger[] integerInputs = new BigInteger[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			integerInputs[i] = new BigInteger(inputs[i].getText().toString());
		}
		return integerInputs;
	}

}
