package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Toolkit;
// import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.TurrentConstants;
import frc.robot.Constants.VisionConstants.AutoFire;
import frc.robot.Constants.VisionConstants.PIDCtrl;
import frc.robot.subsystems.TurrentSystem;
import frc.robot.subsystems.TurrentSystem.TurrentRangeStatus;


public class TurrentCmd extends CommandBase{
    private final TurrentSystem turrentSystem;
    
    //private final Supplier<Boolean> moveBallUp;

    private final PIDController m_PIDController;
    private final Supplier<Double> xTurn;
    private final Supplier<Boolean> fire;
	private final Supplier<Boolean> intaking;
	private final Supplier<Boolean> middle;
	//private final Supplier<Boolean> reverseTakeOut;
    
    public TurrentCmd(
        TurrentSystem _turrentSystem,
        
        Supplier<Double> _xTurn,
        Supplier<Boolean> _fire,
		Supplier<Boolean> _intaking,
		Supplier<Boolean> _middle){
            
        turrentSystem = _turrentSystem;
        xTurn = _xTurn;
        fire = _fire;
        m_PIDController = new PIDController(PIDCtrl.kP, PIDCtrl.kI, PIDCtrl.kD);
		intaking = _intaking;
		middle = _middle;
		//reverseTakeOut=_reverseTakeOut;

        addRequirements(_turrentSystem);
    }

	@Override
	public void initialize(){
		turrentSystem.setLED(true);
		turrentSystem.setZero();
	}

	@Override
	public void execute(){
		double xTurnGet = xTurn.get();
		boolean fireGet = fire.get();
		TurrentRangeStatus range = turrentSystem.amIInRange();
		boolean  intakingGet = intaking.get();
		boolean middleGet = middle.get();
		

		if(range!=TurrentRangeStatus.Ok){
			if(range==TurrentRangeStatus.Left){
				turrentSystem.setMotorX(TurrentConstants.xMotorSPD * -1);
			}
			else{
				turrentSystem.setMotorX(TurrentConstants.xMotorSPD);
			}
		}
		else if(middleGet){
			turrentSystem.setMiddle();
		}
		else if(fireGet == true){
			if(turrentSystem.isValid()){
				double getXGet = turrentSystem.getX();

				if(Math.abs(getXGet) <= AutoFire.allowedDiff){
					turrentSystem.autoFire();
				}
				else{
					turrentSystem.setshootForward(0);
				}

				if(Math.abs(getXGet) >= AutoFire.okDiff){
					turrentSystem.setMotorXSpeed(m_PIDController.calculate(getXGet,0)*AutoFire.PIDCONTROLTOSPEEDFACTOR);
					//turrentSystem.setMotorXSpeed(Toolkit.turrentController(getXGet));
				}
				else{
					turrentSystem.setMotorX(0);
				}
			}
		}
		else if(xTurnGet < TurrentConstants.deadZone * -1){
			turrentSystem.setMotorX(TurrentConstants.xMotorSPD);
		} 
		else if(xTurnGet > TurrentConstants.deadZone){
			turrentSystem.setMotorX(TurrentConstants.xMotorSPD * -1);
		}
		else if(intakingGet){
			turrentSystem.turning();
		}
		else {
			turrentSystem.setMotorX(0.0);
			turrentSystem.setshootForward(0);
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
