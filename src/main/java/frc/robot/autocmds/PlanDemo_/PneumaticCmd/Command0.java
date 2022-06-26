package frc.robot.autocmds.PlanDemo_.PneumaticCmd;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.PneumaticSystem;

public class Command0 extends CommandBase{
    private final PneumaticSystem pcm;
    public Command0(PneumaticSystem _pcm){
        pcm = _pcm;
    }
    
    @Override
    public void initialize(){}

    @Override
    public void execute(){
        pcm.setCompressorClosedLoop(false);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}
