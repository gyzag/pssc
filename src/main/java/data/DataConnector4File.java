package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author gyz
 * @DESCRIPTION
 * @create 2018-01-18 22:46
 **/
public class DataConnector4File implements DataConnectorFactory {

  private static Logger log = LoggerFactory.getLogger(DataConnector4File.class);

  /*
    *
    * extract general points' trajectory data from file
    * @author gyz
    * @date 2018/1/19 12:20
    * @param []
    * @return List of trajectory
    */
  @Override
  public List<List<Point>> getTrajData() throws IOException{
    // List of trajectory
    List<List<Point>> trajList = new ArrayList<>();
    List<Point> curList = new ArrayList<>();
    InputStreamReader isr = new InputStreamReader(
        new FileInputStream(getDataFile()));
    BufferedReader br = new BufferedReader(isr);
    //Skip the first line
    String line;
    br.readLine();
    String[] curLineInfo = null;
    String[] lastLineInfo;
    // count line
    int count = 0;
    // find the trajectory by judging field
    while((line = br.readLine()) != null){
      lastLineInfo = curLineInfo;
      curLineInfo = line.split(",");
      if (lastLineInfo == null)
        continue;
      if (curLineInfo[0].equals(lastLineInfo[0]))
      {
        if (count != 0)
        {
          Point point = new Point();
          // set x and y
          point.setX(Double.valueOf(lastLineInfo[2]));
          point.setY(Double.valueOf(lastLineInfo[3]));
          if (curLineInfo[6].equals("full")
              && lastLineInfo[6].equals("empty"))
          {
            // create a new trajectory
            curList = new ArrayList<Point>();
            curList.add(point);
          }
          else if (curLineInfo[6].equals("full")
              && lastLineInfo[6].equals("full"))
          {
            curList.add(point);
          }
          else if (curLineInfo[6].equals("empty")
              && lastLineInfo[6].equals("full"))
          {
            curList.add(point);
            // delete trajectory which contains only one point
            if (curList.size() > 1)
            {
              // add the trajectory into list
              trajList.add(curList);
            }
          }
        }
        count++;
      }
      else
        count = 0;
    }
    System.out.println("SUCCESS getting trajectory list");
    log.info("SUCCESS getting trajectory list");
    return  trajList;
  }

  /*
    *    
    * extract GPS points' trajectory data from file
    * @author gyz
    * @date 2018/1/19 17:08
    * @param []  
    * @return GPS points' trajectory
    */  
  @Override
  public List<List<GPSPoint>> getGPSTrajData() throws IOException {
    List<List<GPSPoint>> trajList = new ArrayList<>();
    List<GPSPoint> curList = new ArrayList<>();
    InputStreamReader isr = new InputStreamReader(
        new FileInputStream(getDataFile()));
    BufferedReader br = new BufferedReader(isr);
    //Skip the first line
    String line;
    br.readLine();
    String[] curLineInfo = null;
    String[] lastLineInfo;
    // count line
    int count = 0;
    // find the trajectory by judging field
    while((line = br.readLine()) != null){
      lastLineInfo = curLineInfo;
      curLineInfo = line.split(",");
      if (lastLineInfo == null)
        continue;
      if (curLineInfo[0].equals(lastLineInfo[0]))
      {
        if (count != 0)
        {
          GPSPoint point = new GPSPoint();
          // set x and y
          point.setCarId(Integer.valueOf(lastLineInfo[0]));
          point.setTimeStamp(lastLineInfo[1]);
          point.setX(Double.valueOf(lastLineInfo[2]));
          point.setY(Double.valueOf(lastLineInfo[3]));
          point.setCarDirect(Integer.valueOf(lastLineInfo[4]));
          point.setCarSpeed(Integer.valueOf(lastLineInfo[5]));
          point.setCarState(lastLineInfo[6]);
          if (curLineInfo[6].equals("full")
              && lastLineInfo[6].equals("empty"))
          {
            // create a new trajectory
            curList = new ArrayList<GPSPoint>();
            curList.add(point);
          }
          else if (curLineInfo[6].equals("full")
              && lastLineInfo[6].equals("full"))
          {
            curList.add(point);
          }
          else if (curLineInfo[6].equals("empty")
              && lastLineInfo[6].equals("full"))
          {
            curList.add(point);
            // delete trajectory which contains only one point
            if (curList.size() > 1)
            {
              // add the trajectory into list
              trajList.add(curList);
            }
          }
        }
        count++;
      }
      else
        count = 0;
    }
    System.out.println("SUCCESS getting GPS trajectory list");
    log.info("SUCCESS getting GPS trajectory list");
    return  trajList;
  }

  /*
    *    
    * get file from profile(pssc.properties)
    * @author gyz
    * @date 2018/1/19 13:36
    * @param []  
    * @return file
    */  
  private File getDataFile() {
    ClassLoader classLoader = this.getClass().getClassLoader();
    InputStream in = classLoader.getResourceAsStream("pssc.properties");
    Properties properties = new Properties();
    try{
      properties.load(in);
    }
    catch (IOException e){
      System.out.println("ERROR loading profile：pssc.properties");
      log.error("ERROR loading profile：pssc.properties");
      e.printStackTrace();
      System.exit(-1);
    }

    String fileName = properties.getProperty("dataFilePath");
    // you can modify and use your own path on disk
    URL url = classLoader.getResource(fileName);
    try {
      in.close();
    } catch (IOException e) {
      System.out.println("ERROR closing io: pssc.properties");
      log.error("ERROR closing io: pssc.properties");
      e.printStackTrace();
    }
    return new File(url.getFile());
  }

  public static void main(String[] args) throws IOException {
    DataConnector4File d = new DataConnector4File();
    System.out.println(d.getGPSTrajData());
  }
}
