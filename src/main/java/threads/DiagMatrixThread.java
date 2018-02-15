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
  private double[] d;

  /**
   * The similar matrix
   */
  private double[][] w;

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

  /**
   * Constructor of DiagMatrixThread
   * @param d the diagonal matrix
   * @param w similar matrix
   * @param start the start point of thread's target
   * @param end the end point of thread's target
   * @param threadsSignal countDownLatch
   */
  public DiagMatrixThread(double[] d, double[][] w, int start, int end,
      CountDownLatch threadsSignal) {
    this.w = w;
    this.d = d;
    this.start = start;
    this.end = end;
    this.threadsSignal = threadsSignal;
  }

  @Override
  public void run() {
    try {
      int i = start;
      for (; i < end; i++) {
        for (int j = 0; j < w.length; j++) {
          d[i] += w[i][j];
        }
        d[i] = 1.0 / Math.sqrt(d[i]);
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
