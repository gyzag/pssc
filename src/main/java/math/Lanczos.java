package math;

import smile.math.Math;
import smile.math.matrix.ColumnMajorMatrix;
import smile.math.matrix.DenseMatrix;
import smile.math.matrix.EigenValueDecomposition;

/**
 * @author GYZ
 * @DESCRIPTION Implement Lanczos method by com.github.haifengl:smile
 * Thanks to haifengl : https://github.com/haifengl
 * Introduction of Lanczos (from wiki)
 * The Lanczos algorithm is a direct algorithm devised by Cornelius Lanczos
 * that is an adaptation of power methods to find the most useful eigenvalues
 * and eigenvectors of an nth-order linear system with a limited number of
 * operations,  m, where m is much smaller than  n.
 * @create 2018-01-27 20:36
 **/
public class Lanczos {

  /**
   * Hermitian Matrix A
   */
  private double[][] A;

  /**
   * Number of clusters
   */
  private int k;

  public Lanczos(double[][] a, int k ) {
    this.A = a;
    this.k = k;
  }

  /*
    * 
    * Convert two dimensional arrays to smile.math.matrix.DenseMatrix
    * @author gyz
    * @date 2018/1/27 21:42
    * @param [A]  
    * @return smile.math.matrix.DenseMatrix  
    */  
  private DenseMatrix convertA2M(double[][] A){
    int n = A.length;
    DenseMatrix M = new ColumnMajorMatrix(n, n);
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        double l = A[i][j];
        M.set(i, j, l);
        M.set(j, i, l);
      }
    }
    return M;
  }

  /*
    * 
    * get the eigen vectors by Lanczos
    * @author gyz
    * @date 2018/1/27 21:06
    * @param [Array, Clusters]
    * @return eigen vectors
    */  
  public double[][] getEigenVectors(double[][] A, int k){
    DenseMatrix M = convertA2M(A);
    // Using smile.math.matrix.Lanczos
    EigenValueDecomposition eigen = smile.math.matrix.Lanczos.eigen(M, k);
    double[][] Y = eigen.getEigenVectors().array();
    for (int i = 0; i < A.length; i++) {
      Math.unitize2(Y[i]);
    }
    // return eigen vectors
    return Y;
  }
}
