import os

def createNewCommand(name,sys):
    command_file = open(name+".java","x")
    def c(a):
        command_file.write(a)

    c("package;\n")
    c("\n")
    c("import edu.wpi.first.wpilibj2.command.CommandBase;\n")
    c("import frc.robot.subsystems."+sys+";\n")
    c("\n")
    c("public class "+name+" extends CommandBase{\n")
    c("\tprivate final "+sys+" sys;\n")
    c("\n")
    c("\tpublic "+name+"("+sys+" _sys){\n")
    c("\t\tsys=_sys;\n")
    c("\t\taddRequirements(sys);\n")
    c("\t}\n")
    c("\n")
    c("\t@Override\n")
    c("\tpublic void initialize(){}\n")
    c("\n")
    c("\t@Override\n")
    c("\tpublic void execute(){}\n")
    c("\n")
    c("\t@Override\n")
    c("\tpublic void end(boolean interrupted){}\n")
    c("\n")
    c("\t@Override\n")
    c("\tpublic boolean isFinished(){return false;}\n")
    c("}\n")

def createNewPlan(plan_name,home_path="src/main/java/frc/robot/autocmds"):
    #generate the home directory of the plan
    directory = os.path.join(home_path,plan_name+"_")
    os.mkdir(directory)
    #generate the home file of the plan
    home_file = open(os.path.join(home_path,plan_name+".java"),"x")
    #inner function for my hands
    def h(a):
        home_file.write(a)
    #writing the home file, which is boring
    h("package frc.robot.autocmds;\n")
    h("\n")
    h("import frc.robot.Toolkit.TimedCommandGroup;\n")
    h("import frc.robot.Toolkit.TimedCommandHandle;\n")
    h("import frc.robot.autocmds.PlanDemo_.*;\n")
    h("import frc.robot.subsystems.DriveSystem;\n")
    h("import frc.robot.subsystems.LiftSystem;\n")
    h("import frc.robot.subsystems.PneumaticSystem;\n")
    h("import frc.robot.subsystems.TurrentSystem;\n")
    h("import frc.robot.subsystems.intakeSystem;\n")
    h("\n")
    h("public class "+plan_name+" extends TimedCommandHandle{\n")
    h("\tprivate final DriveSystem driveSys;\n")
    h("\tprivate final intakeSystem intakeSys;\n")
    h("\tprivate final LiftSystem liftSys;\n")
    h("\tprivate final PneumaticSystem pcmSys;\n")
    h("\tprivate final TurrentSystem turrentSys;\n")
    h("\n")
    h("\tpublic "+plan_name+"(\n")
    h("\t\tDriveSystem _driveSys,\n")
    h("\t\tintakeSystem _intakeSys,\n")
    h("\t\tLiftSystem _liftSys,\n")
    h("\t\tPneumaticSystem _pcmSys,\n")
    h("\t\tTurrentSystem _turrentSys\n")
    h("\t){\n")
    h("\t\tdriveSys = _driveSys;\n")
    h("\t\tintakeSys = _intakeSys;\n")
    h("\t\tliftSys = _liftSys;\n")
    h("\t\tpcmSys = _pcmSys;\n")
    h("\t\tturrentSys = _turrentSys;\n")
    h("\n")
    h("\t\tdrive = new DriveGroup(driveSys);\n")
    h("\t\tintake = new IntakeGroup(intakeSys);\n")
    h("\t\tlift = new LiftGroup(liftSys);\n")
    h("\t\tpcm = new PneumaticGroup(pcmSys);\n")
    h("\t\tturrent = new TurrentGroup(turrentSys);\n")
    h("\t}\n")
    h("\n")
    h("\tprivate TimedCommandGroup drive;\n")
    h("\tprivate TimedCommandGroup intake;\n")
    h("\tprivate TimedCommandGroup lift;\n")
    h("\tprivate TimedCommandGroup pcm;\n")
    h("\tprivate TimedCommandGroup turrent;\n")
    h("\n")
    h("\t@Override\n")
    h("\tpublic TimedCommandGroup getDriveGroup(){return drive;}\n")
    h("\t@Override\n")
    h("\tpublic TimedCommandGroup getIntakeGroup(){return intake;}\n")
    h("\t@Override\n")
    h("\tpublic TimedCommandGroup getLiftGroup(){return lift;}\n")
    h("\t@Override\n")
    h("\tpublic TimedCommandGroup getPneumaticGroup(){return pcm;}\n")
    h("\t@Override\n")
    h("\tpublic TimedCommandGroup getTurrentGroup(){return turrent;}\n")
    h("}")
    #Generating the other 5 directories
    os.mkdir(os.path.join(directory,"DriveCmd"))
    os.mkdir(os.path.join(directory,"IntakeCmd"))
    os.mkdir(os.path.join(directory,"LiftCmd"))
    os.mkdir(os.path.join(directory,"PneumaticCmd"))
    os.mkdir(os.path.join(directory,"TurrentCmd"))
    #generate the other 5 files
    drive_file = open(os.path.join(directory,"DriveGroup.java"),"x")
    intake_file = open(os.path.join(directory,"IntakeGroup.java"),"x")
    lift_file = open(os.path.join(directory,"LiftGroup.java"),"x")
    pcm_file = open(os.path.join(directory,"PneumaticGroup.java"),"x")
    turrent_file = open(os.path.join(directory,"TurrentGroup.java"),"x")
    #writing drive_file
    def d(a):
        drive_file.write(a)

    d("package frc.robot.autocmds."+plan_name+"_;\n")
    d("\n")
    d("import frc.robot.Toolkit.TimedCommand;\n")
    d("import frc.robot.Toolkit.TimedCommandGroup;\n")
    d("//import frc.robot.autocmds."+plan_name+"_.DriveCmd.*;\n")
    d("import frc.robot.subsystems.DriveSystem;\n")
    d("\n")
    d("public class DriveGroup extends TimedCommandGroup{\n")
    d("\tprivate final DriveSystem sys;\n")
    d("\tpublic DriveGroup(DriveSystem _sys){\n")
    d("\t\tsys = _sys;\n")
    d("\n")
    d("\t}\n")
    d("\n")
    d("\n")
    d("\tTimedCommand[] timedCommands = null;\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand[] getCommands(){\n")
    d("\t\treturn timedCommands;\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand getCommand(int index){\n")
    d("\t\treturn timedCommands[index];\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic int getLength(){\n")
    d("\t\treturn timedCommands.length;\n")
    d("\t}\n")
    d("}\n")
    #writing intake_file
    def d(a):
        intake_file.write(a)

    d("package frc.robot.autocmds."+plan_name+"_;\n")
    d("\n")
    d("import frc.robot.Toolkit.TimedCommand;\n")
    d("import frc.robot.Toolkit.TimedCommandGroup;\n")
    d("//import frc.robot.autocmds."+plan_name+"_.IntakeCmd.*;\n")
    d("import frc.robot.subsystems.intakeSystem;\n")
    d("\n")
    d("public class IntakeGroup extends TimedCommandGroup{\n")
    d("\tprivate final intakeSystem sys;\n")
    d("\tpublic IntakeGroup(intakeSystem _sys){\n")
    d("\t\tsys = _sys;\n")
    d("\n")
    d("\t}\n")
    d("\n")
    d("\n")
    d("\tTimedCommand[] timedCommands = null;\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand[] getCommands(){\n")
    d("\t\treturn timedCommands;\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand getCommand(int index){\n")
    d("\t\treturn timedCommands[index];\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic int getLength(){\n")
    d("\t\treturn timedCommands.length;\n")
    d("\t}\n")
    d("}\n")
    #writing lift_file
    def d(a):
        lift_file.write(a)

    d("package frc.robot.autocmds."+plan_name+"_;\n")
    d("\n")
    d("import frc.robot.Toolkit.TimedCommand;\n")
    d("import frc.robot.Toolkit.TimedCommandGroup;\n")
    d("//import frc.robot.autocmds."+plan_name+"_.LiftCmd.*;\n")
    d("import frc.robot.subsystems.LiftSystem;\n")
    d("\n")
    d("public class LiftGroup extends TimedCommandGroup{\n")
    d("\tprivate final LiftSystem sys;\n")
    d("\tpublic LiftGroup(LiftSystem _sys){\n")
    d("\t\tsys = _sys;\n")
    d("\n")
    d("\t}\n")
    d("\n")
    d("\n")
    d("\tTimedCommand[] timedCommands = null;\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand[] getCommands(){\n")
    d("\t\treturn timedCommands;\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand getCommand(int index){\n")
    d("\t\treturn timedCommands[index];\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic int getLength(){\n")
    d("\t\treturn timedCommands.length;\n")
    d("\t}\n")
    d("}\n")
    #writing pcm_file
    def d(a):
        pcm_file.write(a)

    d("package frc.robot.autocmds."+plan_name+"_;\n")
    d("\n")
    d("import frc.robot.Toolkit.TimedCommand;\n")
    d("import frc.robot.Toolkit.TimedCommandGroup;\n")
    d("//import frc.robot.autocmds."+plan_name+"_.PneumaticCmd.*;\n")
    d("import frc.robot.subsystems.PneumaticSystem;\n")
    d("\n")
    d("public class PneumaticGroup extends TimedCommandGroup{\n")
    d("\tprivate final PneumaticSystem sys;\n")
    d("\tpublic PneumaticGroup(PneumaticSystem _sys){\n")
    d("\t\tsys = _sys;\n")
    d("\n")
    d("\t}\n")
    d("\n")
    d("\n")
    d("\tTimedCommand[] timedCommands = null;\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand[] getCommands(){\n")
    d("\t\treturn timedCommands;\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand getCommand(int index){\n")
    d("\t\treturn timedCommands[index];\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic int getLength(){\n")
    d("\t\treturn timedCommands.length;\n")
    d("\t}\n")
    d("}\n")
    #writing turrent_file
    def d(a):
        turrent_file.write(a)

    d("package frc.robot.autocmds."+plan_name+"_;\n")
    d("\n")
    d("import frc.robot.Toolkit.TimedCommand;\n")
    d("import frc.robot.Toolkit.TimedCommandGroup;\n")
    d("//import frc.robot.autocmds."+plan_name+"_.TurrentCmd.*;\n")
    d("import frc.robot.subsystems.TurrentSystem;\n")
    d("\n")
    d("public class TurrentGroup extends TimedCommandGroup{\n")
    d("\tprivate final TurrentSystem sys;\n")
    d("\tpublic TurrentGroup(TurrentSystem _sys){\n")
    d("\t\tsys = _sys;\n")
    d("\n")
    d("\t}\n")
    d("\n")
    d("\n")
    d("\tTimedCommand[] timedCommands = null;\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand[] getCommands(){\n")
    d("\t\treturn timedCommands;\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic TimedCommand getCommand(int index){\n")
    d("\t\treturn timedCommands[index];\n")
    d("\t}\n")
    d("\n")
    d("\t@Override\n")
    d("\tpublic int getLength(){\n")
    d("\t\treturn timedCommands.length;\n")
    d("\t}\n")
    d("}\n")















































































































'''

## Words from someone who don't talk much
Her name is Lili,
but please also remember the name 'Judy'.
Because though the two names are short,
the stories behind them are quite long.

I won't explain them now,
and I won't explain them in the future.
I don't determine which name is better,
because they mean different to me no matter.

Yet yet,
none your business.
Let the words rest here,
and farewell.

'''