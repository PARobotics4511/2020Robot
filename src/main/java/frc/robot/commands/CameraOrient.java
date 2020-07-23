/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.CameraInfo;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CameraOrient extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final CameraInfo cameraInfo;
  private final DriveTrain driveTrain;
  double XChange = 0;
  
  public CameraOrient(CameraInfo cI, DriveTrain cM) {
    cameraInfo = cI;
    driveTrain = cM;
    addRequirements(cameraInfo);
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
    cameraInfo.setCameraAligned(false);
    cameraInfo.ledOn();
    cameraInfo.snapshotOn();
  }

  @Override
  public void execute() { 
    cameraInfo.ledOn();
    cameraInfo.ShapeData();
    double Kp = -0.08; // was -0.065
    double minCommand = 0.09; // was 0.08
    double XOffset = cameraInfo.CameraX();
    double headingError = -XOffset;

    if(XOffset > 1){
      XChange = (Kp * headingError) - minCommand;
    }
    else if(XOffset < -1){
      XChange = (Kp * headingError) + minCommand;
    }

    double rZ = XChange;
    double lX = 0; 
    double lY = 0;
    driveTrain.RobotDrive(lX, lY, XChange); 
    cameraInfo.setCameraAligned(true);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return Math.abs(XChange) < 0.01;

  }
}
