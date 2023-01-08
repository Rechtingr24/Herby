// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int BACK_LEFT_ID = 0;
        public static final int BACK_RIGHT_ID = 1;
        public static final int FRONT_LEFT_ID = 2;
        public static final int FRONT_RIGHT_ID = 3;

        public static final double TRACK_WIDTH = Units.inchesToMeters(14);

        public static final double MAX_ROTATION_MS = .2;

        public static final double RADIAN_ROTATION = 5;

        public static final double WHEEL_BASE = Units.inchesToMeters(16);

        public static final Translation2d FRONT_RIGHT_TRANSLATION = new Translation2d(
                (TRACK_WIDTH / 2), (-WHEEL_BASE / 2)
        );
        public static final Translation2d BACK_RIGHT_TRANSLATION = new Translation2d(
                (-TRACK_WIDTH / 2), (-WHEEL_BASE / 2)
        );
        public static final Translation2d FRONT_LEFT_TRANSLATION = new Translation2d(
                (TRACK_WIDTH / 2), (WHEEL_BASE / 2)
        );
        public static final Translation2d BACK_LEFT_TRANSLATION = new Translation2d(
                (-TRACK_WIDTH / 2), (WHEEL_BASE / 2)
        );

        public static final MecanumDriveKinematics KINEMATICS = new MecanumDriveKinematics(
                FRONT_LEFT_TRANSLATION, FRONT_RIGHT_TRANSLATION, BACK_LEFT_TRANSLATION, BACK_RIGHT_TRANSLATION
        );
    }

}
