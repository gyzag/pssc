package math;

import data.DataConnector4File;
import data.Point;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.CommonUtils;

/**
 * @author GYZ
 * @DESCRIPTION Dynamic time warping
 * From wikipedia
 * In time series analysis, dynamic time warping (DTW) is one of the algorithms for measuring
 * similarity between two temporal sequences, which may vary in speed. For instance, similarities
 * in walking could be detected using DTW, even if one person was walking faster than the other,
 * or if there were accelerations and decelerations during the course of an observation.
 * DTW has been applied to temporal sequences of video, audio, and graphics data — indeed,
 * any data that can be turned into a linear sequence can be analyzed with DTW.
 * A well known application has been automatic speech recognition, to cope with different speaking speeds.
 * Other applications include speaker recognition and online signature recognition.
 * Also it is seen that it can be used in partial shape matching application.
 * @create 2018-01-20 17:31
 **/
public class DTW {

  /**
   * log
   */
  private static final Logger log = LoggerFactory.getLogger(DTW.class);
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
