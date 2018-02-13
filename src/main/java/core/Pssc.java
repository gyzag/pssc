package core;

import data.Point;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import math.KMeans;
import math.Lanczos;
import threads.DiagMatrixThread;
import threads.LaplaMatrixThread;
import threads.SimiMatrixThread;

/**
 * @author GYZ
 * @DESCRIPTION parallel spatiotemporal spectral clustering algorithm
 * @create 2018-01-23 17:48
 **/
public class Pssc {

  /**
   * The number of clusters.
   */
  private int k ;

  /**
   * The cluster labels of data.
   */
  private int[] labels;

  /**
   * The input trajectory data.
   */
  private List<List<Point>> data;

  /**
   * The number of threads.
   */
  private int thNum;

  /**
   * The width of Gaussian kernel.
   */
  private int sigma;

  public int getK() {
    return k;
  }

  public int[] getLabels() {
    return labels;
  }

  public int getThNum() {
    return thNum;
  }

  public int getSigma() {
    return sigma;
  }

  /**
   * Constructor of Parallel Spatiotemporal Spectral Cluster
   * @param k the cluster labels of data
   * @param data the input trajectory data
   * @throws InterruptedException
   */
  public Pssc(int k , List<List<Point>> data) throws InterruptedException{
    this(k, data, 12, 200);
  }

  /**
   * Constructor of Parallel Spatiotemporal Spectral Cluster
   * @param k the cluster labels of data
   * @param data the input trajectory data
   * @param thNum the number of threads
   * @throws InterruptedException
   */
  public Pssc(int k , List<List<Point>> data, int thNum) throws InterruptedException{
    this(k, data, 12, thNum);
  }

  /**
   * Constructor of Parallel Spatiotemporal Spectral Cluster
   * @param k the cluster labels of data
   * @param data the input trajectory data
   * @param thNum the number of threads
   * @param sigma the width of Gaussian kernel
   */
  public Pssc(int k , List<List<Point>> data, int thNum, int sigma) throws InterruptedException {
    if (k < 2) {
      throw new IllegalArgumentException("Invalid number of clusters: " + k);
    }
    if (thNum <= 0) {
      throw new IllegalArgumentException("Invalid number of threads: " + thNum);
    }
    if (sigma <= 0.0) {
      throw new IllegalArgumentException("Invalid width of Gaussian kernel: " + sigma);
    }
    this.k = k;
    this.data = data;
    this.thNum = thNum;
    this.sigma = sigma;
    int n = data.size();

    // similar matrix
    double[][] m = new double[n][n];
    // diagonal matrix
    double[] d = new double[n];
    // laplacian matrix
    double[][] l = new double[n][n];
    // Thread pool
    ExecutorService threadPool = Executors.newFixedThreadPool(thNum);
    System.out.println("----------Start building similar matrix-----------");
    CountDownLatch latch1 = new CountDownLatch(thNum);
    for(int i = 0; i < thNum; i++){
      threadPool.execute(new SimiMatrixThread(sigma, m, data, (i * n) / thNum,
          (i + 1) * n / thNum, latch1));
    }
    latch1.await();
    System.out.println("----------Start building diagonal matrix-----------");
    CountDownLatch latch2 = new CountDownLatch(thNum);
    for(int i = 0; i < thNum; i++){
      threadPool.execute(new DiagMatrixThread(d, m, (i * n) / thNum,
          (i + 1) * n / thNum, latch2));
    }
    latch2.await();
    System.out.println("----------Start building laplacian matrix-----------");
    CountDownLatch latch3 = new CountDownLatch(thNum);
    for(int i = 0; i < thNum; i++){
      threadPool.execute(new LaplaMatrixThread(l,  m, d, (i * n) / thNum,
          (i + 1) * n / thNum, latch3));
    }
    latch3.await();
    System.out.println("----------Start calculating eigenvectors-----------");
    Lanczos lanczos = new Lanczos(l, k);
    double[][] eigVec = lanczos.getEigenVectors();
    System.out.println("----------Start KMeans cluster-----------");
    KMeans kmeans = new KMeans(eigVec, k);
    this.labels = kmeans.getLabel();
    threadPool.shutdown();
  }
}
