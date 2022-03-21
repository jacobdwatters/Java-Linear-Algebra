package linalg.threaded;

import linalg.complex_number.CNumber;

public class MatrixAdditionRowWorker extends Thread {

    final int rowStart, rowEnd;
    final ThreadedMatrixAddition manager;


    /**
     * Create a worker thread for the matrix addition.
     *
     * @param manager
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     */
    public MatrixAdditionRowWorker(ThreadedMatrixAddition manager, int rowStart, int rowEnd) {
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.manager = manager;
    }


    /**
     * Sums the two matrices over the specified rows.
     */
    public void run() {
        for(int i=rowStart; i<rowEnd; i++) {
            for(int j=0; j<manager.sum[0].length; j++) {
                manager.sum[i][j] = new CNumber(manager.A.entries[i][j].re + manager.B.entries[i][j].re,
                        manager.A.entries[i][j].im + manager.B.entries[i][j].im);
            }
        }
    }
}
