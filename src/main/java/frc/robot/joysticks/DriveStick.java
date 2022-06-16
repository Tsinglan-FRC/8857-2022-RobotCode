package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OIConstants;

public class DriveStick extends Joystick{
    public DriveStick(){
        super(OIConstants.drive_StickPort);
    }

    public double getLeftY(){
        return -1 * getRawAxis(OIConstants.drive_LeftYPort);
    }

    public double getRightX(){
        return getRawAxis(OIConstants.drive_RightXPort);
    }

    public boolean getRight1(){
        return getRawButton(OIConstants.drive_Right1Port);
    }

    public boolean getSTART(){
        return getRawButton(OIConstants.drive_STARTPort);
    }

    public boolean getB(){
        return getRawButton(OIConstants.drive_BPort);
    }

    public double getLeft2(){
        return getRawAxis(OIConstants.drive_Left2Port);
    }

    public double getRight2(){
        return getRawAxis(OIConstants.drive_Right2Port);
    }
}
