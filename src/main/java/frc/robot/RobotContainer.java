/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final Shooter shooter = new Shooter();
  private final CameraInfo cameraInfo = new CameraInfo();
  private final DriveTrain driveTrain = new DriveTrain();
  private final Belt belt = new Belt();
  private final EyesValues eyesValues = new EyesValues();
  private final Climber climber = new Climber();
  
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final CameraOrient cameraOrient = new CameraOrient(cameraInfo, driveTrain);
  private final Intake intake = new Intake(belt, eyesValues);
  private final IntakeStop intakeStop = new IntakeStop(belt);
  private final IntakeReverse intakeReverse = new IntakeReverse(belt);
  private final Ascend ascend = new Ascend(climber);
  private final Descend descend = new Descend (climber);
  private final ClimbStop ascendStop = new ClimbStop (climber);
  private final LiftoffLeftReverse liftoffLeftReverse = new LiftoffLeftReverse(climber);
  private final LiftoffRightReverse liftoffRightReverse = new LiftoffRightReverse(climber);
  private final AutoCommand autoCommand = new AutoCommand(driveTrain, belt, eyesValues, cameraInfo, shooter);
  private final AutoCameraOrient autoCameraOrient = new AutoCameraOrient(cameraInfo, driveTrain);
  private final GeorgeDrop georgeDrop = new GeorgeDrop(belt);
  private final SmallAutoCommand smallAutoCommand = new SmallAutoCommand(driveTrain, belt, eyesValues, cameraInfo, shooter);




  private Joystick drivePad;
  private int drivePadIdx = 0;
  private int driveLXaxis = 0; //was zero
  private int driveLYaxis = 1; //was 1
  private int driveRXaxis = 4; //was 4  
  private int driveRZaxis = 2;
  private final ShootStop shootStop = new ShootStop(shooter);
  private Joystick joy;
  private int joyIdx = 1;

  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    drivePad = new Joystick(drivePadIdx);
    //Default Drive Train
    driveTrain.setDefaultCommand(new Drive(driveTrain,
    () -> drivePad.getRawAxis(driveLXaxis),
    () -> drivePad.getRawAxis(driveLYaxis),
    () -> drivePad.getRawAxis(driveRZaxis)));

    //Default WaterWheel to help 1st ball into correct position
    shooter.setDefaultCommand(new HoldFirstBall(shooter, eyesValues));

    JoystickButton drivePadLB = new JoystickButton(drivePad, 5);
    JoystickButton drivePadRB = new JoystickButton(drivePad, 6);
    JoystickButton drivePadLT = new JoystickButton(drivePad, 7);
    JoystickButton drivePadRT = new JoystickButton(drivePad, 8);
    JoystickButton drivePadX = new JoystickButton(drivePad, 1);
    JoystickButton drivePadA = new JoystickButton(drivePad, 2);
    JoystickButton drivePadBack = new JoystickButton(drivePad, 9);
    JoystickButton drivePadStart = new JoystickButton(drivePad, 10);
    JoystickButton drivePadB = new JoystickButton(drivePad, 3);
    JoystickButton drivePadY = new JoystickButton(drivePad, 4);
    


    //Reverse of Climber Winches
    drivePadY.whileHeld(new LiftoffLeftReverse(climber));
    drivePadY.whenReleased(new LiftoffStopLeft(climber));

    drivePadB.whileHeld (new LiftoffRightReverse(climber));
    drivePadB.whenReleased(new LiftoffStopRight(climber));

    //Shooter on drivePad
    drivePadRT.whileHeld(new Shoot(shooter, eyesValues));
    drivePadRT.whenReleased(new ShootStop(shooter));

    drivePadLT.whileHeld(new CameraOrient(cameraInfo, driveTrain));
    drivePadLT.whenReleased(new CameraLightOff(cameraInfo));

    //Intake on drivePad
    drivePadRB.whileHeld(new Intake(belt, eyesValues));
    drivePadRB.whenReleased(new IntakeStop(belt));
    drivePadLB.whileHeld(new IntakeReverse(belt));
    drivePadLB.whenReleased(new IntakeStop(belt));

    //Climber Winches on drivePad
    drivePadBack.whileHeld(new LiftoffLeft(climber));
    drivePadBack.whenReleased(new LiftoffStopLeft(climber));

    drivePadStart.whileHeld(new LiftoffRight(climber));
    drivePadStart.whenReleased(new LiftoffStopRight(climber));

    //Hooks on drivePad
    drivePadY.whileHeld(new Ascend(climber));
    drivePadY.whenReleased(new ClimbStop(climber));
    drivePadB.whileHeld(new Descend(climber));
    drivePadB.whenReleased(new ClimbStop(climber));





  }

  private void configureButtonBindings() {
    joy = new Joystick(joyIdx);
    JoystickButton joyTrig = new JoystickButton(joy, 1);
    JoystickButton joyTop = new JoystickButton(joy, 2);
    JoystickButton joy3 = new JoystickButton(joy, 3);
    JoystickButton joy4 = new JoystickButton(joy, 4);
    JoystickButton joy5 = new JoystickButton(joy, 5);
    JoystickButton joy6 = new JoystickButton (joy, 6);
    JoystickButton joy7 = new JoystickButton (joy, 7);
    JoystickButton joy8 = new JoystickButton(joy, 8);
    JoystickButton joy9 = new JoystickButton(joy, 9);
    JoystickButton joy11 = new JoystickButton(joy, 11);
    //Trigger joy671011 = new JoystickButton(joy, 11).and(new JoystickButton(joy, 10)).and(new JoystickButton(joy, 6)).and(new JoystickButton(joy, 7));
    
    //Shooter
    joyTrig.whileHeld(new Shoot(shooter, eyesValues));
    joyTrig.whenReleased(new ShootStop(shooter));
    joy3.whileHeld(new CameraOrient(cameraInfo, driveTrain));
    joy3.whenReleased(new CameraLightOff(cameraInfo));

    //Climber Winches
    joy8.whileHeld(new LiftoffLeft(climber));
    joy8.whenReleased(new LiftoffStopLeft(climber));

    joy9.whileHeld(new LiftoffRight(climber));
    joy9.whenReleased(new LiftoffStopRight(climber));

    //Climber Hooks
    joy4.whileHeld(new Ascend(climber));
    joy4.whenReleased(new ClimbStop(climber));
    joy5.whileHeld(new Descend(climber));
    joy5.whenReleased(new ClimbStop(climber));

    //Intake
    joy6.whileHeld(new Intake(belt, eyesValues));
    joy6.whenReleased(new IntakeStop(belt));
    joy7.whileHeld(new IntakeReverse(belt));
    joy7.whenReleased(new IntakeStop(belt));

    //Drive Test
    joy11.whileHeld(new AutoDrive(driveTrain, 0, 0.75, 0).withTimeout(2));
  }
  public Command getAutonomousCommand() {
    //return smallAutoCommand;
    return autoCommand;
  }
}
