package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp
        ( name = "tele", group = "Cascade")
public class tele extends OpMode {
public DcMotor frontLeft, frontRight, backRight, backLeft;
public DcMotor casCade;
public Servo claw;

        double lefslide, rightslide;
        double leftmove, rightmove;

@Override
public void init(){
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        casCade = hardwareMap.dcMotor.get("casCade");
        claw = hardwareMap.servo.get("claw");


        }
@Override
public void loop(){

        /* Game pad 1 */

        if(Math.abs(gamepad1.left_stick_y)>.5){
        frontLeft.setPower(-.5);
        backLeft.setPower(-.5);
        }
        else if (Math.abs(gamepad1.right_stick_y)>.5){
        frontRight.setPower(.5);
        backRight.setPower(.5);
        }
        else{
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        backLeft.setPower(0);
        }

        if(Math.abs(gamepad1.left_stick_x)>.1){
        frontRight.setPower(.5);
        frontLeft.setPower(.5);
        backRight.setPower(-.5);
        backLeft.setPower(-.5);
        }
        else if(Math.abs(gamepad1.left_stick_x)>-.1){
        frontRight.setPower(-.5);
        frontLeft.setPower(-.5);
        backRight.setPower(.5);
        backLeft.setPower(.5);
        }







        /* Game pad 2 */
        if(gamepad2.a){
        claw.setPosition(.5);
        }
        else if(gamepad2.b){
        claw.setPosition(-.5);
        }
        else{
        claw.setPosition(0);
        }

        if(gamepad2.left_bumper){
        casCade.setPower(.5);
        }
        else if(gamepad2.right_bumper){
        casCade.setPower(-.5);
        }
        else{
        casCade.setPower(0);
        }



        }
        }
