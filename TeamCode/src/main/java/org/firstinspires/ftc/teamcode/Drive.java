/**
 * Drive Interface
 * @author Max Bowman
 * @version 7/14/2017
 * Provides an interface so we can quickly swap
 * what kind of drive we want to use.
 */
package org.firstinspires.ftc.teamcode;

public interface Drive {
    public void drive(int left, int right);
    public void turn(int degrees);
    public void setSpeeds(double left, double right);
    public void setSpeed(double speed);
}
