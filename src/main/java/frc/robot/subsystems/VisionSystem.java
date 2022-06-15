// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.Constants.MotorConstants;

public class VisionSystem extends SubsystemBase {

    private final TalonFX motorX = new TalonFX(MotorConstants.MotorxID);
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    private final NetworkTableEntry tx = table.getEntry("tx");
    private final NetworkTableEntry ty = table.getEntry("ty");
    private final NetworkTableEntry ta = table.getEntry("ta");
    private final NetworkTableEntry tv = table.getEntry("tv");
    private final NetworkTableEntry ledMode = table.getEntry("ledMode");

    public VisionSystem() {
        System.out.println("Limelight init");
        setLED(false);

        setBrake(true);
        motorX.config_kP(0, 0.3, 10);
        motorX.config_kI(0, 0, 10);
        motorX.config_kD(0, 0, 10);
    }

    @Override
    public void periodic() {
        updateTelemetry();
    }

    public double getX() {
        return tx.getDouble(0.0);
    }

    public double getY() {
        return ty.getDouble(0.0);
    }

    public double getArea() {
        return ta.getDouble(0.0);
    }

    public boolean isValid() {
        if (tv.getDouble(0.0) == 1.0)
            return true;
        else
            return false;
    }

    public void setBrake(boolean brake){
        if(brake){
            motorX.setNeutralMode(NeutralMode.Brake);
        }
        else{
            motorX.setNeutralMode(NeutralMode.Coast);
        }
    }

    public void configPID(){
        motorX.config_kP(0, 0.3, 10);
        motorX.config_kI(0, 0, 10);
        motorX.config_kD(0, 0, 10);
    }

    public void setLED(boolean on) {
        if (on == true)
            ledMode.setNumber(3);
        else
            ledMode.setNumber(1);

    }

    public void setMotorX(double power) {
        motorX.set(ControlMode.PercentOutput, power);
    }

    public void setMiddle() {
        motorX.set(ControlMode.Position, 0);
    }

    private void updateTelemetry() {
        if (Constants.debug) {
            SmartDashboard.putNumber("LimelightX", getX());
            SmartDashboard.putNumber("LimelightY", getY());
            SmartDashboard.putNumber("LimelightArea", getArea());

            SmartDashboard.putBoolean("LimelightValid", isValid());

        }
    }

}