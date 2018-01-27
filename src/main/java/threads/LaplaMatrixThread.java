package threads;

import java.util.concurrent.CountDownLatch;

/**
 * @author GYZ
 * @DESCRIPTION Thread of building Laplacian matrix
 * @create 2018-01-27 18:54
 **/
public class LaplaMatrixThread extends  Thread{

  /**
   * Laplacian matrix
   */
  private double[][] L;
  /**
   * similar matrix
   */
  private double[][] W;
  /**
   * Use vector to represent the diagonal matrix
   */
  private double[] D;
  /**
   * the start point of thread's target
   */
  private int start;
  /**
   * the end point of thread's target
   */
  private int end;
  /**
   * CountDownLatch
   */
  private CountDownLatch threadsSignal;

  public LaplaMatrixThread(double[][] l, double[][] w, double[] d, int start, int end,
      CountDownLatch threadsSignal) {
    L = l;
    W = w;
    D = d;
    this.start = start;
    this.end = end;
    this.threadsSignal = threadsSignal;
  }

  @Override
  public void run() {
    try {
      int i = start;
      for(; i < end; i++){
        for(int j = 0; j <= i; j ++){
          L[i][j] = D[i] * W[i][j] * D[j];
          L[j][i] = L[i][j];
        }
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }
    finally {
      // CountDown the CountDownLatch
      threadsSignal.countDown();
    }
  }


}
