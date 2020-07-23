/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.ctre.phoenix.motorcontrol.can.*;

public class DriveTrain extends SubsystemBase {
    private static final int planetIdx = 1;
    private static final int nebulaIdx = 4;
    private static final int moonIdx = 2;
    private static final int starIdx = 3; 
    WPI_TalonSRX planet;
    WPI_TalonSRX moon;
    WPI_TalonSRX nebula;
    WPI_TalonSRX star;
    private MecanumDrive mecRobotDrive;

    private AnalogGyro gyro;
   
  public DriveTrain() {
     planet = new WPI_TalonSRX(planetIdx);
     moon = new WPI_TalonSRX(moonIdx);
     nebula = new WPI_TalonSRX(nebulaIdx);
     star = new WPI_TalonSRX(starIdx);
     mecRobotDrive = new MecanumDrive(planet, nebula, moon, star);
     gyro = new AnalogGyro(1);
  }

  public void RobotDrive(double lX, double lY, double rZ){
    //int nebulaPos = nebula.getSelectedSensorPosition();
    //int starPos = star.getSelectedSensorPosition();
    //System.out.println("nebula: " + nebulaPos);
    //System.out.println("star: " + starPos);
    mecRobotDrive.driveCartesian(lX, lY, rZ); 
  }

  public double getHeading()
  {
    double heading = gyro.getAngle();
    //System.out.println("Gyro Heading: " + heading);
    return heading;
  }

  public void gyroReset()
  {
    gyro.reset();
  }
  public void AutoDrive(){
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run.
  }
}


