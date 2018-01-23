package math;

import data.DataConnector;
import data.DataConnector4File;
import data.Point;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author GYZ
 * @DESCRIPTION
 * @create 2018-01-23 16:54
 **/
public class MathTest {

  private DataConnector dataConnector = DataConnector4File.getInstance();

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
  public void testDTW() throws IOException{
    System.out.println("TEST MATH DTW  get DTW distance");
    List<List<Point>> trajList = dataConnector.getTrajData();
    List<Point> traj1 = trajList.get(0);
    List<Point> traj2 = trajList.get(1);
    double dtwDis =DTW.getDTWDistance(traj1, traj2, 2);
    System.out.println("The DTW Distance is " + dtwDis);

  }
}
