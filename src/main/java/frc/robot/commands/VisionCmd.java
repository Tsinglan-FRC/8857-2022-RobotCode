package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.VisionConstants.PIDCtrl;
import frc.robot.Constants.VisionConstants.AutoFire;
import frc.robot.subsystems.VisionSystem;
import frc.robot.subsystems.upAndShootSystem;

public class VisionCmd extends CommandBase{
    private PIDController m_PIDController;
    private upAndShootSystem m_Shooter;
    private VisionSystem m_Vision;

    private final Supplier<Double> xTurn;
    private final Supplier<Boolean> fire;
    private final Supplier<Boolean> setZero;


    public VisionCmd(
        upAndShootSystem _shooter,
        VisionSystem _vision,

        Supplier<Double> _xTurn,
        Supplier<Boolean> _fire,
        Supplier<Boolean> _setZero ){

        m_PIDController = new PIDController(PIDCtrl.kP, PIDCtrl.kI, PIDCtrl.kD);

        m_Shooter = _shooter;
        m_Vision = _vision;

        xTurn = _xTurn;
        fire = _fire;
        setZero = _setZero;

        addRequirements(m_Shooter);
        addRequirements(m_Vision);
    }

    public VisionCmd(
        upAndShootSystem _shooter,
        VisionSystem _vision,

        Supplier<Double> _xTurn,
        Supplier<Boolean> _fire){
        m_Shooter = _shooter;
        m_Vision = _vision;

        xTurn = _xTurn;
        fire = _fire;
        setZero=null;

        addRequirements(m_Shooter);
        addRequirements(m_Vision);
    }

    @Override
    public void initialize(){
        m_Vision.setLED(true);
    }

    @Override
    public void execute(){
        if(xTurn.get() < -0.5){
            m_Vision.setMotorX(-0.2);
        }
        else if(xTurn.get() > 0.5){
            m_Vision.setMotorX(0.2);
        }

        if(setZero!=null) if(setZero.get() == true){
            m_Vision.setMiddle();
        }

        if(fire.get() == true){
            if(m_Vision.isValid()){
                m_Vision.setMotorX(m_PIDController.calculate(m_Vision.getX(),0));
                if(Math.abs(m_Vision.getX()) < 0.1){


                    if (m_Vision.getY() > 10.0 && m_Vision.getY() < 20.0){
                        m_Shooter.setshootForward(AutoFire.POWER10TO20);
                    }
                    else if (m_Vision.getY() > 20.0 && m_Vision.getY() < 30.0){
                        m_Shooter.setshootForward(AutoFire.POWER20TO30);
                    }
                    // ............more condition.................
                    if (m_Shooter.onTarget()) {
                        m_Shooter.setMoveBallUP(0.5);
                    }

                }
            }
        }
        else{
            m_Shooter.setshootForward(0);
            m_Vision.setMiddle();
        }
    }

    @Override
    public void end(boolean interrupted){}

    //@Override
    public boolean isFInished(){
        return false;//
    }
}
