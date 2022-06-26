package frc.robot.autocmds.PlanDemo_;

import frc.robot.Toolkit.TimedCommand;
import frc.robot.Toolkit.TimedCommandGroup;
import frc.robot.subsystems.LiftSystem;

public class LiftGroup extends TimedCommandGroup{
    private final LiftSystem sys;
    public LiftGroup(LiftSystem _sys){
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