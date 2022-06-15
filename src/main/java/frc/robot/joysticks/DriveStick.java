package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OIConstants;

public class DriveStick extends Joystick{
    public DriveStick(){
        super(OIConstants.drive_StickPort);
    }

    public double getSpeed(){
        return getRawAxis(OIConstants.drive_SpeedPort);
    }

    public double getTurn(){
        return getRawAxis(OIConstants.drive_TurnPort);
    }

    public boolean isInLowSpeed(){
        return getRawButton(OIConstants.drive_InLowSpeedPort);
    }

    public boolean getCompressorStatus(){
        return getRawButton(OIConstants.drive_CompressorStatusPort);
    }
}
