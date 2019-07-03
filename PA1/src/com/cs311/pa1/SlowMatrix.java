package com.cs311.pa1;

public class SlowMatrix implements IMatrix, IMeasurable {
	public int Matrix[][];

	public SlowMatrix(int size) {
		this.Matrix = new int[size][size];
	}

	public SlowMatrix() {
	}

	/**
	 * Creates a new Object that implements the IMatrix interface that is a
	 * submatrix of this matrix. The elements of the submatrix are deep copied.
	 * 
	 * @param upperLeftRow
	 *            is the upper left row of the submatrix, inclusive.
	 * @param upperLeftCol
	 *            is the upper left column of the submatrix, inclusive.
	 * @param lowerRightRow
	 *            is the lower right row of the submatrix, inclusive.
	 * @param lowerRightCol
	 *            is the lower right column of the submatrix, inclusive.
	 * 
	 * @return the new submatrix as an object that implements the IMatrix interface.
	 * 
	 * @throws IllegalArgumentException
	 *             if any of the arguments are out of bounds.
	 */
	@Override
	public IMatrix subMatrix(int upperLeftRow, int upperLeftCol, int lowerRightRow, int lowerRightCol)
			throws IllegalArgumentException {
		IMatrix New_submatrix = new SlowMatrix();
		int row_index = 0;
		int col_index = 0;
		for (int i = upperLeftCol; i < lowerRightCol + 1; i++) {
			for (int j = upperLeftRow; j < lowerRightRow + 1; j++) {
				New_submatrix.setElement(row_index, col_index, Matrix[i][j]);
				col_index++;
			}
			row_index++;
		}
		return New_submatrix;
	}

	/**
	 * Sets an element of the matrix.
	 * 
	 * @param row
	 *            is the row at which to set the element.
	 * @param col
	 *            is the column at which to set the element.
	 * @param val
	 *            is the value that must be a Number type or subclass of Number type
	 *            to set.
	 * 
	 * @throws IllegalArgumentException
	 *             if row or column is not a valid index into the matrix.
	 */
	@Override
	public void setElement(int row, int col, Number val) throws IllegalArgumentException {
		Matrix[row][col] = (int) val;
	}

	/**
	 * Returns the specified element in the matrix.
	 * 
	 * @param row
	 *            is the row at which to retrieve the element.
	 * @param col
	 *            is the column at which to retrieve the element.
	 * 
	 * @return the value of the element at the specified row and column.
	 * 
	 * @throws IllegalArgumentException
	 *             if row or column is out of bounds.
	 */
	@Override
	public Number getElement(int row, int col) throws IllegalArgumentException {
		return Matrix[row][col];
	}

	/**
	 * Performs a matrix multiplication of this matrix and the matrix given in the
	 * parameter. Note that the order of the multiplication is this matrix A
	 * multiplied by the parameter matrix B. That is A * B. The result is returned
	 * as a new IMatrix type. The multiplication need only run in n^3 asymptotic
	 * time.
	 * 
	 * @param mat
	 *            is the matrix that this matrix is multiplied by.
	 * 
	 * @return the resultant matrix of the multiplication.
	 * @throws IllegalArgumentException
	 *             if the sizes of the matricies are not compatible with
	 *             multiplication.
	 */
	/**
	 * N^3 time complexity for three for loops
	*/
	@Override
	public IMatrix multiply(IMatrix mat) throws IllegalArgumentException {
		SlowMatrix Mat = (SlowMatrix) mat;
		
		System.out.println("original:");
		print(Mat);
		
		int size = Mat.Matrix.length;
		SlowMatrix New_matrix = new SlowMatrix(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
//					New_matrix.Matrix[i][j] += Mat.Matrix[i][k] * this.Matrix[k][j];
					int val = New_matrix.getElement(i,j).intValue() + Mat.getElement(i, k).intValue() * this.getElement(k, j).intValue();
					New_matrix.setElement(i, j, val);
				}
			}
		}
		
		System.out.println("result:");
		print(New_matrix);
		
		return New_matrix;
	}

	/**
	 * Performs a matrix addition of this matrix and the matrix given in the
	 * parameter. The result is returned as a new IMatrix type. The matrix addition
	 * runs in n^2 asymptotic time.
	 * 
	 * @param mat
	 *            is the matrix to add
	 * 
	 * @return the resultant matrix of the addition
	 * 
	 * @throws IllegalArgumentException
	 *             if the sizes of the matricies are not compatible with addition.
	 */
	@Override
	public IMatrix add(IMatrix mat) throws IllegalArgumentException {
		SlowMatrix Mat = (SlowMatrix) mat;
		int size = Mat.Matrix.length;
		
		System.out.println("original:");
		print(Mat);
		
		SlowMatrix New_matrix = new SlowMatrix(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int val = Mat.getElement(i, j).intValue() + this.getElement(i, j).intValue();
				New_matrix.setElement(i, j, val);
			}
		}
		
		System.out.println("result:");
		print(New_matrix);
		
		return New_matrix;
	}

	/**
	 * ` The execute method contains code that is to be measured. Typically a simple
	 * call to the method that is to be measured in the object.
	 */
	@Override
	public void execute() {
		SlowMatrix matrix = new SlowMatrix();
		matrix.Matrix = this.Matrix;
		/**
		 * the lines below can be alter between the add and multiply methods in order to
		 * test for different result as indicate in the pdf
		 */
//		this.add(matrix);
		this.multiply(matrix);
	}
	public void print(SlowMatrix mat) {
		for(int i =0; i< mat.Matrix.length ; i++) {
			for(int j =0; j< mat.Matrix.length ; j++) {
				System.out.print(mat.Matrix[i][j]+ "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
