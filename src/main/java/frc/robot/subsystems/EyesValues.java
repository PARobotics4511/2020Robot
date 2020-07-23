/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class EyesValues extends SubsystemBase {
  DigitalInput firstEye;
  int firstEyeIdx = 0;
  DigitalInput secondEye;
  int secondEyeIdx = 1;
  DigitalInput thirdEye;
  int thirdEyeIdx = 6;
  DigitalInput fourthEye;
  int fourthEyeIdx = 7;
  DigitalInput fifthEye;
  int fifthEyeIdx = 8;

  public EyesValues() {
    firstEye = new DigitalInput(firstEyeIdx); 
    secondEye = new DigitalInput(secondEyeIdx);
    thirdEye = new DigitalInput(thirdEyeIdx);
    fourthEye = new DigitalInput(fourthEyeIdx);
    fifthEye = new DigitalInput(fifthEyeIdx);
  }

  public boolean GetFirstEye(){
    return !firstEye.get();
  }
  public boolean GetSecondEye(){
    return !secondEye.get();
  }
  public boolean GetThirdEye(){
    return !thirdEye.get();
  }
  public boolean GetFourthEye(){
    return !fourthEye.get();
  }
  public boolean GetFifthEye(){
    return !fifthEye.get();
  }
  public int GetEyesNumbers(){
    int i = -2;
    if (!GetFirstEye() && !GetSecondEye() && !GetThirdEye() && !GetFourthEye()&& !GetFifthEye()){
      i = -1;
    }
    else if (!GetFirstEye() && !GetSecondEye() && !GetThirdEye() && !GetFourthEye() && GetFifthEye()){
      i = 0;
    }
    else if (GetFirstEye() && !GetSecondEye() && !GetThirdEye() && !GetFourthEye()){
      i = 1;
    }
    else if (GetFirstEye() && GetSecondEye() && !GetThirdEye() && !GetFourthEye()){
      i = 2;
    }
    else if (GetFirstEye() && GetSecondEye() && GetThirdEye() && !GetFourthEye()){
      i = 3;
    }
    else if (GetFirstEye() && GetSecondEye() && GetThirdEye() && GetFourthEye() && GetFifthEye()){
      i = 5;
    }
    else if (GetFirstEye() && GetSecondEye() && GetThirdEye() && GetFourthEye() && !GetFifthEye()){
      i = 4;
    }
    else if (GetFifthEye() && !GetFourthEye()){
      i = 6;
    }
    return i;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
