package linalg.concurrent;


import linalg.Matrix;

import java.util.ArrayList;
import java.util.List;


/**
 * An object which supports matrix addition using multiple threads.
 */
public class ConcurrentMatrixMultiplication {
    private final static int MAX_THREADS = Concurrency.PROCESSORS;
    private final int numThreads;

    private List<Thread> threadList;
    private int rowsPerThread;

    Matrix product;
    Matrix A, B;


    /**
     * Creates a ConcurrentMatrixMultiplication object to compute a matrix multiplication using multiple threads.
     *
     * @param A First matrix to multiply.
     * @param B Second matrix to multiply.
     */
    public ConcurrentMatrixMultiplication(Matrix A, Matrix B) {
        this.A = A;
        this.B = B;
        product = new Matrix(A.numRows(), B.numCols());
        threadList = new ArrayList<>();

        // Ensure we do not use more threads than there are rows.
        if(product.numRows() < MAX_THREADS) {
            numThreads = product.numRows();
        } else {
            numThreads = MAX_THREADS;
        }

        threadList = new ArrayList<>(numThreads);
    }


    /**
     * Multiplies two matrices using multi-threading.
     * @return The resulting matrix multiplication.
     */
    public Matrix mult() {
        allocateThreadsByRows(); // allocate the threads to portions of the matrix.

        for(Thread t : threadList) { // Join the threads together
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threadList.clear();
        A = B = null;

        return new Matrix(product);
    }


    private void allocateThreadsByRows() {
        rowsPerThread = product.numRows()/numThreads;
        int rowStart = 0;
        int rowEnd = rowsPerThread;

        for(int i=0; i<numThreads; i++) {
            threadList.add(new MatrixMultiplicationWorker(this, rowStart, rowEnd));
            threadList.get(i).start(); // Start thread
            rowStart = rowEnd;

            if(i==numThreads-2) {
                rowEnd = product.numRows();
            } else {
                rowEnd += rowsPerThread;
            }
        }
    }

}
