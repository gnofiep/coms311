package com.cs311.pa1;
import java.util.ArrayList;
import java.util.List;

public class SlowMatrixTest {

	/**
	 * 1. Find the number of iterations required to multiply a 2 by 2 matrix so that the CPU times
	 *    required is approximately 2 milliseconds.
	 * 2. Determine the amount of time required to perform a matrix multiplication for sizes 2 to 100, using the number of iterations determined above for each measurement.
	 * 3. Finally output the number of iterations found in (1) above on a single line followed by pairs of numbers representing size and time in millisecond, one pair per line. Use spaces to delimit numbers, but do not put any other text in the output.
	 *
	**/
	
	 public static void main(String[] args) {
		 SlowMatrixFactory fac = new SlowMatrixFactory();
	        SlowMatrix matrix = (SlowMatrix) fac.createRandom(2);
	        MeasureTimeComplexity measureTime = new MeasureTimeComplexity();
	        List<Result> result = new ArrayList();
	         
	        /**
	         *  below are the variables to normalize and calibrate the cpu times 
	         *  required to 2 milliseconds
	         *  according to the pdf 
	         */
	        long millisecond = 2;
	        int startsize = 2;
	        int endsize = 2;//100
	        int stepsize = 1;
	        
	        result = (List<Result>) measureTime.measure(fac, measureTime.normalize(matrix, millisecond), startsize, endsize, stepsize);
	         
	        for (int i = 0 ; i < result.size(); i++)
	            System.out.println("size of matrix: "+result.get(i).getSize() + "  time: "+result.get(i).getTime());
//	          
//	        System.out.println(" ");
//	        System.out.println("end ");
//	        System.out.println(" ");
//	         
//	        for (int i = 0 ; i < result.size(); i++)
//	            System.out.println(result.get(i).getTime());
	    }
	
}
