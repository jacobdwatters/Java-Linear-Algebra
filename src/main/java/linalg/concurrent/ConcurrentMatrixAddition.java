package linalg.concurrent;

import linalg.Matrix;
import linalg.complex_number.CNumber;

import java.util.ArrayList;
import java.util.List;


/**
 * An object which supports matrix addition using multiple threads.
 */
public class ConcurrentMatrixAddition {

    private final static int MAX_THREADS = Concurrency.PROCESSORS;
    private final int numThreads;

    private List<Thread> threadList;
    private int rowsPerThread;

    CNumber[][] sum;
    Matrix A, B;


    /**
     * Create a new object to compute the matrix addition of two matrices using multiple threads.
     * @param A First matrix to add.
     * @param B Second matrix to add.
     */
    public ConcurrentMatrixAddition(Matrix A, Matrix B) {
        this.A = A;
        this.B = B;
        sum = new CNumber[A.numRows()][A.numCols()];

        // Ensure we do not use more threads than there are rows.
        if(sum.length < MAX_THREADS) {
            numThreads = sum.length;
        } else {
            numThreads = MAX_THREADS;
        }

        threadList = new ArrayList<>(numThreads);
    }


    /**
     * Sums two matrices using multi-threading.
     * @return The resulting matrix sum.
     */
    public Matrix add() {
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

        return new Matrix(sum);
    }



    // TODO: Improve the thread allocation scheme.
    /**
     * Allocates MatrixAdditionWorker threads to compute the sum over all columns and groups of rows.
     */
    private void allocateThreadsByRows() {
        rowsPerThread = sum.length/numThreads;
        int rowStart = 0;
        int rowEnd = rowsPerThread;

        for(int i=0; i<numThreads; i++) {
            threadList.add(new MatrixAdditionWorker(this, rowStart, rowEnd));
            threadList.get(i).start(); // Start thread
            rowStart = rowEnd;

            if(i==numThreads-2) {
                rowEnd = sum.length;
            } else {
                rowEnd += rowsPerThread;
            }
        }
    }
}
