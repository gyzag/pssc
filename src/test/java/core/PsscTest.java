package core;

import data.DataConnector;
import data.DataConnector4File;
import data.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GYZ
 * @DESCRIPTION
 * @create 2018-01-29 20:44
 **/
public class PsscTest {

  // init dataConnector
  private static DataConnector dataConnector = DataConnector4File.getInstance();


  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("----------START TEST----------");
    long t1 = System.currentTimeMillis();
    // init data
    List<List<Point>> trajs = dataConnector.getTrajData();
    int k = 4;
    Pssc pssc = new Pssc(k, trajs, 8);
    int[] labels = pssc.getLabels();
    List<Integer> nums = new ArrayList<>();
    for(int i : labels)
      if(!nums.contains(i))
        nums.add(i);
    System.out.println("Correct answer is "  + k );
    System.out.println( "My answer is "+ nums.size());
    System.out.println("----------END TEST----------");
    System.out.println(System.currentTimeMillis() - t1);
  }



}
