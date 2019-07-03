package com.cs311.pa1;
import java.util.Random;

public class SlowMatrixFactory implements IMeasureFactory {

	 /**
     * This method creates an object that implements the IMeasurable interface
     * of the specified size.  The instance of the object is initialized with
     * random data.
     * 
     * @param size is the size of the object to create.
     * 
     * @return a new object that implements the IMeasureable interface.
     */
	@Override
    public IMeasurable createRandom(int size) {
		SlowMatrix New_matrix = new SlowMatrix(size);
        Random s = new Random();              
        for(int i = 0; i < size; i++)
            for (int j = 0; j< size; j++)
                    New_matrix.Matrix[i][j] = s.nextInt(5);
        return New_matrix;
    }
	
}
