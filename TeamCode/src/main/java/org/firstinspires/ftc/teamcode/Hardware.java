package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
/**
 * Hardware Class
 * @author Max Bowman
 * @version 7/14/2017
 * The Hardware class provides an interface with the physical hardware
 * of the robot - in other words, anything connected to the Android
 * phone's USB bus. All OpModes have a variable called hwMap that
 * contains all of these USB devices, and this class is simply an
 * easier-to-use wrapper of that. If there is a device you would like
 * to interface with here, please make the variable referencing it public
 * so you can access it from other classes.
 */
public class Hardware {
    public DcMotor frontLeft = null;
    public DcMotor frontRight = null;
    public DcMotor backLeft = null;
    public DcMotor backRight = null;

    private HardwareMap hwMap = null;

    public Hardware() {

    }

    public void init(HardwareMap hwMap, boolean usingEncoders) {
        this.hwMap = hwMap;

        this.frontLeft = this.hwMap.dcMotor.get("front_left");
        this.frontRight = this.hwMap.dcMotor.get("front_right");
        this.backLeft = this.hwMap.dcMotor.get("back_left");
        this.backRight = this.hwMap.dcMotor.get("back_right");

        this.frontLeft.setDirection(DcMotor.Direction.FORWARD);
        this.backLeft.setDirection(DcMotor.Direction.FORWARD);
        this.frontRight.setDirection(DcMotor.Direction.REVERSE);
        this.backRight.setDirection(DcMotor.Direction.REVERSE);

        this.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        if (usingEncoders) {
            this.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }
        else {
            this.frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public HardwareMap getHwMap() {
        return hwMap;
    }
}
