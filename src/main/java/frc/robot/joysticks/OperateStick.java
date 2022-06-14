package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OIConstants;

public class OperateStick extends Joystick{
    public OperateStick(){
        super(OIConstants.Operate_stickPort);
    }

    public boolean isFire(){
        return false;
    }

    public boolean isSetZero(){
        return false;
    }

    public double getHorizontal(){
        return this.getRawAxis(5);
    }
}
