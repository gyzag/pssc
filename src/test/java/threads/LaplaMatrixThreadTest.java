package threads;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author GYZ
 * @DESCRIPTION Test the speedup of building Lapalcian matrix
 * The actual speedup is depending on your hardware configuration
 * You can set threads' number according to your Core and CPU
 * @create 2018-01-29 15:49
 **/
public class LaplaMatrixThreadTest {
  /**
   *  Similar matrix (the input of building Laplacian matrix)
   */
  private static double[][] W;

  /**
   * Diagonal matrix (the input of building Laplacian matrix)
   */
  private static  double[] D;
  /**
   * The length of Laplacian Matrix
   */
  private static int LEN = 10000;

  // init data
  static {
    Random random = new Random();
    // init similar matrix
    W = new double[LEN][LEN];
    D = new double[LEN];
    for(int i = 0; i < LEN; i++){
      for(int j = 0; j <= i; j++){
        W[i][j] = random.nextDouble() * 100;
        W[j][i] = W[i][j];
      }
    }
    // init diagonal matrix
    for (int i = 0; i < LEN; i++) {
      for (int j = 0; j < LEN; j++) {
        try{
          D[i] += W[i][j];
        }
        catch (Exception e){
          System.out.println(i);
        }

      }
      D[i] = 1.0 / Math.sqrt(D[i]);
    }
  }

  /*
    * Calculate time cost of parallel building
    * Laplacian matrix
    * @author gyz
    * @date 2018/1/29 16:16
    * @param [thNum]  
    * @return long  
    */  
  private static long parallelBuildLaplaMatrix (int thNum) throws  IOException, InterruptedException{
    System.out.println("Starting parallel computing");
    long t1 = System.currentTimeMillis();
    double[][] L = new double[LEN][LEN];
    // Threads pool
    ExecutorService threadPool = Executors.newFixedThreadPool(thNum);
    CountDownLatch threadSignal = new CountDownLatch(thNum);
    // Starting calculating
    for(int i = 0; i < thNum; i++){
      threadPool.execute(new LaplaMatrixThread(L,  W, D, (i * LEN) / thNum,
          (i + 1) * LEN / thNum, threadSignal));
    }
    threadSignal.await();
    long t2 = System.currentTimeMillis();
    // Ending multi threading calculating
    System.out.println("Ending parallel calculating");
    threadPool.shutdown();
    return t2 - t1;
  }

  /*
    * Calculate time cost of serial building
    * Laplacian matrix
    * @author gyz
    * @date 2018/1/29 16:17
    * @param []  
    * @return long  
    */  
  private static long serialBuildLaplaMatrix() throws IOException, InterruptedException{
    System.out.println("Starting serial computing");
    long t1 = System.currentTimeMillis();
    double[][] L = new double[LEN][LEN];
    // init data
    CountDownLatch singleSignal = new CountDownLatch(1);
    Thread th = new LaplaMatrixThread(L, W, D, 0, LEN, singleSignal);
    th.start();
    singleSignal.await();
    long t2 = System.currentTimeMillis();
    System.out.println("Ending serial computing");
    return t2 - t1;
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("-----START TEST-----");
    long pTime = parallelBuildLaplaMatrix(8);
    long sTime = serialBuildLaplaMatrix();
    System.out.println("Speedup = " + 1.0 *  sTime / pTime );
    System.out.println("-----END TEST-------");
  }
}
