package math;

import java.util.Random;
import util.CommonUtils;

/**
 * @author GYZ
 * @DESCRIPTION KMeans-cluster
 * @create 2018-01-27 21:32
 **/
public class KMeans {

  /**
   *  Number of clusters
   */
  private int k;

  /**
   *  Maximum number of iterations
   */
  private int maxIter;

  /**
   * The Input matrix
   */
  private double[][] data;

  /**
   * The centroids of clusters
   */
  private double[][] centroids;

  /**
   * The number of elements in data
   */
  private int n;

  /**
   * The number of fields in each element
   */
  private int m;

  /**
   *  The label of data
   */
  private int[] label;

  /**
   *
   * @param data the Input matrix
   * @param k the number of clusters
   */
  public KMeans(double[][] data, int k){
    this(data, k, 100);
  }

  /**
   *
   * @param data The Input matrix
   * @param k Number of clusters
   * @param maxIter Maximum number of iterations
   */
  public KMeans(double[][] data, int k , int maxIter){
    this.data = data;
    this.k = k;
    this.maxIter = maxIter;
    this.n = data.length;
    this.m = data[0].length;
    this.centroids = new double[k][m];
    this.label = new int[n];

    // The first round of loop
    for(int i = 0; i < k; i++){
      // Pick the center randomly
      int j = new Random().nextInt(n);
      centroids[i] = data[j];
    }

    // The process of kmeans
    for(int o = 0; o < maxIter; o++){
      int[] newLabel = new int[n];
      for(int i = 0; i < n; i ++){
        double minDis = Double.MAX_VALUE;
        // Compute the type of each element
        for(int j = 0; j < k;j++){
          double dis = CommonUtils.getVectorDistance(data[i], centroids[j]);
          if(dis < minDis){
            minDis = dis;
            newLabel[i] = j;
          }
        }
      }
      // If there is no changes in result
      // End the loop ahead of time
      if(CommonUtils.isVectorEqual(label, newLabel))
        break;
      label = newLabel;
      // Update the centroids
      updateCentroids();
    }

  }

/*
  * 
  * Update the centroids
  * @author gyz
  * @date 2018/2/3 0:20
  * @param []  
  * @return void  
  */  
  private void updateCentroids(){
    // The number of element in each cluster
    int[] num = new int[k];
    // The new centroids
    double[][] newCentroids = new double[k][m];
    for(int i = 0; i < n; i++){
      int t = label[i];
      num[t]++;
      for(int j = 0; j < m; j++)
        newCentroids[t][j] += data[t][j];
    }
    for(int i = 0; i < k; i++)
      for(int j = 0; j < m; j ++)
        newCentroids[i][j] /= num[i];

    centroids = newCentroids;
  }

  public int getK() {
    return k;
  }

  public int getMaxIter() {
    return maxIter;
  }

  public int[] getLabel() {
    return label;
  }
}
