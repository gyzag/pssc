package data;

import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author
 * @DESCRIPTION
 * @create 2018-01-20 16:18
 **/
public class DataConnectTest {
  private DataConnector dataConnector = DataConnector4File.getInstance();

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
    System.out.println("START TEST");
  }

  @After
  public void tearDown() {
    System.out.println("END TEST");
  }

  @Test
  public void testGetTrajData() throws IOException{
    System.out.println("TEST  DATA CONNECT  get trajectory list");
    List<List<Point>> trajList = dataConnector.getTrajData();
    System.out.println("List Length: " + trajList.size());
    System.out.println("Point0 X: " + trajList.get(0).get(0).getX());
    System.out.println("Point0 Y: " + trajList.get(0).get(0).getY());
  }

  @Test
  public void testGetGPSTrajData() throws IOException{
    System.out.println("TEST - DATA CONNECT -  get gps trajectory list");
    List<List<GPSPoint>> trajList = dataConnector.getGPSTrajData();
    System.out.println("List Length: " + trajList.size());
    System.out.println("Point1 X: " + trajList.get(0).get(1).getX());
    System.out.println("Point1 Y: " + trajList.get(0).get(1).getY());
    System.out.println("Point1 CarId: " + trajList.get(0).get(1).getCarId());
    System.out.println("Point1 CarDirect: " + trajList.get(0).get(1).getCarDirect());
    System.out.println("Point1 CarSpeed: " + trajList.get(0).get(1).getCarSpeed());
    System.out.println("Point1 getCarState(): " + trajList.get(0).get(1).getCarState());
  }
}
