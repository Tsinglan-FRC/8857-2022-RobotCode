package frc.robot.joysticks;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants.OIConstants;

public class OperateStick extends Joystick{
    public OperateStick(){
        super(OIConstants.operate_StickPort);
    }

    public boolean isFire(){
        return false;
    }

    public boolean isSetZero(){
        return false;
    }

    public double getHorizontal(){
        return this.getRawAxis(OIConstants.operate_HorizontalPort) * -1;
    }

    public boolean shallMoveBallUp(){
        return getRawButton(OIConstants.operate_MoveBallUpPort);
    }

    public boolean shallPutBallOut(){
        return getRawButton(OIConstants.operate_PutBallOutPort);
    }

    public boolean getIntakeStatus(){
        return getRawButton(OIConstants.operate_IntakeStatus);
    }

    public boolean shallJustShoot(){
        return getRawButton(OIConstants.operate_JustShoot);
    }
}
