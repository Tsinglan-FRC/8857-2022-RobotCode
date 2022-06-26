package frc.robot.autocmds.PlanDemo_;

import frc.robot.Toolkit.TimedCommand;
import frc.robot.Toolkit.TimedCommandGroup;
import frc.robot.subsystems.intakeSystem;

public class IntakeGroup extends TimedCommandGroup{
    private final intakeSystem sys;
    public IntakeGroup(intakeSystem _sys){
        sys = _sys;
    }


    TimedCommand[] timedCommands = null;

    @Override
    public TimedCommand[] getCommands(){
        return timedCommands;
    }

    @Override
    public TimedCommand getCommand(int index){
        return timedCommands[index];
    }

    @Override
    public int getLength(){
        return timedCommands.length;
    }
}