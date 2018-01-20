package data;

import java.io.Serializable;

/**
 * @author gyz
 * @DESCRIPTION general point, must has X-axis  and  Y-axis
 * @create 2018-01-19 14:20
 **/
public class Point implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * X-axis
   */
  private  double x;
  /**
   * Y-axis
   */
  private  double y;

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }
}
