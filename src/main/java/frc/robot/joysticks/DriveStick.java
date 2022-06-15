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

    public boolean getPenumatic(){
        return getRawButton(OIConstants.drive_LiftPneumaticPort);
    }

    public double getLiftForward(){
        return getRawAxis(OIConstants.drive_LiftForwardPort);
    }

    public double getLifeBackward(){
        return getRawAxis(OIConstants.drive_LiftBackwardPort);
    }
}
