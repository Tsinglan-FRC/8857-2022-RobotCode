package frc.robot.commands;

// import java.sql.Time;
import java.util.function.Supplier;

// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;
// import frc.robot.Constants.MotorConstants;
import frc.robot.Constants.MotorConstants.Telepid;

public class TankDrive extends CommandBase {
  /*
   * private final DriveSystem m_drivesystem;
   * private final Double speedFunction, turnFunction;
   * private final boolean lowspeedFuntion;
   * 
   * public ArcadeDrive(DriveSystem m_drive, double speed, double turn, boolean
   * lowspeed) {
   * m_drivesystem = m_drive;
   * speedFunction = speed;
   * turnFunction = turn;
   * lowspeedFuntion = lowspeed;
   * addRequirements(m_drive);
   * // Use addRequirements() here to declare subsystem dependencies.
   * }
   */

  private final DriveSystem driveSubsystem;
  private final Supplier<Double> LspeedFunction, RspeedFunction;
  // private final Timer timer = new Timer();

  public TankDrive(DriveSystem driveSubsystem, //
      Supplier<Double> lspeedFunction, Supplier<Double> rspeedFunction) {
    this.LspeedFunction = lspeedFunction;
    this.RspeedFunction = rspeedFunction;
    this.driveSubsystem = driveSubsystem;
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveSubsystem.setDrivePID(Telepid.kP, Telepid.kI, Telepid.kD, Telepid.kF, Telepid.kIZone, Telepid.Maxout);
    driveSubsystem.setBrake(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double lspeed = LspeedFunction.get();
    double rspeed = RspeedFunction.get();

    if (Math.abs(lspeed) < 0.05)
      lspeed = 0;

    if (Math.abs(rspeed) < 0.05)
      rspeed = 0;

    /*
     * if (Math.abs(lspeed) + Math.abs(rspeed) == 0) {
     * if (Math.abs(driveSubsystem.getleftspeed()) +
     * Math.abs(driveSubsystem.getrightspeed()) > 1000) {
     * for (int n = 0; n < 10; n++) {
     * driveSubsystem.setLeftSpeed(0);
     * driveSubsystem.setRightSpeed(0);
     * }
     * 
     * } else {
     * driveSubsystem.setLeftSpeed(0);
     * driveSubsystem.setRightSpeed(0);
     * 
     * }
     * 
     * } else {
     * driveSubsystem.setLeftSpeed(lspeed);
     * driveSubsystem.setRightSpeed(rspeed);
     * }
     */
    driveSubsystem.setLeftSpeed(lspeed);
    driveSubsystem.setRightSpeed(rspeed);
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsystem.setBrake(true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
