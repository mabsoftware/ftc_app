package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * MecanumDrive class
 * @author Max Bowman
 * @version 7/14/2017
 * Provides a set of methods to drive a robot with mecanum wheels.
 * This only works with LinearOpModes!
 */
public class MecanumDrive implements Drive {
    private Hardware robot;
    private LinearOpMode opMode;
    private double speed;

    /**
     * Constructor
     * @param robot Robot hardware wrapper
     * @param opMode Opmode
     */
    public MecanumDrive(Hardware robot, LinearOpMode opMode) {
        this.robot = robot;
        this.opMode = opMode;
        this.speed = 0;
    }

    /**
     * Encoder-based driving wrapper function
     * @param left
     * @param right
     */
    public void drive(int left, int right) {
        this.encoderDrive(left, right);
    }

    /**
     * Gyro-based turning implementation
     * @param degrees
     */
    public void turn(int degrees) {

    }

    /**
     * Uses mecanum wheels to "side step" either left or
     * right a number of inches
     * @param direction
     * @param inches
     */
    public void sideStep(char direction, int inches) {

    }

    /**
     * Sets speeds of left and right sides
     * @param left
     * @param right
     */
    public void setSpeeds(int left, int right) {
        robot.frontLeft.setPower(left);
        robot.backLeft.setPower(left);
        robot.frontRight.setPower(right);
        robot.backRight.setPower(right);
    }

    /**
     * Does the same as setSpeeds except
     * with only one argument to set both
     * sides to the same speed
     * @param speed
     */
    public void setSpeed(int speed) {
        this.setSpeeds(speed, speed);
    }

    /**
     * Sets the speed of encoder-based driving
     * @param speed
     */
    public void setEncoderDriveSpeed(int speed) {
        this.speed = speed;
    }

    public void brake() {
        this.setSpeed(0);
    }

    /**
     * Drives the motors forward / backward using motor encoders
     * Based heavily on encoderDrive(double speed, leftInches, rightInches)
     * by Robert Atkinson, (c) 2016
     * @param leftInches
     * @param rightInches
     */
    private void encoderDrive(double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;
        int avgLeftPosition;
        int avgRightPosition;

        // Ensure that the OpMode is still active
        if (this.opMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            avgLeftPosition = (robot.backLeft.getCurrentPosition() + robot.frontLeft.getCurrentPosition()) / 2;
            avgRightPosition = (robot.backRight.getCurrentPosition() + robot.frontRight.getCurrentPosition()) / 2;

            newLeftTarget = avgLeftPosition + (int) (leftInches * Constants.COUNTS_PER_INCH);
            newRightTarget = avgRightPosition + (int) (rightInches * Constants.COUNTS_PER_INCH);
            robot.frontLeft.setTargetPosition(newLeftTarget);
            robot.backLeft.setTargetPosition(newLeftTarget);
            robot.frontRight.setTargetPosition(newRightTarget);
            robot.backRight.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            robot.frontLeft.setPower(Math.abs(speed));
            robot.frontRight.setPower(Math.abs(speed));
            robot.backLeft.setPower(Math.abs(speed));
            robot.backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (this.opMode.opModeIsActive() && (robot.frontLeft.isBusy() || robot.frontRight.isBusy() || robot.backLeft.isBusy() || robot.backRight.isBusy()));

            // Stop all motion
            brake();

            // Turn off RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
