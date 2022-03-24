package linalg.concurrent;


/**
 * Worker thread for concurrent matrix multiplication.
 */
class MatrixMultiplicationWorker extends Thread {

    private final int BLOCK_SIZE = 100;
    private ConcurrentMatrixMultiplication manager;
    private int rowStart;
    private int rowEnd;


    /**
     * Create a worker thread for the matrix multiplication. This worker will compute
     * the block multiplication from the rows rowStart to rowEnd for all columns of the matrix.
     *
     * @param manager Managing {@link ConcurrentMatrixMultiplication} object.
     */
    public MatrixMultiplicationWorker(ConcurrentMatrixMultiplication manager, int rowStart, int rowEnd) {
        this.manager = manager;
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
    }


    public void run() {
        // TODO: Implementation.
        int n = manager.A.numCols();
        int m = manager.B.numCols();

        // Blocked matrix multiplication
        for(int kk=0; kk<n; kk+=BLOCK_SIZE) {
            for(int jj=0; jj<n; jj+=BLOCK_SIZE) {

                // Compute matrix multiplication for the block.
                for(int i=rowStart; i<rowEnd; i++) {
                    for(int k=kk; k<kk+BLOCK_SIZE && k<n; k++) {
                        for(int j=jj; j<jj+BLOCK_SIZE && j<m; j++) {
                            manager.product.entries[i][j].re += (manager.A.entries[i][k].re*manager.B.entries[k][j].re -
                                    manager.A.entries[i][k].im*manager.B.entries[k][j].im);
                            manager.product.entries[i][j].im += (manager.A.entries[i][k].re*manager.B.entries[k][j].im +
                                    manager.A.entries[i][k].im*manager.B.entries[k][j].re);
                        }
                    }
                }
            }
        }
    }
}
