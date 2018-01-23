package Util;

import data.Point;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.CommonUtils;

/**
 * @author GYZ
 * @DESCRIPTION
 * @create 2018-01-21 22:53
 **/
public class UtilTest {
  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
    System.out.println("-----START TEST-----");
  }

  @After
  public void tearDown() {
    System.out.println("-----END TEST-------");
  }

  @Test
  public void testSpatialDistance(){
    System.out.println("TEST  UTILS  get Spatial distance");
    /**
     * Point1 represent Shanghai's latitude and longitude
     */
    Point p1 = new Point();
    p1.setX(121.471574);
    p1.setY(31.245194);
    /**
     * Point2 represent Beijing's latitude and longitude
     */
    Point p2 = new Point();
    p2.setX(116.417492);
    p2.setY(39.915599);
    double dis = CommonUtils.getSpatialDistance(p1, p2);
    System.out.println("Calculate the distance between Shanghai And Beijing");
    System.out.println("The distance is " + dis);
  }
}
