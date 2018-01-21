package data;

import java.io.IOException;
import java.util.List;

/**
 * @author gyz
 * @DESCRIPTION The factory of data connector
 * @create 2018-01-18 22:50
 **/
public interface DataConnector {

   /**
    * extract general points' trajectory
    * @return general points' trajectory
    * @throws IOException
    */
   List<List<Point>> getTrajData() throws IOException;

   /**
    * extract GPS points' trajectory
    * @return gps points' trajectory
    * @throws IOException
    */
   List<List<GPSPoint>> getGPSTrajData() throws IOException;

}
