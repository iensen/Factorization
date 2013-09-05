package com.example.multithread;

import java.math.BigInteger;
import java.util.ArrayList;
import com.example.algorithms.FactorizationAlgo;
import android.os.AsyncTask;

class FactorizationTask extends AsyncTask<Pair<BigInteger,FactorizationAlgo>, Void,Void > {

	private OnFactorizationTaskCompleteListener callback;
	
	FactorizationTask(OnFactorizationTaskCompleteListener callback) {
		this.callback=callback;
	}
	@Override
	protected Void doInBackground(
			Pair<BigInteger, FactorizationAlgo>... arg0) {
	  this.callback.onTaskComplete(getMultiplicationString(arg0[0].second.run(arg0[0].first)));
	  return null;	
	}


	/**
	 * get a string to be displayed as a result
	 * @param factors list of factors
	 * @return factors separated by '*'
	 */
	private String getMultiplicationString(ArrayList<BigInteger> factors) {
		StringBuilder result =new StringBuilder();
		boolean firstFactor=true;
		for (BigInteger factor:factors) {
			if(!firstFactor) {
				result.append("*");
			}
			result.append(factor.toString());
			firstFactor=false;
		}
		return result.toString();
	}
}
