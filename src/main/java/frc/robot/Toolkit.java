package frc.robot;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.MotorConstants.Telepid;

public class Toolkit {
    public interface TKTalonFX{
        default void configMotor(TalonFX motor, double P, double I, double D, double F, double Izone, double maxout) {
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

        default void configMotor(TalonFX motor){
            configMotor(motor,Telepid.kP, Telepid.kI, Telepid.kD, Telepid.kF, Telepid.kIZone, Telepid.Maxout);
        }
    }
}
