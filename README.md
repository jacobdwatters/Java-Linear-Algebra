# Java-Linear-Algebra

This is a mostly complete linear algebra library for dense real and complex matrices and vectors implemented.

See the Javadoc for further useage and the methods/classes provided [here](https://jacobdwatters.github.io/Java-Linear-Algebra/).<br>

>This library allows the user do the following and more:
>- Create of complex numbers
>- Preform operations/arithmetic with complex numbers
>- Create of real or complex matrices and vectors
>- Preform various operations on matrices
>    - Add, subtract, multiply, scalar multiply
>    - determinant
>    - transpose
>    - complex conjugate
>    - inverse
>    - row reduced echelon forms
>- Manipulate matrices or vectors
>    - get/set individual values
>    - get/set slices of a matrix
>    - reshape/faltten matrix
>    - swap rows/columns
>- Check/compute various properties of matrices or Vectors
>    - number of rows/columns
>    - minimum/maximum entry (if matrix has complex entries this will be the value with minimum/maximum magnitude)
>    - Check if the matrix is a vector (i.e. either 1-by-1, a single row, or a single column)
>    - Check if matrix is symmetric/hermation
>    - Check if matrix is orthogonal/unitary
>    - Check if the matrix is triangular/diagonal
>    - Compute various norms of the matrix or vector
>    - Compute subspaces of the matrix
>    - Compute the eigenvalues/vectors
>    - Compute the singularvalues
>    - Check if the matrix is positive-definite
>- Matrix Decompositions
>    - LU decomposition
>    - LUP decomposition
>    - LUPQ decomposition
>    - Cholesky decomposition
>    - QR decomposition
>    - Schur decomposition
>   - Singular Value Decomposition
>- Solve systems of linear equations

### A Word Of Warning
It should be noted that, as of now, there has not been sufficent testing on this library and known bugs exist. This library was devloped as a personal project and does not aim to meet the accuracy, robustness, and efficiency of already established linear algebra libraries. A very small (but still non-zero) amount of effort has been put into efficency and numerical stability as that was not the main goal of developing this library. The primary was to create an easy to use and modular libray that facilites the aplication of linear algebra in a Java project.
