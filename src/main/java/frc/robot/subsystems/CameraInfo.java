/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class CameraInfo extends SubsystemBase {

  NetworkTable table;
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  boolean aligned = false;
  double x;
  double y;
  double area;

  public void Camera() { 
  }

  public void ShapeData(){
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
// 0.0 is the default value.
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    System.out.println("LimelightX: " + x);
    System.out.println("LimelightY" + y);
    System.out.println("LimelightArea" + area);
  }

  public double CameraX(){
    return x;
  }
  public double CameraY(){
    return y;
  }
  public double CameraArea(){
    return area;
  }
  public void setCameraAligned(boolean a){
    aligned = a;
  }
  public boolean cameraAligned(){
    return aligned;
  }

  public void ledOff()
  {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  }

  public void ledOn()
  {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  }
  public void snapshotOn()
  {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setNumber(1);
  }
  public void snapshotOff()
  {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("snapshot").setNumber(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
