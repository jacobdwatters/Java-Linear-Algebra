package linalg.concurrent;


/**
 * A class which has implementations for several thread allocation schemes for matrices.
 */
class AllocateThreads {

    private AllocateThreads() {
        throw new IllegalStateException("Utility class cannot be instantiated.");
    }

    /**
     * Allocates threads for synchronous computation of an operation on a matrix or matrices. These threads will work
     * on computing the operations resulting matrix by computing groups of rows concurrently on different threads.
     *
     * @param m The number of rows in the operations resulting matrix.
     * @param n The number of columns in the operations resulting matrix.
     * @param numThreads Number of threads to use in the computation.
     * @param type Type of threaded operation being computed.
     * @return An array of the threads to be using in the synchronous computation of the operation.
     */
    static Thread[] byRows(int m, int n, int numThreads, ThreadTypes type) {
        // TODO: Implementation
        return null;
    }


    /**
     * Allocates threads for synchronous computation of an operation on a matrix or matrices. These threads will work
     * on computing the operations resulting matrix by computing groups of columns concurrently on different threads.
     *
     * @param m The number of rows in the operations resulting matrix.
     * @param n The number of columns in the operations resulting matrix.
     * @param numThreads Number of threads to use in the computation.
     * @param type Type of threaded operation being computed.
     * @return An array of the threads to be using in the synchronous computation of the operation.
     */
    static Thread[] byCols(int m, int n, int numThreads, ThreadTypes type) {
        // TODO: Implementation
        return null;
    }


    /**
     * Allocates threads for synchronous computation of an operation on a matrix or matrices. These threads will work
     * on computing the operations resulting matrix by computing groups of rows and columns concurrently on different threads.
     *
     * @param m The number of rows in the operations resulting matrix.
     * @param n The number of columns in the operations resulting matrix.
     * @param numThreads Number of threads to use in the computation.
     * @param type Type of threaded operation being computed.
     * @return An array of the threads to be using in the synchronous computation of the operation.
     */
    static Thread[] byRowsAndCols(int m, int n, int numThreads, ThreadTypes type) {
        // TODO: Implementation
        return null;
    }
}
