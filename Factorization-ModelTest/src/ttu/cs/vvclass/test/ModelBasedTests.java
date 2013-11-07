package ttu.cs.vvclass.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


import ttu.cs.vvclass.R;
import com.jayway.android.robotium.solo.Solo;
import cs.ttu.vvclass.activities.MainActivity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * This class contains black box tests of Factorization APP using robotium tool
 * 
 * @author Evgenii Balai
 * 
 */
public class ModelBasedTests extends
		ActivityInstrumentationTestCase2<MainActivity> {

	/* Object for communicating with robotium */
	private Solo solo;

	/*
	 * Constructor
	 */
	public ModelBasedTests() {
		// pass the class of the main activity
		super(MainActivity.class);
	}

	/**
	 * Implement set -up method
	 * 
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		   Intent i = new Intent();
		    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    setActivityIntent(i);
		    solo = new Solo(getInstrumentation(), getActivity());
	

	}

	/**
	 * Use case 1 Scenario 1 test
	 */
	public void testU11() {
		BigInteger[] inputs = createBigIntegerArray("1", "2");
		inputDataToEditBoxes(inputs);
		BigInteger[] actual = getDataFromEditBoxes();
		
		// check that the first two edit boxes contain same numbers as the numbers that were entered
		for(int i=0;i<inputs.length;i++) {
			assertEquals(inputs[i], actual[i]);
		}
		for(int i=inputs.length;i<actual.length;i++) {
			assertEquals(actual[i],null);
		}	
	}
	
	/**
	 * Use case 1 Scenario 2 test
	 */
	public void testU12() {
	
		EditText[] editBoxes = new EditText[] {
				(EditText) solo.getView(R.id.number_1),
				(EditText) solo.getView(R.id.number_2)};
	   
		solo.enterText(editBoxes[0],"a");
		solo.enterText(editBoxes[0],"b");
			
		BigInteger[] actual = getDataFromEditBoxes();

		for(int i=0;i<actual.length;i++) {
			assertEquals(actual[i],null);
		}	
	}
	
	/**
	 * Use case 1 Scenario 3 test
	 */
	public void testU13() {
			BigInteger[] inputs = createBigIntegerArray("1", "2","3","4","5");
			inputDataToEditBoxes(inputs);
			BigInteger[] actual = getDataFromEditBoxes();		
			// check that the first two edit boxes contain same numbers as the numbers that were entered
			for(int i=0;i<inputs.length;i++) {
				assertEquals(inputs[i], actual[i]);
			}
	
	}
	

	/**
	 * Use case 2 Scenario 1 test
	 */
	public void testU21() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "0", "4", "5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 3 };
		final String errorMessage = "The input numbers for factorization must be positive";
		runInvalidInput(inputs, algorithms, errorMessage);
	}
	

	/**
	 * Use case 2 Scenario 2 test
	 */
	public void testU22() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "3", "4", "5");
		int[] algorithms = new int[] { 1, 2, 3, 2, 3 };
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("1");
		expectedOutput[1] = createBigIntegerList("2");
		expectedOutput[2] = createBigIntegerList("3");
		expectedOutput[3] = createBigIntegerList("2","2");
		expectedOutput[4] = createBigIntegerList("5");
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
	}
	
	/**
	 * Use case 3 Scenario 1 test
	 */
	public void testU31() {
		BigInteger[] inputs = createBigIntegerArray("863320134106909528879134574091","1","2","4","6");
		int[] algorithms = new int[] { 1, 2, 3, 2, 3 };
	    inputDataToEditBoxes(inputs);
        selectAlgorithms(algorithms);
        // click on submit and wait until the result appear
        solo.clickOnButton("Submit");
        solo.clickOnButton("Reset");
		ensureEmptyness();
	}
	
	/**
	 * Use case 3 Scenario 2 test
	 */
	public void testU32() {
		BigInteger[] inputs = createBigIntegerArray("863320134106909528879134574091","1","2","4","6");
		int[] algorithms = new int[] { 1, 2, 3, 2, 3 };
	    inputDataToEditBoxes(inputs);
        selectAlgorithms(algorithms);
        // click on submit and wait until the result appear

        solo.clickOnButton("Reset");
		ensureEmptyness();
	}
	
	/**
	 * Use case 4 Scenario 1 test
	 */
	public void testU41() {
		BigInteger[] inputs = createBigIntegerArray("863320134106909528879134574091","1","2","4","6");
		int[] algorithms = new int[] { 1, 2, 3, 2, 3 };
	    inputDataToEditBoxes(inputs);
        selectAlgorithms(algorithms);
        solo.finishOpenedActivities();
	}
	
	/**
	 * State machine  Scenario 1 
	 */
	public void testSS1() {
		BigInteger[] inputs = createBigIntegerArray("1","2","3","4","5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
	    inputDataToEditBoxes(inputs);
	    solo.clickOnButton("Reset");
		ensureEmptyness();
        //selectAlgorithms(algorithms);
	}
	
	/**
	 * State machine  Scenario 2 
	 */
	public void testSS2() {
		BigInteger[] inputs = createBigIntegerArray("1","2","3","4","5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };

	    selectAlgorithms(algorithms);
	    solo.clickOnButton("Reset");
		ensureEmptyness();
      
	}
	
	/**
	 * State machine  Scenario 3 
	 */
	public void testSS3() {
		BigInteger[] inputs = createBigIntegerArray("1","2","3","4","5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
	    inputDataToEditBoxes(inputs);
	    selectAlgorithms(algorithms);
	    solo.clickOnButton("Reset");
		ensureEmptyness();
      
	}
	
	
	/**
	 * State machine  Scenario 4 
	 */
	public void testSS4() {
		BigInteger[] inputs = createBigIntegerArray("1","2","3","4","5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
	    selectAlgorithms(algorithms);
	    inputDataToEditBoxes(inputs);
	    solo.clickOnButton("Reset");
		ensureEmptyness();
      
	}
	
	/**
	 * State machine  Scenario 5 
	 */
	public void testSS5() {
		BigInteger[] inputs = createBigIntegerArray("863320134106909528879134574091","2","3","4","5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
	    selectAlgorithms(algorithms);
	    inputDataToEditBoxes(inputs);
	    solo.clickOnButton("Submit");
	    solo.clickOnButton("Reset");
		ensureEmptyness();
      
	}
	
	/**
	 * State machine  Scenario 6 
	 */
	public void testSS6() {
		BigInteger[] inputs = createBigIntegerArray("1","2","3","4","5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
	    selectAlgorithms(algorithms);
	    inputDataToEditBoxes(inputs);
	    solo.clickOnButton("Submit");
	    assertTrue(solo.waitForActivity("ResultDisplayActivity", 35000));
	    solo.clickOnButton("Go Back");
		ensureEmptyness();
      
	}
	
	/**
	 * State machine based on the shared object
	 */
	public void testSSO() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "3", "4", "5");
		int[] algorithms = new int[] { 1, 2, 3, 2, 3 };
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("1");
		expectedOutput[1] = createBigIntegerList("2");
		expectedOutput[2] = createBigIntegerList("3");
		expectedOutput[3] = createBigIntegerList("2","2");
		expectedOutput[4] = createBigIntegerList("5");
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
	}
	
	
	
		
	
	/**
	 * check if all the editboxes are empty 
	 */
	private void ensureEmptyness() {
		// create links to edit boxes
				EditText[] editBoxes = new EditText[] {
						(EditText) solo.getView(R.id.number_1),
						(EditText) solo.getView(R.id.number_2),
						(EditText) solo.getView(R.id.number_3),
						(EditText) solo.getView(R.id.number_4),
						(EditText) solo.getView(R.id.number_5) };

				// enter the numbers into the editboxes
				for (int i = 0; i < editBoxes.length; i++) {
					assertEquals(editBoxes[i].getText().toString(), "");
				}
	}
	/**
	 * Check if two arrays contains the same numbers (not necessarily in the
	 * same order)
	 * 
	 * @param results
	 *            first array
	 * @param expectedOutput
	 *            second array
	 * @param allowTimeOut
	 *         boolean variable, if being set to true, allows the execution
	 *         to time-out
	 */
	private void assertSortedEqual(ArrayList<BigInteger>[] results,
			ArrayList<BigInteger>[] expectedOutput, boolean allowTimeOut) {
		for (int i = 0; i < results.length; i++) {
			if(allowTimeOut && results[i]==null) {
				continue;
			}
			if (results[i] != null)
				Collections.sort(results[i]);
			if (expectedOutput[i] != null)
				Collections.sort(expectedOutput[i]);
			assertEquals(results[i], expectedOutput[i]);
		}
	}



	/**
	 * Create an ArrayList of BigInteger from a number of string arguments given
	 * in arg
	 * 
	 * @param arg
	 *            array of strings
	 * @return array of bigIntegers created from arg If arg[i]=null, result[i]
	 *         is also null
	 */
	private ArrayList<BigInteger> createBigIntegerList(String... arg) {
		return new ArrayList<BigInteger>(
				Arrays.asList(createBigIntegerArray(arg)));
	}

	/**
	 * Checks if two arrays contain the same multisets of numbers
	 * 
	 * @param array1
	 * @param array2
	 * @return true if the arrays contain the same multisets of numbers and
	 *         false otherwise
	 */
	boolean compareSorted(BigInteger[] array1, BigInteger[] array2) {

		if (array1.length != array2.length) {
			return false;
		}

		Arrays.sort(array1);
		Arrays.sort(array2);

		for (int i = 0; i < array1.length; i++) {
			if (!array1[i].equals(array2[i])) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Close all the activities
	 */
	@Override
	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();

	}

	/**
	 * Run the user input on APP
	 * 
	 * @param inputs
	 *            the numbers in the user input, must be of size 5 not
	 *            containing 0
	 * @param algorithmUserIds
	 *            algorithm ids selected by user 1 - Primitive Division 2 -
	 *            Fermat Method 3 - Pollard-Rho
	 * @return the set of outputs: factorization of each number in the inputs
	 *         Can contain Nulls, which means that the thread was time out.
	 */
	private ArrayList<BigInteger>[] runValidInput(BigInteger[] inputs,
			int[] algorithmUserIds) {

		try {
			setUp();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// create links to edit boxes
		EditText[] editBoxes = new EditText[] {
				(EditText) solo.getView(R.id.number_1),
				(EditText) solo.getView(R.id.number_2),
				(EditText) solo.getView(R.id.number_3),
				(EditText) solo.getView(R.id.number_4),
				(EditText) solo.getView(R.id.number_5) };

		// enter the numbers into the editboxes
		for (int i = 0; i < inputs.length; i++) {
			solo.clearEditText(editBoxes[i]);
			solo.enterText(editBoxes[i], inputs[i].toString());
		}

		selectAlgorithms(algorithmUserIds);

		// click on submit and wait until the result appear
		solo.clickOnButton("Submit");
		assertTrue(solo.waitForActivity("ResultDisplayActivity", 35000));

		// read the results from edit boxes
		TextView factorTextViews[] = new TextView[] {
				(TextView) solo.getView(R.id.factors_for_num_1),
				(TextView) solo.getView(R.id.factors_for_num_2),
				(TextView) solo.getView(R.id.factors_for_num_3),
				(TextView) solo.getView(R.id.factors_for_num_4),
				(TextView) solo.getView(R.id.factors_for_num_5) };

		// put the results into an array of bigIntegers
		// if time out was displayed instead of a result, put null instead
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger>[] result = new ArrayList[5];
		for (int i = 0; i < result.length; i++) {
			String factorization = factorTextViews[i].getText().toString();
			if (!factorization.startsWith("Time")) {
				result[i] = new ArrayList<BigInteger>();
				String[] factors = factorization.split("\\*");
				for (int j = 0; j < factors.length; j++) {
					result[i].add(new BigInteger(factors[j]));
				}
			}
		}

		// close the activities and return the result
		solo.finishOpenedActivities();
		
		return result;
	}

	private void selectAlgorithms(int[] algorithmUserIds) {
		// select algorithms in the spinners
		for (int i = 0; i < algorithmUserIds.length; i++) {
			// 0 is the first spinner in the layout
			View view1 = solo.getView(Spinner.class, i);
			solo.clickOnView(view1);
			solo.scrollToTop(); // I put this in here so that it always keeps
								// the list at start
			// select the 10th item in the spinner
			solo.clickOnView(solo.getView(TextView.class,
					algorithmUserIds[i] - 1));
		}
	}

	
	

	
  /**
   * Enter data into edit boxes of the main activity
   * @param inputs array of big integers containing the data
   * some of the inputs may be nulls
   */
	private void inputDataToEditBoxes(BigInteger[] inputs) {
		// if the inputs are not null, input them into edit boxes
		if (inputs != null) {
			EditText[] editBoxes = new EditText[] {
					(EditText) solo.getView(R.id.number_1),
					(EditText) solo.getView(R.id.number_2),
					(EditText) solo.getView(R.id.number_3),
					(EditText) solo.getView(R.id.number_4),
					(EditText) solo.getView(R.id.number_5) };

			// enter the numbers into the edit boxes
			for (int i = 0; i < inputs.length; i++) {
				solo.clearEditText(editBoxes[i]);
				solo.enterText(editBoxes[i], inputs[i].toString());
			}
		}
	}
	
	
	/**
	 * Retrieve data from the edit boxes of the main activity
	 * @return array of big integers containing the numbers entered to editboxes
	 * if editbox is empty, the corresponding biginteger is equal to null        
	 */
	private BigInteger[] getDataFromEditBoxes() {
		
		EditText[] editBoxes = new EditText[] {
				(EditText) solo.getView(R.id.number_1),
				(EditText) solo.getView(R.id.number_2),
				(EditText) solo.getView(R.id.number_3),
				(EditText) solo.getView(R.id.number_4),
				(EditText) solo.getView(R.id.number_5) };
		
		BigInteger[] numbers =new BigInteger[editBoxes.length];
		
		for(int i=0;i<editBoxes.length;i++) {
			try{
				BigInteger number=new BigInteger(editBoxes[i].getText().toString());
				numbers[i]=number;
			}
			catch(NumberFormatException ex){}
		}
		return numbers;
	}
	
	
	/**
	 * Create an array of BigInteger from a number of string arguments given in
	 * arg
	 * 
	 * @param arg
	 *            array of strings
	 * @return array of bigIntegers created from arg If arg[i]=Null, result[i]
	 *         is also null
	 */
	private BigInteger[] createBigIntegerArray(String... arg) {
		BigInteger[] result = new BigInteger[arg.length];
		for (int i = 0; i < arg.length; i++) {
			result[i] = new BigInteger(arg[i]);
		}
		return result;

	}
	
	/**
	 * Run the user input on APP
	 * 
	 * @param inputs
	 *            the numbers in the user input, must be invalid (i.e, some
	 *            alert box should appear with a message containing the
	 *            description of a problem)
	 * @param algorithmUserIds
	 *            algorithm ids selected by user 1 - Primitive Division 2 -
	 *            Fermat Method 3 - Pollard-Rho
	 * @param errrorMessage
	 *            the description of the occurred problem in the string
	 * @return the set of outputs: factorization of each number in the inputs
	 *         Can contain Nulls, which means that the thread was time out.
	 */
	private void runInvalidInput(BigInteger[] inputs, int[] algorithmUserIds,
			String errorMessage) {

		// if the inputs are not null, input them into edit boxes
		if (inputs != null) {
			EditText[] editBoxes = new EditText[] {
					(EditText) solo.getView(R.id.number_1),
					(EditText) solo.getView(R.id.number_2),
					(EditText) solo.getView(R.id.number_3),
					(EditText) solo.getView(R.id.number_4),
					(EditText) solo.getView(R.id.number_5) };

			// enter the numbers into the edit boxes
			for (int i = 0; i < inputs.length; i++) {
				solo.clearEditText(editBoxes[i]);
				solo.enterText(editBoxes[i], inputs[i].toString());
			}
		}

		selectAlgorithms(algorithmUserIds);

		// click on submit button
		solo.clickOnButton("Submit");

		// now, a alert box must appear, which the errorMessage on it
		assertTrue("Could not find the dialog!", solo.searchText(errorMessage));
		solo.finishOpenedActivities();
	}
}
