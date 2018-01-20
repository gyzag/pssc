package data;
import java.io.Serializable;
/**
 * @author gyz
 * @DESCRIPTION GPS point
 * @create 2018-01-19 14:07
 **/
public class GPSPoint extends  Point implements Serializable{
  private static final long serialVersionUID = 1L;

  private int carId;

  private String timeStamp;

  private int carDirect;

  private int carSpeed;
  /**
   *  car's passenger state - empty or full
   */
  private String carState;

  public int getCarId() {
    return carId;
  }

  public void setCarId(int carId) {
    this.carId = carId;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public int getCarDirect() {
    return carDirect;
  }

  public void setCarDirect(int carDirect) {
    this.carDirect = carDirect;
  }

  public int getCarSpeed() {
    return carSpeed;
  }

  public void setCarSpeed(int carSpeed) {
    this.carSpeed = carSpeed;
  }

  public String getCarState() {
    return carState;
  }

  public void setCarState(String carState) {
    this.carState = carState;
  }
}
