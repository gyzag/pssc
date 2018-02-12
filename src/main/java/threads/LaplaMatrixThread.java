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
  private double[][] l;
  /**
   * similar matrix
   */
  private double[][] w;
  /**
   * Use vector to represent the diagonal matrix
   */
  private double[] d;
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
    this.l = l;
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
      for(; i < end; i++){
        for(int j = 0; j <= i; j ++){
          l[i][j] = d[i] * w[i][j] * d[j];
          l[j][i] = l[i][j];
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
