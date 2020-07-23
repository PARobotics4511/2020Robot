/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Belt;
import frc.robot.subsystems.CameraInfo;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.EyesValues;
//import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class SmallAutoCommand extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain driveTrain;
  private final Belt belt;
  private final EyesValues eyesValues;
  private final CameraInfo cameraInfo;
  private final Shooter shooter;

  
  public SmallAutoCommand(DriveTrain dT, Belt b, EyesValues eV, CameraInfo cI, Shooter s ) {
    driveTrain = dT;
    shooter = s;
    belt = b;
    eyesValues = eV;
    cameraInfo = cI;
   addCommands(
     new GeorgeDrop(belt).withTimeout(0.05),
     new SmallAutoParallel(driveTrain, belt, eyesValues).withTimeout(0.75),
     new AutoDrive(driveTrain, 0, 0, 0).withTimeout(0.05), // positive lateral motion goes right.
     new CameraLightOn(cameraInfo).withTimeout(0.05),
     new AutoCameraOrient(cameraInfo, driveTrain).withTimeout(3),
     new CameraLightOff(cameraInfo).withTimeout(0.05),
     new Shoot(shooter, eyesValues),
     new IntakeStop(belt).withTimeout(0.1)
     );
  }
}//wow. idk how to help. how can i be of assistance.
// why do i phase between complete selflessness to the point of ruin and selfishness to the point of productivity?
//although, admittedly, i spend more time in the latter. it's more productive after all.
//tiger or lion? tiger
//hot or cold? cold
//sunny or rainy? rainy, but lighting storms are my favorite.
//book or movie? both, preferably. I would say that there are more good books than good movies though.
//soup or sandwhich? soup. I feel like once you've eaten one sandwhich, you've had them all.
//dog or cat? Despite never having owned a cat and never wanting to and thinking dogs are cuter, I don't like all the effort of caring for a dog.
//favorite color? a very specific blue-grey. beyond that, I love jewel tones.
//favorite fruit? i'm torn between kiwi (the zesty fruit of the gods) and mango (the ambrosia of the spirits). i really really love fruit.
//putting Shane and a hardcore optimist in a stressful situation together would be hilarious, just saying.
//okay, back on track.
//favorite veggie? corn. it's so sweet.
//least favorite fruit? none of the above. Although red grapes tend to be pretty gross and sour.
//least favorite veggie? Oh, wow. I don't like couliflower, beets, radi

