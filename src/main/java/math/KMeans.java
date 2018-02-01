package math;

/**
 * @author GYZ
 * @DESCRIPTION KMeans-cluster
 * @create 2018-01-27 21:32
 **/
public class KMeans {

  /**
   *  Numbers of clusters
   */
  private int k;

  /**
   *  Maximum number of iterations
   */
  private int max;

  /**
   * The Input matrix
   */
  private double[][] m;

  /**
   * The centers of clusters
   */
  private double[] center;

  /**
   *  The label of data
   */
  private int[] label;
}
