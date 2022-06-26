
package frc.robot;

import frc.robot.Toolkit.ShootZone;

// import javax.swing.plaf.synth.SynthLookAndFeel;

// import frc.robot.subsystems.intakeSystem;

public final class Constants {
    public static final int kCANTimeoutMS = 10;
    public static final boolean debug = true;

    public static final class MotorConstants {
        public static final int LeftmasterID = 1; // 四个马达
        public static final int LeftfollowerID = 2; // 四个马达
        public static final int RightmasterID = 3; // 四个马达
        public static final int RightfollowerID = 4; // 四个马达

        public static final int intakeID = 11; // 收球
        public static final double intakeSpeedTruePower = 0.2;
        public static final double intakeSpeedFalsePower = -0.2;

        public static final int upBallForwardID = 10; // 上球
        public static final int upBallBackwardID = 12; // 上球
        public static final int shootBallLeftID = 7;
        public static final int shootBallRightID = 8;
        //public static final int panTilt = 141514;
        
        public static final int liftLeftID = 5;
        public static final int liftRightID = 6;
        public static final int MotorxID = 9;

        public static final double Encode2Meter = (4 * 2.54 * 0.01 * Math.PI) / 2048.0;// D=4inch
        public static final double MaxRPM = 4000.0;
        public static final double EncoderToRPM = MaxRPM * 2048.0 / 600.0;
        public static final double LiftgearRatio = 3 / 50;
        public static final double DrivegearRatio = 36 / (31 * 7);
        public static final int kPIDSlot = 0;
        public static final double kCurrentLimitAmps = 80;
        public static final double kCurrentLimitDelay = 0.25;

        public static final class Telepid {
            public static final double kP = 0.1;
            public static final double kI = 0.001;
            public static final double kD = 5.0;
            public static final double kF = 1023.0 / 20660.0;
            public static final double kIZone = 300;
            public static final double Maxout = 1.00;
        }

        public static final class Liftpid {
            public static final double kP = 0.1;
            public static final double kI = 0.001;
            public static final double kD = 5.0;
            public static final double kF = 1023.0 / 20660.0;
            public static final double kIZone = 300;
            public static final double Maxout = 1.00;
        }

        public static final class Movepid {// 0.15, 0.0, 1.0, 0.0, 0, 1.0
            public static final double kP = 0.01;
            public static final double kI = 0.0;
            public static final double kD = 1.5;
            public static final double kF = 0;
            public static final double kIZone = 0;
            public static final double Maxout = 1.0;
        }

        public static final class Turnpid {
            public static final double kP = 1;
            public static final double kI = 0.001;
            public static final double kD = 1;
            public static final double Maxout = 0.5;
        }

        public static final double isOnTargetValue = 1;
    }

    public static final class SolenoidConstants{
        public static final int intakeChannel = 0;
        public static final int liftChannel = 7;
    }

    public static final class DriveConstants{
        public static final double joystickDeadZone = 0.1;

        public static final double speedFactor = 1;
        public static final double speedFactor_slowspeed = 0.3;
        public static final double turnFacotr = 0.5;
        public static final double turnFactor_slowspeed = 0.2;
    }

    public static final class ShooterConstants {

        public static final int kickID = 7;
        public static final double kCurrentLimitAmps = 80;
        public static final double kCurrentLimitDelay = 0.25;
        public static final double kEncoderTicksToRotations = 1.0 / 2048.0;
        public static final double kEncoderTicksToRPM = 600.0 / 2048.0;
        public static final double kMotorVelocityToleranceRPM = 8;
        public static final int kPIDSlot = 0;
        public static final double kP = 0.5;
        public static final double kI = 0.001;
        public static final double kD = 1;
        public static final double kF = (1023.0 + 100) / 20660.0;
        public static final double kIZone = 150;

    }

    public static final class IntakeConstants {

        public static final double Uptakepower = 0.5;
        public static final double Intakepower = 0.5;
        public static final double slowUppower = 0.15;
        public static final double slowDownpower = 0.15;

        public static final double power_MoveBallUp = 0.5;
        public static final double power_MoveBallIn = 0.5;
        public static final double power_MoveBallDown = 0.5;
        public static final double power_MoveBallOut = 0.5;

        public static final double slowMovementDeadzone = 0.1;

        public static final double doing_stage1 = 0.2;
        public static final double doing_stage2 = doing_stage1 + 0.1;

    }

    public static final class OIConstants {
        /*public static final int Drive_stickPort = 0;
        public static final int Operate_stickPort = 1;

        public static final int L_speed = 1;// getL_Y
        public static final int R_speed = 5;// getR_Y
        public static final int D_turn = 0;// getL_X
        public static final int D_lowspeed = 1;

        public static final int O_ShooterTurn = 1;
        public static final int O_Compressor = 1;
        public static final int O_Intakestandby = 2;
        public static final int O_Intake = 3;
        public static final int O_Uptake = 4;
        public static final int O_Fire = 5;
        public static final int O_ShooterZero = 6;*/

        public static final int drive_StickPort = 0;
        public static final int operate_StickPort = 1;

        public static final int operate_RightXPort = 4;
        public static final int operate_BACKPort = 7;
        public static final int operate_Right1Port = 6;
        public static final int operate_Left1Port = 5;
        public static final int operate_STARTPort = 8;
        public static final int operate_APort = 1;
        public static final int operate_Right2Port = 3;
        public static final int operate_Left2Port = 2;
        public static final int operate_XPort = 3;
        public static final int operate_BPort = 2;

        public static final int drive_LeftYPort = 1;
        public static final int drive_RightXPort = 4;
        public static final int drive_Right1Port = 6;
        public static final int drive_STARTPort = 8;
        public static final int drive_Left2Port = 2;
        public static final int drive_Right2Port = 3;
        public static final int drive_BPort = 2;
        public static final int drive_XPort = 3;

    }
    
    public static final class VisionConstants{
        public static final class PIDCtrl{
            public static final double kP = 100;
            public static final double kI = 0;
            //public static final double kD = 0.00001;
            public static final double kD = 0.00001;
        }

        public static final class AutoFire{
            public static final double allowedDiff = 100;
            public static final double okDiff = 1.5;

            public static final double EncoderToRPM = 1 / 2048 * 600;

            public static final double POWERDEFAULT = 0;
            
            /*public static final double POWER0TOn10 = 7000;
            public static final double POWER0TOn15 = 7500;
            public static final double POWERn10TOn20 = 8000;
            public static final double POWERn20TOn30 = 9000;*/

            public static final double PIDCONTROLTOSPEEDFACTOR = 1;

            public static final ShootZone[] shootZone={ 
                new ShootZone(6,10,6500),
                new ShootZone(2,6,6750),
                new ShootZone(-3, 2, 6900),
                new ShootZone(-7,-3,7300),
                new ShootZone(-10,-7,8100),
                new ShootZone(-11,-10,8300),
                new ShootZone(-12,-11,8600),
                new ShootZone(-13,-12,8700),
                new ShootZone(-14,-13,8900),
                new ShootZone(-14.5,-14,9200),
                new ShootZone(-15,-14.5,9300),
                new ShootZone(-15.5,-15,9600),
                new ShootZone(-16,-15.5,9700),
                new ShootZone(-16.5,-16,9900),
                new ShootZone(-17,-16.5,10000),
                new ShootZone(-17.5,-17,10400),
                new ShootZone(-18,-17.5,10500),
                new ShootZone(-18.5,-18,10800),
                new ShootZone(-19,-18.5,11000)
            };

            //public static final double OUTOFTIME = 1;
        }
    }

    public static final class TurrentConstants{
        public static final double deadZone = 0.5;

        public static final double xMotorSPD = 0.1;

        public static final double turningFac = -500;

        public static final class ConfigConstants{
            public static final int kP_slotIdx = 0;
            public static final double kP_value = 0.3;
            public static final int kP_timeoutMs = 10;

            public static final int kI_slotIdx = 0;
            public static final double kI_value = 0;
            public static final int kI_timeoutMs = 10;

            public static final int kD_slotIdx = 0;
            public static final double kD_value = 0;
            public static final int kD_timeoutMs = 10;
        }

        public static final class TurrentRangeConstants{
            public static final int Left = 12000;
            public static final int Right = -12000;
        }
    }

    public static final class LiftConstants{
        public static final double joystickDeadZone = 0.05;

        public static final class FGTB{
            public static final double LPosMAX = -900000;
            public static final double LPowFAC = 1;
        }

        public static final class FSTB{
            public static final double LPosMIN = 100000000;
            public static final double LPowFAC = 1;
        }
    }

    public static final class ToolkitConst{
        public static final class TurrentControllerConst{
            public static final double speed = 12;
            public static final double limit = 12;
        }
    }
}
