package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OIConstants;

public class DriveStick extends Joystick{
    public DriveStick(){
        super(OIConstants.Drive_stickPort);
    }
}
