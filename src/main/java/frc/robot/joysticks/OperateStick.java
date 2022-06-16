package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OIConstants;

public class OperateStick extends Joystick{
    public OperateStick(){
        super(OIConstants.operate_StickPort);
    }

    /*public boolean isSetZero(){
        return false;
    }*/

    public double getRightX(){
        return this.getRawAxis(OIConstants.operate_RightXPort);
    }

    public boolean getRight1(){
        return getRawButton(OIConstants.operate_Right1Port);
    }

    public boolean getLeft1(){
        return getRawButton(OIConstants.operate_Left1Port);
    }

    public boolean getSTART(){
        return getRawButton(OIConstants.operate_STARTPort);
    }

    public boolean getA(){
        return getRawButton(OIConstants.operate_APort);
    }
}
