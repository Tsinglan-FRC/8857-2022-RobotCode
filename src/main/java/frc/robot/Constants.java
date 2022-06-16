
package frc.robot;

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
        public static final double MaxRPM = 2000.0;
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

        public static final int operate_HorizontalPort = 4;
        public static final int operate_ShootBallForwardPort = 7;
        public static final int operate_MoveBallUpPort = 6;
        public static final int operate_PutBallOutPort = 8;
        public static final int operate_IntakeStatus = 5;
        public static final int operate_JustShoot = 1;

        public static final int drive_SpeedPort = 1;
        public static final int drive_TurnPort = 4;
        public static final int drive_InLowSpeedPort = 6;
        public static final int drive_CompressorStatusPort = 8;
        public static final int drive_LiftForwardPort = 2;
        public static final int drive_LiftBackwardPort = 4;
        public static final int drive_LiftPneumaticPort = 2;

    }
    
    public static final class VisionConstants{
        public static final class PIDCtrl{
            public static final double kP = 0.1;
            public static final double kI = 0;
            public static final double kD = 0;
        }

        public static final class AutoFire{
            public static final double POWER10TO20 = 1;
            public static final double POWER20TO30 = 1;
        }
    }
}
