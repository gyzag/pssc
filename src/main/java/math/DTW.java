package math;

import data.Point;
import java.util.List;
import util.CommonUtils;

/**
 * @author GYZ
 * @DESCRIPTION Dynamic time warping
 * @create 2018-01-20 17:31
 **/
public class DTW {

/*
  * get DTW distance using dp
  * where w is set as default (2)
  * @author gyz
  * @date 2018/1/23 17:29
  * @param [traj1, traj2]
  * @return double
  */
  public static double getDTWDistance(List<Point>traj1, List<Point> traj2){
    double[][] DTWMatrix = buildDTWMatrix(traj1, traj2);
    return dynamicProgramming(DTWMatrix, 2);
  }

  /*
    *
    * get DTW distance using dp
    * @author gyz
    * @date 2018/1/23 16:47
    * @param [traj1, traj2, the weight of distance from (i-1, j-1) to (i, j)]
    * @return double
    */
  public static double getDTWDistance(List<Point> traj1, List<Point> traj2, double w){
    double[][] DTWMatrix = buildDTWMatrix(traj1, traj2);
    return dynamicProgramming(DTWMatrix, w);
  }

  /*
    * 
    * calculate the distance by dp, we only need to update the matrix
    * @author gyz
    * @date 2018/1/21 15:54
    * @param [DTWMatrix, the weight of distance from (i-1, j-1) to (i, j)]
    * @return double  
    */
  private static double dynamicProgramming(double[][] DTWMatrix, double w){
    int nRow = DTWMatrix.length;
    int nCol = DTWMatrix[0].length;
    for(int i = 1; i < nRow; i++){
      for(int j = 0; j < nCol; j++){
        if(j == 0)
          DTWMatrix[i][j] += DTWMatrix[i - 1][j];
        else
          DTWMatrix[i][j] += Math.min(Math.min(DTWMatrix[i - 1][j], DTWMatrix[i][j - 1]), w * DTWMatrix[i - 1][j - 1]);
      }
    }
    return DTWMatrix[nRow - 1][nCol - 1];
  }

  /*
  *
  * Build the DTW Matrix
  * @author gyz
  * @date 2018/1/21 15:46
  * @param [traj1, traj2]
  * @return double[][]
  */
  private static double[][] buildDTWMatrix(List<Point> traj1, List<Point> traj2) {
    // length of trajectory 1
    int l1 = traj1.size();
    // length of trajectory 2
    int l2 = traj2.size();
    double[][] DTWMatrix = new double[l1][l2];
    for (int i = 0; i < l1; i++) {
      for (int j = 0; j < l2; j++) {
        DTWMatrix[i][j] = CommonUtils.getSpatialDistance(traj1.get(i), traj2.get(j));
      }
    }
    return DTWMatrix;
  }

}
