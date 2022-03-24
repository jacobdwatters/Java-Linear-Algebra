package linalg.concurrent;

import linalg.complex_number.CNumber;

public class MatrixAdditionWorker extends Thread {

    final int rowStart, rowEnd, colStart, colEnd;
    final ThreadedMatrixAddition manager;


    /**
     * Create a worker thread for the matrix addition. This worker will compute
     * the block sum from the rows rowStart to rowEnd for every column of the matrix.
     *
     * @param manager Managing {@link ThreadedMatrixAddition}
     * @param rowStart Starting row for the block addition.
     * @param rowEnd Ending row for the block addition.
     */
    public MatrixAdditionWorker(ThreadedMatrixAddition manager, int rowStart, int rowEnd) {
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.manager = manager;
        this.colStart = 0;
        this.colEnd = manager.A.numCols();
    }


    /**
     * Create a worker thread for the matrix addition. This worker will compute
     * the block sum from the rows rowStart to rowEnd and from the columns from colStart to colEnd.
     *
     * @param manager
     * @param rowStart
     * @param rowEnd
     * @param colStart
     * @param colEnd
     */
    public MatrixAdditionWorker(ThreadedMatrixAddition manager, int rowStart, int rowEnd, int colStart, int colEnd) {
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.manager = manager;
        this.colStart = colStart;
        this.colEnd = colEnd;
    }


    /**
     * Sums the two matrices over the specified rows.
     */
    public void run() {
        for(int i=rowStart; i<rowEnd; i++) {
            for(int j=colStart; j<colEnd; j++) {
                manager.sum[i][j] = new CNumber(manager.A.entries[i][j].re + manager.B.entries[i][j].re,
                        manager.A.entries[i][j].im + manager.B.entries[i][j].im);
            }
        }
    }
}
