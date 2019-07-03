package com.cs311.pa1;
import java.util.List;
import java.util.ArrayList;

public class MeasureTimeComplexity implements IMeasureTimeComplexity, IMeasurable {
	/**
     * The execute method contains code that is to be measured.  Typically a
     * simple call to the method that is to be measured in the object.
     */
    public void execute() {
	}
    
    /**
     *  This method calibrates the measurement by computing the number of iterations
     *  of the execute method are required to match or exceed a specified time
     *  in milliseconds.
     * 
     * @param m is the IMeasureable object to run the execute method in.
     * 
     * @param timeInMilliseconds is the number of milliseconds to calibrate to. 
     * 
     * @return the number of iterations required for the execute method to run
     * in order to match or exceed the specified time given. (timeInMilliseconds)
     */
    public int normalize(IMeasurable m, long timeInMilliseconds) {
    	 int count = 0;
         long timePass = 0;
         long startTime = System.currentTimeMillis();
         while(timePass < timeInMilliseconds){
             count++;
             for(int i = 0; i < count; i++) {
                 m.execute();
             }
             timePass = System.currentTimeMillis()-startTime;
         }
         return count;
     }
    
    /**
     * The measure method is used measure the execution time of a sequence of
     * sizes.
     * 
     * @param factory is a IMeasureFactory implementation for creating instances of
     * random data of specified sizes.
     * 
     * @param nmeasures is the number of iterations to perform of the measurement function
     * (Usually the result returned from the normalize method.)
     * 
     * @param startsize is the initial size of the data to measure
     * @param endsize is the final size of the data to measure
     * @param stepsize is how much to change the size for each new data size.
     * 
     * @return an array list of IResult types that contains the size and time in
     * milliseconds the measurement took.
     */
    public List<? extends IResult> measure(IMeasureFactory factory, int nmeasures, int startsize, int endsize, int stepsize) {
        List<Result> result = new ArrayList();        
        SlowMatrix matrix = (SlowMatrix)factory.createRandom(startsize);
        int iteration = nmeasures;
        long startTime = 0;
        System.out.println("Iteration needed is " +iteration+ ".");
        System.out.println();
        for(int i = startsize; i <=endsize; i = i + stepsize) {
            matrix = (SlowMatrix)factory.createRandom(i);
            startTime = System.currentTimeMillis();
            for (int j = 0; j < iteration; j++) {
                matrix.execute();
            } 
            long finalTime =  System.currentTimeMillis() - startTime;
            Result res = new Result(i, finalTime);
            result.add(res);
        }
        return result;
    }

	
}
