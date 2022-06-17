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

    public double getRight2(){
        return getRawAxis(OIConstants.operate_Right2Port);
    }

    public boolean getLeft1(){
        return getRawButton(OIConstants.operate_Left1Port);
    }

    public double getLeft2(){
        return getRawAxis(OIConstants.operate_Left2Port);
    }

    public boolean getSTART(){
        return getRawButton(OIConstants.operate_STARTPort);
    }

    public boolean getA(){
        return getRawButton(OIConstants.operate_APort);
    }

    public boolean getXbtn(){
        return getRawButton(OIConstants.operate_XPort);
    }
}
