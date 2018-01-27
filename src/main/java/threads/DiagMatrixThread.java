package threads;

import java.util.concurrent.CountDownLatch;

/**
 * @author GYZ
 * @DESCRIPTION Thread of building diagonal matrix The diagonal matrix only contains values on
 * diagonal So we can use a vector to represent the matrix and save the space cost
 * @create 2018-01-27 17:36
 **/
public class DiagMatrixThread extends Thread {

  /**
   * Use vector to represent the diagonal matrix
   */
  private double[] D;

  /**
   * similar matrix
   */
  private double[][] W;

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

  public DiagMatrixThread(double[] D, double[][] W, int start, int end,
      CountDownLatch threadsSignal) {
    this.W = W;
    this.D = D;
    this.start = start;
    this.end = end;
    this.threadsSignal = threadsSignal;
  }

  @Override
  public void run() {
    try {
      int i = start;
      for (; i < end; i++) {
        for (int j = 0; j < W.length; j++) {
          D[i] += W[i][j];
        }
        D[i] = 1.0 / Math.sqrt(D[i]);
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
