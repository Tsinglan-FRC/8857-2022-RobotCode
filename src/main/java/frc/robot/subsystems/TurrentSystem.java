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
import frc.robot.Constants.TurrentConstants.ConfigConstants;
import frc.robot.Constants.VisionConstants.AutoFire;
import frc.robot.Toolkit.TKTalonFX;
import frc.robot.Toolkit.TKTalonFX.PIDType;



public class TurrentSystem extends SubsystemBase{

    private final TalonFX motorX = new TalonFX(MotorConstants.MotorxID);
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    private final NetworkTableEntry tx = table.getEntry("tx"); //距目标X
    private final NetworkTableEntry ty = table.getEntry("ty"); //距目标y
    private final NetworkTableEntry ta = table.getEntry("ta"); //目标面积
    private final NetworkTableEntry tv = table.getEntry("tv"); //有无目标
    private final NetworkTableEntry ledMode = table.getEntry("ledMode");



    private final TalonFX shootBall_L = new TalonFX(MotorConstants.shootBallLeftID); //一左一右两个射球
    private final TalonFX shootBall_R = new TalonFX(MotorConstants.shootBallRightID); //一左一右两个射球



    public TurrentSystem(){
        System.out.println("Limelight init");
        setLED(false);

        setBrake(true);
        
        motorX.config_kP(ConfigConstants.kP_slotIdx, ConfigConstants.kP_value, ConfigConstants.kP_timeoutMs);
        motorX.config_kI(ConfigConstants.kI_slotIdx, ConfigConstants.kI_value, ConfigConstants.kI_timeoutMs);
        motorX.config_kD(ConfigConstants.kD_slotIdx, ConfigConstants.kD_value, ConfigConstants.kD_timeoutMs);



        shootBall_L.setInverted(true);// 设置反转
        shootBall_L.setSensorPhase(true); // 设置传感器状态
        shootBall_R.setInverted(false);// 设置不反转（需要测试）
        shootBall_R.setSensorPhase(true); // 设置传感器状态
        shootBall_R.follow(shootBall_L);

        TKTalonFX.configMotor(shootBall_L,PIDType.Telepid);
        TKTalonFX.configMotor(shootBall_R,PIDType.Telepid);
        /*configMotor(moveBall_B,PIDType.Telepid);
        configMotor(moveBall_F,PIDType.Telepid);*/
    }

    @Override
    public void periodic() {
        updateTelemetry();

        /*SmartDashboard.putNumber("intake speed Value Left", shootBall_L.getMotorOutputPercent());
        SmartDashboard.putNumber("intake Current Left", shootBall_L.getSupplyCurrent());
        SmartDashboard.putNumber("intake speed Value Right", shootBall_R.getMotorOutputPercent());
        SmartDashboard.putNumber("intake Current Right", shootBall_R.getSupplyCurrent());*/
        SmartDashboard.putNumber("Turrent Position : ", motorX.getSelectedSensorPosition());
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
            
            shootBall_L.setNeutralMode(NeutralMode.Brake); // 设置刹车
            shootBall_R.setNeutralMode(NeutralMode.Brake); // 设置刹车
            //moveBall_F.setNeutralMode(NeutralMode.Brake); // 设置刹车
            //moveBall_B.setNeutralMode(NeutralMode.Brake);
        }
        else{
            motorX.setNeutralMode(NeutralMode.Coast);
            shootBall_L.setNeutralMode(NeutralMode.Coast); 
            shootBall_R.setNeutralMode(NeutralMode.Coast);
            //moveBall_F.setNeutralMode(NeutralMode.Coast); 
            //moveBall_B.setNeutralMode(NeutralMode.Coast);
        }
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

    public void setshootForward(double power){
        shootBall_L.set(ControlMode.PercentOutput, power);
    }

    /*public void setshootForward(double power) {
        shootBall_L.set(ControlMode.PercentOutput, power);
        shootBall_R.set(ControlMode.PercentOutput, power);
    }
    
    public void setshootBackward(double power) {
        shootBall_L.set(ControlMode.PercentOutput, power * -1 * 0.6);
        shootBall_R.set(ControlMode.PercentOutput, power * -1 * 0.6);
    }*/
    
    /*public void setMoveBallUP(double power){
        moveBall_B.set(ControlMode.PercentOutput, power);
        moveBall_F.set(ControlMode.PercentOutput, power * -1);
    }*/
    
    /*public void setMoveBallDown(double power){
        moveBall_B.set(ControlMode.PercentOutput, power * -1);
        moveBall_F.set(ControlMode.PercentOutput, power);
    }*/

    public boolean onTarget(){
        if(shootBall_L.getSelectedSensorVelocity()>=MotorConstants.isOnTargetValue && shootBall_R.getSelectedSensorVelocity()>=MotorConstants.isOnTargetValue){
          return true;
        }
        else{
          return false;
        }
    }



    public void autoFire(){
        double getYGet = getY();

        if(getYGet > 10 && getYGet <= 20){
            setshootForward(AutoFire.POWER10TO20);
        }
        else if(getYGet > 20 && getYGet <= 30){
            setshootForward(AutoFire.POWER20TO30);
        }

        /*if(onTarget()){
            setMoveBallUP(0.5);
        }*/
        //setMoveBallUP(1);
    }
    
}
