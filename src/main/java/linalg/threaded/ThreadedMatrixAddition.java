package linalg.threaded;

import linalg.Matrix;
import linalg.complex_number.CNumber;


public class ThreadedMatrixAddition {

    private final static int MAX_THREADS = Runtime.getRuntime().availableProcessors();
    private final int numThreads;

    private Thread[] threadList;
    private int rowsPerThread;

    CNumber[][] sum;
    Matrix A, B;


    /**
     * Create a new object to compute the matrix addition of two matrices using multiple threads.
     * @param A First matrix to add.
     * @param B Second matrix to add.
     */
    ThreadedMatrixAddition(Matrix A, Matrix B) {
        this.A = A;
        this.B = B;
        sum = new CNumber[A.numRows()][A.numCols()];

        // Ensure we do not use more threads than there are rows.
        if(sum.length < MAX_THREADS) {
            numThreads = sum.length;
        } else {
            numThreads = MAX_THREADS;
        }

        threadList = new Thread[numThreads];
        rowsPerThread = sum.length/numThreads;
    }


    /**
     * Sums two matrices using multi-threading.
     * @return The resulting matrix sum.
     */
    public Matrix add() {
        int rowStart = 0;
        int rowEnd = rowsPerThread;

        for(int i=0; i<numThreads; i++) {
            threadList[i] = new MatrixAdditionRowWorker(this, rowStart, rowEnd);
            rowStart = rowEnd;

            if(i==numThreads-1) {
                rowEnd = sum.length;
            } else {
                rowEnd += rowsPerThread;
            }
        }

        for(Thread t : threadList) { // Start each thread.
            t.start();
        }

        for(Thread t : threadList) { // Join the threads together
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return new Matrix(sum);
    }


    public static void main(String[] args) {
        String shape = "10000x5000";
        Matrix A = Matrix.random(shape, -1000, 1000, false);
        Matrix B = Matrix.random(shape, -1000, 1000, false);

        final long startTime1 = System.currentTimeMillis();
        Matrix singleThreadSum = A.add(B);
        final long endTime1 = System.currentTimeMillis();


        final long startTime2 = System.currentTimeMillis();
        ThreadedMatrixAddition adder = new ThreadedMatrixAddition(A, B);
        Matrix MultiThreadSum = adder.add();
        final long endTime2 = System.currentTimeMillis();

        System.out.println("Standard: " + (endTime1 - startTime1) + " ms");
        System.out.println("Threaded: " + (endTime2 - startTime2) + " ms");
        System.out.println("Equivalent: " + singleThreadSum.equals(MultiThreadSum));
    }
}
