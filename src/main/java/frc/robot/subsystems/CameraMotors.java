/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;

public class CameraMotors extends SubsystemBase {
 
  TalonSRX justAMotor;
  private int justAMotorIdx = 2;

  public CameraMotors() {
// so the hawk tells you of how you're free,
// but who wrote that word and what does it mean?
// ~ Crackers the believer
    justAMotor = new TalonSRX(justAMotorIdx);

  }

  public void Orient(double XChange){
    
  }

  public void StopOrient(){
    justAMotor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
