package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurrentConstants;
import frc.robot.Constants.VisionConstants.AutoFire;
import frc.robot.Constants.VisionConstants.PIDCtrl;
import frc.robot.subsystems.TurrentSystem;


public class TurrentCmd extends CommandBase{
    private final TurrentSystem turrentSystem;
    
    //private final Supplier<Boolean> moveBallUp;

    private final PIDController m_PIDController;
    private final Supplier<Double> xTurn;
    private final Supplier<Boolean> fire;
	//private final Supplier<Boolean> reverseTakeOut;
    
    public TurrentCmd(
        TurrentSystem _turrentSystem,
        
        Supplier<Double> _xTurn,
        Supplier<Boolean> _fire){
            
        turrentSystem = _turrentSystem;
        xTurn = _xTurn;
        fire = _fire;
        m_PIDController = new PIDController(PIDCtrl.kP, PIDCtrl.kI, PIDCtrl.kD);
		//reverseTakeOut=_reverseTakeOut;

        addRequirements(_turrentSystem);
    }

	@Override
	public void initialize(){
		turrentSystem.setLED(true);
	}

	@Override
	public void execute(){
		double xTurnGet = xTurn.get();
		boolean fireGet = fire.get();
		

		if(fireGet == true){
			if(turrentSystem.isValid()){
				double getXGet = turrentSystem.getX();
				turrentSystem.setMotorX(m_PIDController.calculate(getXGet,0));

				if(Math.abs(getXGet) < AutoFire.allowedDiff){
					turrentSystem.autoFire();
				}
			}
		}
		else if(xTurnGet < TurrentConstants.deadZone * -1){
			turrentSystem.setMotorX(TurrentConstants.xMotorSPD);
		} 
		else if(xTurnGet > TurrentConstants.deadZone){
			turrentSystem.setMotorX(TurrentConstants.xMotorSPD * -1);
		}
		else {
			turrentSystem.setMotorX(0.0);
		}
	}

	@Override
	public void end(boolean interrupted){

	}

	@Override
	public boolean isFinished(){
		return false;
	}
}
