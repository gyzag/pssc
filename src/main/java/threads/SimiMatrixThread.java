package threads;

import data.Point;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import math.DTW;

/**
 * @author GYZ
 * @DESCRIPTION Thread of building similar matrix
 * @create 2018-01-23 18:03
 **/
public class SimiMatrixThread  extends Thread{

  /**
   * Gauss kernel function's width
   */
  private int sigma;

  /**
   * similar matrix
   */
  private double[][] w;

  /**
   * the trajectory lisr
   */
  private  List<List<Point>> trajs;

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

  public SimiMatrixThread(int sigma, double[][] w, List<List<Point>> trajs, int start, int end,
      CountDownLatch threadsSignal) {
    this.sigma = sigma;
    this.w = w;
    this.trajs = trajs;
    this.start = start;
    this.end = end;
    this.threadsSignal = threadsSignal;
  }


  /*
    * 
    * Each thread is responsible for
    * calculating from its start to end
    * @author gyz
    * @date 2018/1/23 18:30
    * @param []  
    * @return void  
    */  
  @Override
  public void run(){
    try{
      double gamma = -0.5 / (sigma * sigma);
      int i = start;
      for(; i < end; i++){
        for(int j = 0; j <= i; j ++){
          if(j == i)
            w[i][j] = 1;
          else{
            // Calculate the DTW distance
            w[i][j] = Math.exp(gamma * DTW.getDTWDistance(trajs.get(i), trajs.get(j)));
            w[j][i] = w[i][j];
          }
        }
      }
    }

    catch (Exception e){
      e.printStackTrace();
    }
    // CountDown the CountDownLatch
    finally {
      threadsSignal.countDown();
    }
  }

}
