/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at
  http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
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
