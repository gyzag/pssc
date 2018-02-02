package util;

import data.Point;

/**
 * @author GYZ
 * @DESCRIPTION Common utils
 * @create 2018-01-20 17:43
 **/
public class CommonUtils {
/*
  * 
  * calculate radian by degree
  * @author gyz
  * @date 2018/1/21 23:09
  * @param [degree]  
  * @return double  
  */  
  public static double getRadian(double degree) {
    return degree * Math.PI / 180.0;
  }

  /*
    *
    * Calculate the spatial distance between two point
    * @author gyz
    * @date 2018/1/20 18:04
    * @param [p1, p2]
    * @return double
    */
  public static double getSpatialDistance(Point p1, Point p2){
    // get latitude and longitude
    double p1Lat = getRadian(p1.getX());
    double p1Lon = p1.getY();
    double p2Lat = getRadian(p2.getX());
    double p2lon = p2.getY();

    double latDiff = p1Lat - p2Lat;
    double lonDiff = getRadian(p1Lon) - getRadian(p2lon);

    double dis = 2 * Math
        .asin(Math.sqrt(Math.pow(Math.sin(latDiff / 2), 2) + Math.cos(p1Lat)
            * Math.cos(p2Lat) * Math.pow(Math.sin(lonDiff / 2), 2)));
    dis = dis * 6378.137 * 1000;
    dis = Math.abs(dis);
    return dis;
  }

  /*
    *
    * Calculate the euclidean  distance between two point
    * @author gyz
    * @date 2018/1/20 17:55
    * @param [p1, p2]
    * @return double
    */
  public static double getEucliDistance(Point p1, Point p2){
    // get latitude and longitude
    double p1X = p1.getX();
    double p1Y = p1.getY();
    double p2X = p2.getX();
    double p2Y = p2.getY();

    double dis = Math.pow(Math.pow(p1X - p2X, 2) + Math.pow(p1Y - p2Y, 2), 0.5);
    return dis;
  }

  /*
    *
    * Calculate the distance between two vector
    * @author gyz
    * @date 2018/2/2 21:42
    * @param [x, y]  
    * @return double  
    */  
  public static double getVectorDistance(double[] x, double[] y){
    if(x.length != y.length)
      throw new IllegalArgumentException("x and y have different length");
    double res = 0;
    for(int i = 0; i < x.length ; i++)
      res += Math.pow(x[i] - y[i], 2);
    res /= x.length;
    return res;
  }

 /*
   * 
   * judge whether vector x is equal to vector y
   * @author gyz
   * @date 2018/2/2 22:38
   * @param [x, y]  
   * @return whether x is equal to y
   */  
  public static boolean isVectorEqual(int[] x, int[] y){
    if(x.length != y.length)
      return false;
    for(int i = 0; i < x.length ; i++){
      if(x[i] != y[i])
        return false;
    }
    return true;
  }
}
