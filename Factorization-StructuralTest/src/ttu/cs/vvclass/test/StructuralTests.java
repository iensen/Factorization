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
 * This class contains structural tests of Factorization APP that 
 * were build using robotium tool
 * 
 * @author Evgenii Balai
 * 
 */
public class StructuralTests extends
		ActivityInstrumentationTestCase2<MainActivity> {

	/* Object for communicating with robotium */
	private Solo solo;

	/*
	 * Constructor
	 */
	public StructuralTests() {
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
		 solo.goBack(); 
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
		 solo.goBack(); 
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
			 solo.goBack(); 
	
	}
	

	/**
	 * Use case 2 Scenario 1 test
	 */
	public void testU21() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "0", "4", "5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 3 };
		final String errorMessage = "The input numbers for factorization must be positive";
		runInvalidInput(inputs, algorithms, errorMessage);
		 solo.goBack(); 
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
		 solo.goBack(); 
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
        solo.sleep(1000);
		ensureEmptyness();
		 solo.goBack(); 
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

        solo.clickOnButton("Reset"); solo.sleep(1000);
		ensureEmptyness();
		 solo.goBack(); 
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
        solo.goBack(); 
	}
	
	/**
	 * State machine  Scenario 1 
	 */
	public void testSS1() {
		BigInteger[] inputs = createBigIntegerArray("1","2","3","4","5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
	    inputDataToEditBoxes(inputs);
	    solo.clickOnButton("Reset");
	    solo.sleep(1000);
		ensureEmptyness();
        //selectAlgorithms(algorithms);
		 solo.goBack(); 
	}
	
	/**
	 * State machine  Scenario 2 
	 */
	public void testSS2() {
		BigInteger[] inputs = createBigIntegerArray("1","2","3","4","5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };

	    selectAlgorithms(algorithms);
	    solo.clickOnButton("Reset");
	    solo.sleep(1000);
		ensureEmptyness();
		 solo.goBack(); 
      
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
	    solo.sleep(1000);
		ensureEmptyness();
		 solo.goBack(); 
      
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
	    solo.sleep(1000);
		ensureEmptyness();
		 solo.goBack(); 
      
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
	    solo.sleep(1000);
		ensureEmptyness();
		 solo.goBack(); 
      
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
		 solo.goBack(); 
      
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
		 solo.goBack(); 
	}
	
	/**
	 * Equivalence partitioning test #1, only 4 numbers in the input
	 */
	public void testEP1() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "3", "4");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
		final String errorMessage = "You should input numbers into all the edit boxes";
		runInvalidInput(inputs, algorithms, errorMessage);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #2, 0 input
	 */
	public void testEP2() {
		BigInteger[] inputs = createBigIntegerArray("5", "15", "0", "23", "10");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
		final String errorMessage = "The input numbers for factorization must be positive";
		runInvalidInput(inputs, algorithms, errorMessage);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #3, empty inputs
	 */
	public void testEP3() {
		BigInteger[] inputs = null;
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
		final String errorMessage = "You should input numbers into all the edit boxes";
		runInvalidInput(inputs, algorithms, errorMessage);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #4, Single digits prime numbers, Primitive
	 * Division Algorithm
	 */
	public void testEP4() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "3", "5", "7");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("1");
		expectedOutput[1] = createBigIntegerList("2");
		expectedOutput[2] = createBigIntegerList("3");
		expectedOutput[3] = createBigIntegerList("5");
		expectedOutput[4] = createBigIntegerList("7");
		int[] algorithms = new int[] { 1, 1, 1, 1, 1 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #5, Single digit prime numbers, Fermat
	 * Method Algorithm
	 */
	public void testEP5() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "3", "5", "7");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("1");
		expectedOutput[1] = createBigIntegerList("2");
		expectedOutput[2] = createBigIntegerList("3");
		expectedOutput[3] = createBigIntegerList("5");
		expectedOutput[4] = createBigIntegerList("7");
		int[] algorithms = new int[] { 2, 2, 2, 2, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #6, Single digit prime numbers, Pollard-Rho
	 * Method Algorithm
	 */
	public void testEP6() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "3", "5", "7");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("1");
		expectedOutput[1] = createBigIntegerList("2");
		expectedOutput[2] = createBigIntegerList("3");
		expectedOutput[3] = createBigIntegerList("5");
		expectedOutput[4] = createBigIntegerList("7");
		int[] algorithms = new int[] { 3, 3, 3, 3, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false); solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #7, Single digit prime numbers, A mixture
	 * of different algorithms
	 */
	public void testEP7() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "3", "5", "7");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("1");
		expectedOutput[1] = createBigIntegerList("2");
		expectedOutput[2] = createBigIntegerList("3");
		expectedOutput[3] = createBigIntegerList("5");
		expectedOutput[4] = createBigIntegerList("7");
		int[] algorithms = new int[] { 1, 2, 3, 2, 1 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #8, Single digit composite numbers,
	 * Primitive Division Algorithm
	 */
	public void testEP8() {
		BigInteger[] inputs = createBigIntegerArray("4", "6", "8", "9", "6");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "2");
		expectedOutput[1] = createBigIntegerList("2", "3");
		expectedOutput[2] = createBigIntegerList("2", "2", "2");
		expectedOutput[3] = createBigIntegerList("3", "3");
		expectedOutput[4] = createBigIntegerList("2", "3");
		int[] algorithms = new int[] { 1, 1, 1, 1, 1 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #9, Single digit composite numbers, Fermat
	 * Method Algorithm
	 */
	public void testEP9() {
		BigInteger[] inputs = createBigIntegerArray("4", "6", "8", "9", "4");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "2");
		expectedOutput[1] = createBigIntegerList("2", "3");
		expectedOutput[2] = createBigIntegerList("2", "2", "2");
		expectedOutput[3] = createBigIntegerList("3", "3");
		expectedOutput[4] = createBigIntegerList("2", "2");
		int[] algorithms = new int[] { 2, 2, 2, 2, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #10, Single digit composite numbers,
	 * Pollard-Rho Algorithm
	 */
	public void testEP10() {
		BigInteger[] inputs = createBigIntegerArray("4", "6", "8", "9", "8");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "2");
		expectedOutput[1] = createBigIntegerList("2", "3");
		expectedOutput[2] = createBigIntegerList("2", "2", "2");
		expectedOutput[3] = createBigIntegerList("3", "3");
		expectedOutput[4] = createBigIntegerList("2", "2", "2");
		int[] algorithms = new int[] { 3, 3, 3, 3, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #11, Single digit composite numbers, A
	 * mixture of different algorithms
	 */
	public void testEP11() {
		BigInteger[] inputs = createBigIntegerArray("4", "6", "8", "9", "9");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "2");
		expectedOutput[1] = createBigIntegerList("2", "3");
		expectedOutput[2] = createBigIntegerList("2", "2", "2");
		expectedOutput[3] = createBigIntegerList("3", "3");
		expectedOutput[4] = createBigIntegerList("3", "3");
		int[] algorithms = new int[] { 3, 2, 1, 2, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #12, composite numbers consisting of more
	 * than one digit, Primitive Division Algorithm
	 */
	public void testEP12() {
		BigInteger[] inputs = createBigIntegerArray("16", "100", "99", "20",
				"121");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "2", "2", "2");
		expectedOutput[1] = createBigIntegerList("2", "2", "5", "5");
		expectedOutput[2] = createBigIntegerList("3", "3", "11");
		expectedOutput[3] = createBigIntegerList("2", "2", "5");
		expectedOutput[4] = createBigIntegerList("11", "11");
		int[] algorithms = new int[] { 1, 1, 1, 1, 1 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #13, composite numbers consisting of more
	 * than one digit, Fermat Method Algorithm
	 */
	public void testEP13() {
		BigInteger[] inputs = createBigIntegerArray("16", "100", "99", "20",
				"121");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "2", "2", "2");
		expectedOutput[1] = createBigIntegerList("2", "2", "5", "5");
		expectedOutput[2] = createBigIntegerList("3", "3", "11");
		expectedOutput[3] = createBigIntegerList("2", "2", "5");
		expectedOutput[4] = createBigIntegerList("11", "11");
		int[] algorithms = new int[] { 2, 2, 2, 2, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #14, composite numbers consisting of more
	 * than one digit, Pollard-Rho Algorithm
	 */
	public void testEP14() {
		BigInteger[] inputs = createBigIntegerArray("16", "100", "99", "20",
				"121");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "2", "2", "2");
		expectedOutput[1] = createBigIntegerList("2", "2", "5", "5");
		expectedOutput[2] = createBigIntegerList("3", "3", "11");
		expectedOutput[3] = createBigIntegerList("2", "2", "5");
		expectedOutput[4] = createBigIntegerList("11", "11");
		int[] algorithms = new int[] { 3, 3, 3, 3, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #15, composite numbers consisting of more
	 * than one digit, A mixture of different algorithms
	 */
	public void testEP15() {
		BigInteger[] inputs = createBigIntegerArray("18", "105", "98", "36",
				"135");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "3", "3");
		expectedOutput[1] = createBigIntegerList("5", "3", "7");
		expectedOutput[2] = createBigIntegerList("2", "7", "7");
		expectedOutput[3] = createBigIntegerList("2", "2", "3", "3");
		expectedOutput[4] = createBigIntegerList("5", "3", "3", "3");
		int[] algorithms = new int[] { 1, 3, 2, 1, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #16, prime numbers consisting of more than
	 * one digit, Primitive Division Algorithm
	 */
	public void testEP16() {
		BigInteger[] inputs = createBigIntegerArray("11", "73", "79", "569",
				"929");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("11");
		expectedOutput[1] = createBigIntegerList("73");
		expectedOutput[2] = createBigIntegerList("79");
		expectedOutput[3] = createBigIntegerList("569");
		expectedOutput[4] = createBigIntegerList("929");
		int[] algorithms = new int[] { 1, 1, 1, 1, 1 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #17, prime numbers consisting of more than
	 * one digit, Fermat Method Algorithm
	 */
	public void testEP17() {
		BigInteger[] inputs = createBigIntegerArray("11", "73", "79", "569",
				"929");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("11");
		expectedOutput[1] = createBigIntegerList("73");
		expectedOutput[2] = createBigIntegerList("79");
		expectedOutput[3] = createBigIntegerList("569");
		expectedOutput[4] = createBigIntegerList("929");
		int[] algorithms = new int[] { 2, 2, 2, 2, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #18, prime numbers consisting of more than
	 * one digit, Pollard-Rho Algorithm
	 */
	public void testEP18() {
		BigInteger[] inputs = createBigIntegerArray("11", "73", "79", "569",
				"929");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("11");
		expectedOutput[1] = createBigIntegerList("73");
		expectedOutput[2] = createBigIntegerList("79");
		expectedOutput[3] = createBigIntegerList("569");
		expectedOutput[4] = createBigIntegerList("929");
		int[] algorithms = new int[] { 3, 3, 3, 3, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Equivalence partitioning test #19, prime numbers consisting of more than
	 * one digit, A mixture of different algorithms
	 */
	public void testEP19() {
		BigInteger[] inputs = createBigIntegerArray("1213", "13", "7187",
				"6037", "17");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("1213");
		expectedOutput[1] = createBigIntegerList("13");
		expectedOutput[2] = createBigIntegerList("7187");
		expectedOutput[3] = createBigIntegerList("6037");
		expectedOutput[4] = createBigIntegerList("17");
		int[] algorithms = new int[] { 3, 2, 1, 1, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #1, numbers consisting of one digit , A
	 * mixture of different algorithms
	 */
	public void testEVA1() {
		BigInteger[] inputs = createBigIntegerArray("1", "2", "3", "4", "5");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("1");
		expectedOutput[1] = createBigIntegerList("2");
		expectedOutput[2] = createBigIntegerList("3");
		expectedOutput[3] = createBigIntegerList("2", "2");
		expectedOutput[4] = createBigIntegerList("5");
		int[] algorithms = new int[] { 1, 2, 3, 1, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #2, numbers consisting of one digit , A
	 * mixture of different algorithms
	 */
	public void testEVA2() {
		BigInteger[] inputs = createBigIntegerArray("6", "7", "8", "9", "9");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("2", "3");
		expectedOutput[1] = createBigIntegerList("7");
		expectedOutput[2] = createBigIntegerList("2", "2", "2");
		expectedOutput[3] = createBigIntegerList("3", "3");
		expectedOutput[4] = createBigIntegerList("3", "3");
		int[] algorithms = new int[] { 3, 2, 1, 1, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #3, prime numbers consisting of 30 digits ,
	 * Primitive Division Algorithm
	 */
	public void testEVA3() {
		BigInteger[] inputs = createBigIntegerArray(
				"707212484964685845810343064231",
				"933828387973499888728986976561",
				"582284249328080821278976360441",
				"978905822172838099050079991099",
				"666947371757872524008165984399");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		// null means time -out!
		expectedOutput[0] = createBigIntegerList("707212484964685845810343064231");
		expectedOutput[1] = createBigIntegerList("933828387973499888728986976561");
		expectedOutput[2] = createBigIntegerList("582284249328080821278976360441");
		expectedOutput[3] = createBigIntegerList("978905822172838099050079991099");
		expectedOutput[4] = createBigIntegerList("666947371757872524008165984399");
		int[] algorithms = new int[] { 1, 1, 1, 1, 1 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #4, prime numbers consisting of 30 digits ,
	 * Fermat Method Algorithm
	 */
	public void testEVA4() {
		BigInteger[] inputs = createBigIntegerArray(
				"707212484964685845810343064231",
				"933828387973499888728986976561",
				"582284249328080821278976360441",
				"978905822172838099050079991099",
				"666947371757872524008165984399");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("707212484964685845810343064231");
		expectedOutput[1] = createBigIntegerList("933828387973499888728986976561");
		expectedOutput[2] = createBigIntegerList("582284249328080821278976360441");
		expectedOutput[3] = createBigIntegerList("978905822172838099050079991099");
		expectedOutput[4] = createBigIntegerList("666947371757872524008165984399");
		int[] algorithms = new int[] { 2, 2, 2, 2, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,false);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #5, prime numbers consisting of 30 digits ,
	 * Pollard - Rho Algorithm
	 */
	public void testEVA5() {
		BigInteger[] inputs = createBigIntegerArray(
				"707212484964685845810343064231",
				"933828387973499888728986976561",
				"582284249328080821278976360441",
				"978905822172838099050079991099",
				"666947371757872524008165984399");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("707212484964685845810343064231");
		expectedOutput[1] = createBigIntegerList("933828387973499888728986976561");
		expectedOutput[2] = createBigIntegerList("582284249328080821278976360441");
		expectedOutput[3] = createBigIntegerList("978905822172838099050079991099");
		expectedOutput[4] = createBigIntegerList("666947371757872524008165984399");
		int[] algorithms = new int[] { 3, 3, 3, 3, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #6, composite numbers that are a product of
	 * two primes , Primitive Division Algorithm
	 */
	public void testEVA6() {
		BigInteger[] inputs = createBigIntegerArray(
				"172844502721249440065613005959",
				"863320134106909528879134574091",
				"413715988845287811664603815217",
				"514089086636605010097889281431",
				"412287866876406967025392736257");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("415745718824941",
				"415745718824899");
		expectedOutput[1] = createBigIntegerList("929150221496459",
				"643207578348757");
		expectedOutput[2] = createBigIntegerList("643207578348781",
				"643207578348757");
		expectedOutput[3] = createBigIntegerList("717000060416053",
				"717000060416027");
		expectedOutput[4] = createBigIntegerList("642096462283061",
				"642096462283037");
		int[] algorithms = new int[] { 1, 1, 1, 1, 1 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #7, composite numbers that are a product of
	 * two primes with a small difference, Fermat Method
	 */
	public void testEVA7() {
		
		BigInteger[] inputs = createBigIntegerArray(
				"172844502721249440065613005959",
				"863320134106909528879134574091",
				"413715988845287811664603815217",
				"514089086636605010097889281431",
				"412287866876406967025392736257");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("415745718824941",
				"415745718824899");
		expectedOutput[1] = createBigIntegerList("929150221496459",
				"929150221496449");
		expectedOutput[2] = createBigIntegerList("643207578348781",
				"643207578348757");
		expectedOutput[3] = createBigIntegerList("717000060416053",
				"717000060416027");
		expectedOutput[4] = createBigIntegerList("642096462283061",
				"642096462283037");
	     
		
		
	     
		int[] algorithms = new int[] { 2, 2, 2, 2, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #8, composite numbers that are a product of
	 * two primes , Pollard-Rho Algorithm
	 */
	public void testEVA8() {
		BigInteger[] inputs = createBigIntegerArray(
				"172844502721249440065613005959",
				"863320134106909528879134574091",
				"413715988845287811664603815217",
				"514089086636605010097889281431",
				"412287866876406967025392736257");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("415745718824941",
				"415745718824899");
		expectedOutput[1] = createBigIntegerList("929150221496459",
				"643207578348757");
		expectedOutput[2] = createBigIntegerList("643207578348781",
				"643207578348757");
		expectedOutput[3] = createBigIntegerList("717000060416053",
				"717000060416027");
		expectedOutput[4] = createBigIntegerList("642096462283061",
				"642096462283037");
		int[] algorithms = new int[] { 3, 3, 3, 3, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #10, composite numbers that are a product of
	 * many small primes , Primitive Division Algorithm
	 */
	public void testEVA9() {
		BigInteger[] inputs = createBigIntegerArray(
				"160841709432652500000000000000",
				"372473904656441601562500000000",
				"363319032442408972272000000000",
				"296586965259109365120000000000",
				"353160442933515000000000000000");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("7", "5", "7", "2", "5", "3",
				"2", "2", "5", "5", "3", "7", "3", "3", "3", "7", "2", "2",
				"3", "2", "2", "5", "5", "2", "5", "2", "3", "5", "7", "3",
				"5", "5", "2", "2", "5", "5", "2", "3", "5", "7", "2", "3",
				"3", "5", "2", "7", "5", "3", "7", "3", "7", "5");
		expectedOutput[1] = createBigIntegerList("7", "7", "5", "2", "5", "7",
				"7", "2", "5", "2", "5", "5", "7", "7", "3", "5", "7", "5",
				"3", "5", "3", "5", "2", "2", "2", "5", "3", "5", "2", "5",
				"3", "5", "7", "3", "5", "7", "3", "7", "3", "3", "7", "5",
				"2", "5", "7", "7", "5");
		expectedOutput[2] = createBigIntegerList("3", "7", "5", "7", "2", "3",
				"5", "3", "7", "7", "3", "5", "7", "3", "2", "7", "3", "3",
				"2", "2", "2", "7", "7", "3", "2", "2", "3", "5", "5", "5",
				"3", "3", "7", "5", "2", "3", "3", "2", "7", "2", "7", "3",
				"2", "7", "2", "7", "7", "2", "5", "5", "7");
		expectedOutput[3] = createBigIntegerList("3", "2", "7", "3", "7", "3",
				"2", "3", "5", "3", "5", "3", "5", "2", "3", "7", "2", "5",
				"2", "7", "2", "2", "3", "2", "2", "2", "3", "7", "5", "2",
				"3", "5", "3", "3", "7", "2", "3", "2", "7", "3", "7", "5",
				"7", "2", "7", "5", "5", "5", "2", "2", "7", "7", "7");
		expectedOutput[4] = createBigIntegerList("7", "7", "5", "5", "7", "2",
				"3", "5", "5", "2", "2", "3", "2", "2", "5", "7", "2", "2",
				"2", "3", "5", "2", "7", "7", "5", "7", "2", "2", "7", "5",
				"5", "2", "7", "5", "5", "3", "2", "5", "3", "5", "7", "2",
				"5", "3", "2", "7", "5", "7", "5", "7");
		int[] algorithms = new int[] { 1, 1, 1, 1, 1 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #10, composite numbers that are a product of
	 * many small primes , Fermat Method
	 */
	public void testEVA10() {
		BigInteger[] inputs = createBigIntegerArray(
				"160841709432652500000000000000",
				"372473904656441601562500000000",
				"363319032442408972272000000000",
				"296586965259109365120000000000",
				"353160442933515000000000000000");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("7", "5", "7", "2", "5", "3",
				"2", "2", "5", "5", "3", "7", "3", "3", "3", "7", "2", "2",
				"3", "2", "2", "5", "5", "2", "5", "2", "3", "5", "7", "3",
				"5", "5", "2", "2", "5", "5", "2", "3", "5", "7", "2", "3",
				"3", "5", "2", "7", "5", "3", "7", "3", "7", "5");
		expectedOutput[1] = createBigIntegerList("7", "7", "5", "2", "5", "7",
				"7", "2", "5", "2", "5", "5", "7", "7", "3", "5", "7", "5",
				"3", "5", "3", "5", "2", "2", "2", "5", "3", "5", "2", "5",
				"3", "5", "7", "3", "5", "7", "3", "7", "3", "3", "7", "5",
				"2", "5", "7", "7", "5");
		expectedOutput[2] = createBigIntegerList("3", "7", "5", "7", "2", "3",
				"5", "3", "7", "7", "3", "5", "7", "3", "2", "7", "3", "3",
				"2", "2", "2", "7", "7", "3", "2", "2", "3", "5", "5", "5",
				"3", "3", "7", "5", "2", "3", "3", "2", "7", "2", "7", "3",
				"2", "7", "2", "7", "7", "2", "5", "5", "7");
		expectedOutput[3] = createBigIntegerList("3", "2", "7", "3", "7", "3",
				"2", "3", "5", "3", "5", "3", "5", "2", "3", "7", "2", "5",
				"2", "7", "2", "2", "3", "2", "2", "2", "3", "7", "5", "2",
				"3", "5", "3", "3", "7", "2", "3", "2", "7", "3", "7", "5",
				"7", "2", "7", "5", "5", "5", "2", "2", "7", "7", "7");
		expectedOutput[4] = createBigIntegerList("7", "7", "5", "5", "7", "2",
				"3", "5", "5", "2", "2", "3", "2", "2", "5", "7", "2", "2",
				"2", "3", "5", "2", "7", "7", "5", "7", "2", "2", "7", "5",
				"5", "2", "7", "5", "5", "3", "2", "5", "3", "5", "7", "2",
				"5", "3", "2", "7", "5", "7", "5", "7");
		int[] algorithms = new int[] { 2, 2, 2, 2, 2 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #10, composite numbers that are a product of
	 * many small primes , Pollard-Rho Algorithm
	 */

	public void testEVA11() {
		BigInteger[] inputs = createBigIntegerArray(
				"1608417094326525000000",
				"3724739046564416015625",
				"3633190324424089722720",
				"2965869652591093651200",
				"3531604429335150000000");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		expectedOutput[0] = createBigIntegerList("7", "5", "7", "3",
				"3", "7", "3", "3", "3", "7", 
				"3", "2", "3", "5", "7", "3",
				 "2", "2", "5", "5", "2", "3", "5", "7", "2", "3",
				"3", "5", "2", "7", "5", "3", "7", "3", "7", "5");
		expectedOutput[1] = createBigIntegerList("7", "7",  "7",
				"7",  "7", "7", "3",  "7",
				"3", "5", "3", "5",  "3", "5",
				"3", "5", "7", "3", "5","5","5", "7", "3", "7", "3", "3", "7", "5",
			    "5", "7", "7");
		expectedOutput[2] = createBigIntegerList("3", "7", "7", "3",
				"3", "7", "7", "3", "7", "3", "2", "7", "3", "3",
				"7", "7", "3", "2", "2", "3",  "5", 
				"3", "3", "7",  "2", "3", "3", "7", "7", "3",
				 "7","7", "7", "2",  "7");
		expectedOutput[3] = createBigIntegerList("3", "7", "3", "7", "3",
			 "3",  "3",  "3",  "3", "7", 
				 "7",   "3",  "2", "2", "3", "7", "5", "2",
				"3",  "3", "3", "7", "2", "3", "2", "7", "3", "7", "5",
				"7", "2", "7",   "2", "2", "7", "7", "7");
		expectedOutput[4] = createBigIntegerList("7", "7",  "7",
				"3",   "3", "5", "7", 
				 "3", "5", "2", "7", "7", "5", "7", "2", "2", "7", 
				"5", "2", "7", "5",  "3", "2", "3", "5", "7", "2",
				"5", "3", "2", "7", "7", "5", "7");
		int[] algorithms = new int[] { 3, 3, 3, 3, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
		 solo.goBack(); 
	}

	/**
	 * Extreme Value Analysis test #12, random composite numbers consisting of
	 * 30 digits
	 */
	public void testEVA12() {
		BigInteger[] inputs = createBigIntegerArray(
				"549873418025445696521276903947",
				"489180140894458575387764210381",
				"216488064302581628590413036959",
				"245691505279186890176283925361",
				"126647377709649845297644927601");
		@SuppressWarnings("unchecked")
		ArrayList<BigInteger> expectedOutput[] = new ArrayList[inputs.length];
		// add actual numbers, accept time out
		expectedOutput[0] = createBigIntegerList("90211826186057295527","7393","824477");
		expectedOutput[1] = createBigIntegerList("92309419743981367","529935235484");
		expectedOutput[2] = createBigIntegerList("92309419743981367","529935235484");
		expectedOutput[3] = createBigIntegerList("32603","6369436763","312968573501","3331");
		expectedOutput[4] = createBigIntegerList("67","20239115885714879983","872863","107");
		int[] algorithms = new int[] { 3, 2, 1, 2, 3 };
		ArrayList<BigInteger>[] results = runValidInput(inputs, algorithms);
		assertSortedEqual(results, expectedOutput,true);
	   solo.goBack(); 
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
		if(allowTimeOut) {
			solo.sleep(30000);
		} else {
			solo.sleep(5000);
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
			solo.sleep(1000);
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
