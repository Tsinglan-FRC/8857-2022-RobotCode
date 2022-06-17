package frc.robot;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.MotorConstants.Liftpid;
import frc.robot.Constants.MotorConstants.Movepid;
import frc.robot.Constants.MotorConstants.Telepid;

public class Toolkit {
    public static class TKTalonFX{
        public static void configMotor(TalonFX motor, double P, double I, double D, double F, double Izone, double maxout) {
            motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, MotorConstants.kPIDSlot,
                Constants.kCANTimeoutMS);
        
            motor.config_kP(MotorConstants.kPIDSlot, P, Constants.kCANTimeoutMS);
            motor.config_kI(MotorConstants.kPIDSlot, I, Constants.kCANTimeoutMS);
            motor.config_kD(MotorConstants.kPIDSlot, D, Constants.kCANTimeoutMS);
            motor.config_kF(MotorConstants.kPIDSlot, F, Constants.kCANTimeoutMS);
        
            motor.config_IntegralZone(MotorConstants.kPIDSlot, Izone, Constants.kCANTimeoutMS);
            // motor.configPeakOutputForward(maxout);
            // motor.configPeakOutputReverse(-maxout);
            motor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, MotorConstants.kCurrentLimitAmps,
                MotorConstants.kCurrentLimitAmps, MotorConstants.kCurrentLimitDelay));
            motor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, MotorConstants.kCurrentLimitAmps,
                MotorConstants.kCurrentLimitAmps, MotorConstants.kCurrentLimitDelay));
        }

        public enum PIDType{
            Telepid,
            Liftpid,
            Movepid,
            Turnpid
        }

        public static void configMotor(TalonFX motor,PIDType type){
            switch(type){
                case Telepid:
                configMotor(motor,Telepid.kP, Telepid.kI, Telepid.kD, Telepid.kF, Telepid.kIZone, Telepid.Maxout);
                break;
                case Liftpid:
                configMotor(motor,Liftpid.kP, Liftpid.kI, Liftpid.kD, Liftpid.kF, Liftpid.kIZone, Liftpid.Maxout);
                break;
                case Movepid:
                configMotor(motor,Movepid.kP, Movepid.kI, Movepid.kD, Movepid.kF, Movepid.kIZone, Movepid.Maxout);
                break;
                default:
                return;
            }
        }
    }


    public static class Toggle{
        private boolean value = false;
        private boolean lasttime = false;

        public boolean get(){
            return value;
        }

        public void press(boolean input){
            if(lasttime == false){
                if(input == true){
                    value = !value;
                }
            }
            lasttime = input;
        }
    }

    public static class ShootZone{
        public double Min;
        public double Max;
        public double Zone;
    }
}
