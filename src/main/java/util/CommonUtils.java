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
    * Calculate the spatial distance between two point
    * @author gyz
    * @date 2018/1/20 18:04
    * @param [p1, p2]
    * @return double
    */
  public static double getSpatialDistance(Point p1, Point p2){
    // get latitude and longitude
    double p1Lat = p1.getX();
    double p1Lon = p1.getY();
    double p2Lat = p2.getY();
    double p2lon = p2.getY();

    double latDiff = p1Lat - p2Lat;
    double lonDiff = p1Lon - p2lon;

    double dis = 2 * Math
        .asin(Math.sqrt(Math.pow(Math.sin(latDiff / 2), 2) + Math.cos(p1Lat)
            * Math.cos(p2Lat) * Math.pow(Math.sin(lonDiff / 2), 2)));
    dis = dis * 6378.137;
    dis = Math.abs(dis);
    return dis;
  }

  /*
    *
    * Calculate the plane distance between two point
    * @author gyz
    * @date 2018/1/20 17:55
    * @param [p1, p2]
    * @return double
    */
  public static double getPlaneDistance(Point p1, Point p2){
    // get latitude and longitude
    double p1X = p1.getX();
    double p1Y = p1.getY();
    double p2X = p2.getX();
    double p2Y = p2.getY();

    double dis = Math.pow(Math.pow(p1X - p2X, 2) + Math.pow(p1Y - p2Y, 2), 0.5);
    return dis;
  }
}
