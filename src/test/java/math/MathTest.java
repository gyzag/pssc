package math;

import data.DataConnector;
import data.DataConnector4File;
import data.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

  /**
   * The test of DTW
   * @throws IOException
   */
  @Test
  public void testDTW() throws IOException{
    System.out.println("TEST MATH DTW");
    List<List<Point>> trajList = dataConnector.getTrajData();
    List<Point> traj1 = trajList.get(0);
    List<Point> traj2 = trajList.get(1);
    double dtwDis =DTW.getDTWDistance(traj1, traj2, 2);
    System.out.println("The DTW Distance is " + dtwDis);
  }

  /**
   *  The test of KMeans
   */
  @Test
  public void testKMeans(){
    System.out.println("TEST MATH KMeans");
    // Init test data
    int len = 5000;
    double[][] data = new double[len][2];
    Random random = new Random();
    for(int i = 0; i < len; i++){
      data[i][0] = random.nextDouble() * 100;
      data[i][1] = random.nextDouble() * 100;
    }
    // Execute Kmeans
    int k = 3;
    KMeans kmeans = new KMeans(data, k);
    // Verify result
    int[] labels = kmeans.getLabel();
    List<Integer> nums = new ArrayList<>();
    for(int i : labels)
      if(!nums.contains(i))
        nums.add(i);
    System.out.println("Correct answer is "  + k );
    System.out.println( "My answer is "+ nums.size());
  }
}
