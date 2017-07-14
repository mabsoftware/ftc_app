package org.firstinspires.ftc.teamcode;

/**
 * Constants class
 * @author Max Bowman
 * @version 7/14/2017
 * Provides a series of necessary constants for encoder-based
 * driving.
 */
public class Constants {
    // Constants for figuring distance using motor encoders.
    public static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    public static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    public static final double WHEEL_DIAMETER_INCHES = 5.3125;     // For figuring circumference
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);
}