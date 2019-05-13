// Import packages
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import org.math.plot.*;
import org.math.plot.plotObjects.*;
import java.util.Arrays;

public class Frisbee 
{
	public static main (String [] args)
	{
		int alpha; //angle of attack in rads
		int theta = 0;  //launch angle in rads
		double g = 9.81;   //gravity 
		double Fd;	//Drag force
		double Fl;  //Lift force
		double p = 1.23;	//air density 
		double d = 0.26;	//diameter of standard frisbee 
		double A;	//area 
		double t; 	//delta t
		double V = 14;	//velocity 
		double Cd;	//drag coefficient
		double Cl;	//lift coefficient 
		double Cdi = 0.08;	//
		double Cdalpha = 2.72;//
		double alpha0; //angle of attack that produces the least lift in rads
		double Cli = 0.15;  //
		double Clalpha = 1.4;//
		double Vx;		//velocity on the x axis 
		double Vy; 		//velocity on the y axis 

		alpha = 
		alpha0 = -4 * Math.PI/180;
		A = Math.PI * Math.pow(d,2)/4;
		Cd = Cdi + Cdalpha * (Math.pow((alpha - alpha0),2);
		Fd = -1 * (Cd * p * A * Math.pow(V,2))/2;
		Cl = Cli + Clalpha * alpha;
		Fl = (p * Math.pow(V,2) * A * Cl)/2;
		Vx = V * Math.cos(theta);
		Vy = V * Math.sin(theta)
		
		}
		
		public static double Vangle(double Vx, double Vy )
		{
			Vangle = Math.atan(Vy/Vx);
			return Vangle;
		}
		
		public static double accelerationx (double mass, double Fdragx, double Fliftx)
		{
			accelerationx = (Fdragx + Fliftx)/mass;
			return accelerationx;
		}

		public static double accelerationy (double mass, double Fdragy, double Flifty, double Fgravity )	
		{
			accelerationy = (Fdragy + Flifty - Fgravity)/mass;
			return accelerationy;
		}
		
		public static double newvelx (double velx0, double accx, double dt)
		{
			newvelx = velx0 + accx * dt;
			return newvelx;
		}
		
		public static double newvely (double vely0, double accy, double dt)
		{
			newvely = vely0 + accy * dt;
			return newvely;
		}
		
		public static double deltax ( double vel0x, double accx, double dt)
		{
			deltax = vel0x * dt + ( accx * dt * dt)/2;
			return deltax;
		}
		
		public static double deltay ( double vel0y, double accy, double dt)
		{
			deltay = vel0y * dt + ( accy * dt * dt)/2;
			return deltay;
		}

		
		
