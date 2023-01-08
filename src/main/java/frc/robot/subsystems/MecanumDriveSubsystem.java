package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


import static frc.robot.Constants.DriveConstants.KINEMATICS;

public class MecanumDriveSubsystem extends SubsystemBase {
    VictorSPX backLeft = new VictorSPX(Constants.DriveConstants.BACK_LEFT_ID);
    VictorSPX backRight = new VictorSPX(Constants.DriveConstants.BACK_RIGHT_ID);
    VictorSPX frontLeft = new VictorSPX(Constants.DriveConstants.FRONT_LEFT_ID);
    VictorSPX frontRight = new VictorSPX(Constants.DriveConstants.FRONT_RIGHT_ID);

    PIDController controller  = new PIDController(0, 0, 0);

    // With eager singleton initialization, any static variables/fields used in the
    // constructor must appear before the "INSTANCE" variable so that they are
    // initialized
    // before the constructor is called when the "INSTANCE" variable initializes.


    /**
     * The Singleton instance of this MecanumDriveSubsystem. Code should use the
     * {@link #getInstance()} method to get the single instance (rather than trying
     * to construct an instance of this class.)
     */
    private final static MecanumDriveSubsystem INSTANCE = new MecanumDriveSubsystem();



    /**
     * Returns the Singleton instance of this MecanumDriveSubsystem. This static
     * method should be used, rather than the constructor, to get the single
     * instance of this class. For example:
     * {@code MecanumDriveSubsystem.getInstance();}
     */
    @SuppressWarnings("WeakerAccess")
    public static MecanumDriveSubsystem getInstance() {
        return INSTANCE;
    }

    /**
     * Creates a new instance of this MecanumDriveSubsystem. This constructor is
     * private since this class is a Singleton. Code should use the
     * {@link #getInstance()} method to get the singleton instance.
     */
    public MecanumDriveSubsystem() {

    }

    public void chasisToWheelSpeeds(double forwardSpeed, double sideSpeed, double rotationSpeed) {
        ChassisSpeeds speeds = new ChassisSpeeds(forwardSpeed/3, sideSpeed, rotationSpeed/30);
        MecanumDriveWheelSpeeds wheelSpeeds = KINEMATICS.toWheelSpeeds(speeds);

        double frontLeftSpeed = wheelSpeeds.frontLeftMetersPerSecond;
        double frontRightSpeed = -wheelSpeeds.frontRightMetersPerSecond;
        double backLeftSpeed = wheelSpeeds.rearLeftMetersPerSecond;
        double backRightSpeed = wheelSpeeds.rearRightMetersPerSecond;

        double frontLeftSpeedPercent = frontLeftSpeed/Constants.DriveConstants.MAX_ROTATION_MS;
        double frontRightSpeedPercent = frontRightSpeed/Constants.DriveConstants.MAX_ROTATION_MS;
        double backLeftSpeedPercent = backLeftSpeed/Constants.DriveConstants.MAX_ROTATION_MS;
        double backRightSpeedPercent = backRightSpeed/Constants.DriveConstants.MAX_ROTATION_MS;



        backLeft.set(ControlMode.PercentOutput, backLeftSpeedPercent);
        backRight.set(ControlMode.PercentOutput, backRightSpeedPercent);
        frontLeft.set(ControlMode.PercentOutput, frontLeftSpeedPercent);
        frontRight.set(ControlMode.PercentOutput, frontRightSpeedPercent);

        backRight.setInverted(true);
        frontRight.setInverted(true);
    }
}
