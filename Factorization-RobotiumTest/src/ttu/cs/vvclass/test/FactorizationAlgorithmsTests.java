package ttu.cs.vvclass.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import ttu.cs.vvclass.R;
import com.jayway.android.robotium.solo.Solo;
import cs.ttu.vvclass.activities.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FactorizationAlgorithmsTests extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public FactorizationAlgorithmsTests() {
		super( MainActivity.class);
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());

	}

	public void testFirst() {
		solo.assertCurrentActivity("wrong activity loaded", MainActivity.class);
		BigInteger []inputs=createBigIntegerArray("1","2","3","4","5");
		ArrayList<BigInteger> expectedOutput[]=new ArrayList[inputs.length];
		expectedOutput[0]=createBigIntegerList("1");
		expectedOutput[1]=createBigIntegerList("2");
		expectedOutput[2]=createBigIntegerList("3");
		expectedOutput[3]=createBigIntegerList("2","2");
		expectedOutput[4]=createBigIntegerList("5");
		int []algorithms = new int[]{0,1,2,0,1};
		ArrayList<BigInteger>[] results = runInput(inputs,algorithms);
	    
		for(int i=0;i<results.length;i++) {
			Collections.sort(results[i]);
			Collections.sort(expectedOutput[i]);
			assertEquals(results[i],expectedOutput[i]);
		}
	}

	private  BigInteger[] createBigIntegerArray(String ... arg) {
		BigInteger[] result=new BigInteger[arg.length];
		for(int i=0;i<arg.length;i++) {
			result[i]=new BigInteger(arg[i]);
		}
		return result;
		
	}
	
	private ArrayList<BigInteger> createBigIntegerList(String ...arg) {
		return new ArrayList<BigInteger>(Arrays.asList(createBigIntegerArray(arg)));
	}
	boolean compare(BigInteger[] array1, BigInteger[] array2) {
		
		if(array1.length != array2.length) {
			return false;
		}
		
		Arrays.sort(array1);
		Arrays.sort(array2);
		
		for(int i=0;i<array1.length;i++) {
			if(!array1[i].equals(array2[i])) {
				return false;
			}
		}
		
		return true;
		
	}
	@Override
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	private ArrayList<BigInteger>[]  runInput(BigInteger [] inputs, int[] algorithmUserIds) {
		EditText [] editBoxes = new EditText[] { (EditText) solo.getView(R.id.number_1),
				(EditText) solo.getView(R.id.number_2),
				(EditText) solo.getView(R.id.number_3),
				(EditText) solo.getView(R.id.number_4),
				(EditText) solo.getView(R.id.number_5) };
    
		for(int i=0;i<inputs.length;i++) {
			solo.clearEditText(editBoxes[i]);
			solo.enterText(editBoxes[i], inputs[i].toString());
		}
		
		for(int i=0;i<algorithmUserIds.length;i++) {
			// 0 is the first spinner in the layout
			View view1 = solo.getView(Spinner.class, i);
			solo.clickOnView(view1);
			solo.scrollToTop(); // I put this in here so that it always keeps the list at start
			// select the 10th item in the spinner
			solo.clickOnView(solo.getView(TextView.class, algorithmUserIds[i])); 
		}
		
		solo.clickOnButton("Submit");
		assertTrue(solo.waitForActivity("ResultDisplayActivity", 35000));
		TextView factorTextViews[] = new TextView[] {
				(TextView) solo.getView(R.id.factors_for_num_1),
				(TextView) solo.getView(R.id.factors_for_num_2),
				(TextView) solo.getView(R.id.factors_for_num_3),
				(TextView) solo.getView(R.id.factors_for_num_4),
				(TextView) solo.getView(R.id.factors_for_num_5) };
	
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger>[] result=new ArrayList[5];
		for(int i=0;i<result.length;i++) {
			String factorization=factorTextViews[i].getText().toString();
			if(!factorization.startsWith("Time")) {
				result[i] = new ArrayList<BigInteger>();
				String [] factors=factorization.split("\\*");
			    for(int j=0;j<factors.length;j++) {
			    	result[i].add(new BigInteger(factors[j]));
			    }
			}
		}
		return result;

	}

}
