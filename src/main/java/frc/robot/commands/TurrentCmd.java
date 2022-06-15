package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.VisionConstants.PIDCtrl;
import frc.robot.subsystems.TurrentSystem;


public class TurrentCmd extends CommandBase{
    private final TurrentSystem turrentSystem;
    
    private final Supplier<Boolean> shootBallForward;
    private final Supplier<Boolean> moveBallUp;

    private final PIDController m_PIDController;
    private final Supplier<Double> xTurn;
    private final Supplier<Boolean> fire;
    
    public TurrentCmd(
        TurrentSystem _turrentSystem,
        
        Supplier<Boolean> _shootBallForward,
        Supplier<Boolean> _moveBallUp,
        Supplier<Double> _xTurn,
        Supplier<Boolean> _fire){
            
        turrentSystem = _turrentSystem;
        shootBallForward = _shootBallForward;
        moveBallUp = _moveBallUp;
        xTurn = _xTurn;
        fire = _fire;
        m_PIDController = new PIDController(PIDCtrl.kP, PIDCtrl.kI, PIDCtrl.kD);

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
		boolean moveBallUpGet = moveBallUp.get();
		boolean shootballForwardGet = shootBallForward.get();

		if(xTurnGet < -0.5){
			turrentSystem.setMotorX(-0.2);
		}
		else if(xTurnGet > 0.5){
			turrentSystem.setMotorX(0.2);
		}

		if(fireGet == true){
			if(turrentSystem.isValid()){
				double getXGet = turrentSystem.getX();
				turrentSystem.setMotorX(m_PIDController.calculate(getXGet,0));

				if(Math.abs(getXGet) < 0.1){
					turrentSystem.autoFire();
				}
			}
		}


		if(shootballForwardGet == true){
			turrentSystem.setshootForward(1);
		}
		else{
			turrentSystem.setshootForward(0);
		}

		if(moveBallUpGet == true){
			turrentSystem.setMoveBallUP(0.05);
		}
		else{
			turrentSystem.setMoveBallUP(0);
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
