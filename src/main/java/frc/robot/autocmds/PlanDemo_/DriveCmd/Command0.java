package frc.robot.autocmds.PlanDemo_.DriveCmd;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class Command0 extends CommandBase{
    private final DriveSystem drive;

    public Command0(DriveSystem _drive){
        drive = _drive;
        addRequirements(drive);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        drive.arcade(0.1,0,false);
    }

    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){return false;}
}
