package linalg;

public class SimpleMatrix {
    double[][] entries;

    public SimpleMatrix(double[][] entries) {
        this.entries = entries;
    }

    public SimpleMatrix mult(SimpleMatrix right) {
        double[][] product = new double[this.entries.length][right.entries[0].length];

        for(int i = 0; i < product.length; i++) {
            for(int k = 0; k < this.entries[0].length; k++) {
                for(int j = 0; j < product[0].length; j++) {
                    product[i][j] += this.entries[i][k] * right.entries[k][j];
                }
            }
        }

        return new SimpleMatrix(product);
    }
}
