import java.io.File;
import java.io.FileWriter;
import java.io.*;



public class Frisbee 
{
	public static void main (String [] args) throws IOException
	{
		// variables declaration
		
		double alpha; //angle of attack in rads
		double theta = (10 * 2 * Math.PI)/360;  //launch angle in rads which is the same as the tilt 
		double Vangle = theta;
		double g = 9.81;   //gravity 
		double Fd;	//Drag force
		double Fl;  //Lift force
		double p = 1.23;	//air density 
		double d = 0.26;	//diameter of standard frisbee 
		double A;	//area 
		double dt = 0.001; 	//delta t in secs
		double V = 14;	//velocity in m/s
		double Cd;	//drag coefficient
		double Cl;	//lift coefficient 
		double Cdi = 0.08;	//
		double Cdalpha = 2.72;//
		double alpha0; //angle of attack that produces the least lift in rads
		double Cli = 0.15;  //
		double Clalpha = 1.4;//
		double Vx;		//velocity on the x axis 
		double Vy; 		//velocity on the y axis 
		double posX = 0;	//position of the frisbee on the x axis in m
		double posY = 1;	//position of the frisbee on the y axis in m
		double m = 0.175; //mass in kg
		double accelx;		//acceleration on x axis 
		double accely;		//acceleration on y axis 
		double Fdragx;
		double Fdragy;
		double Fliftx;
		double Flifty;
		double dtin = dt;
		double maxY = 0;
		
		File file = new File("C://Users//khali//Documents//term_project//Frisbee//Code//FrisbeeTrajectory.data");
		
		FileWriter writer = new FileWriter(file);
		
		
		
		
		
		
		// Initial Values
		Vangle = theta;
		Vx = V * Math.cos(Vangle);
		Vy = V * Math.sin(Vangle);
		alpha = theta - Vangle;
		Cd = Cd(alpha);
		Cl = Cl(alpha);
		Fdragx = Fdragx(Cd, V, Vx);
		Fliftx = Fliftx(Cl, V, Vx);	
		accelx = accelerationx(Fdragx, Fliftx);
		Fdragy = Fdragy(Cd, V, Vy);
		Flifty = Flifty(Cl, V, Vy);
		accely = accelerationy(Fdragy, Flifty); 
		posX = deltax(Vx, accelx, dt);
		posY = posY + deltay(Vy, accely, dt);
		Vx = newvelx(Vx, accelx, dt);
		Vy = newvely(Vy, accely, dt);
		
		
		while( posY > 0.001 )
		{
			V = Velocity(Vx, Vy);
			Vangle = Vangle(Vx, Vy);
			alpha = theta - Vangle;
			Cd = Cd(alpha);
			Cl = Cl(alpha);
			Fdragx = Fdragx(Cd, V, Vx);
			Fliftx = Fliftx(Cl, V, Vx);	
			accelx = accelerationx(Fdragx, Fliftx);
			Fdragy = Fdragy(Cd, V, Vy);
			Flifty = Flifty(Cl, V, Vy);
			accely = accelerationy(Fdragy, Flifty); 
			posX = posX + deltax(Vx, accelx, dt);
			posY = posY + deltay(Vy, accely, dt);
			Vx = newvelx(Vx, accelx, dt);
			Vy = newvely(Vy, accely, dt);
			dt = dt + dtin;
			
			if(posY < 0)
			{
				break;
			}
			
			
			if(maxY < posY)
			{
				maxY = posY;
			}
			
				
			System.out.println("position on x: " + posX + "  position on y: " + posY + "  time: " + dt);
			String newLine = Double.toString(posX) + "," + Double.toString(posY) + "," + Double.toString(dt);
			writer.write(newLine);
			writer.write('\n');
			
			
			
		}//while loop end
			writer.close();
			
			System.out.println();
			System.out.println("max position on y axis: " + maxY);
			System.out.println("Final position on x: " + posX + "  Final position on y: " + posY + "   Final time: " + dt);
		}//end of main
		
		// Methods
		public static double Velocity (double Vx, double Vy)
		{
			double Velocity;
			Velocity = Math.sqrt(Math.pow(Vx, 2) + Math.pow(Vy, 2));
			return Velocity;
		}
		
		public static double alpha (double Vangle)
		{
			double alpha;
			double theta = (10 * 2 * Math.PI)/360;
			alpha = theta - Vangle;
			return alpha; 
		}
		
		public static double Vangle(double Vx, double Vy )
		{
			double Vangle;
			Vangle = Math.atan(Vy/Vx);
			return Vangle;
		}
		
		public static double accelerationx ( double Fdragx, double Fliftx)
		{
			double accelerationx;
			double mass = 0.175; 
			accelerationx = (Fdragx + Fliftx)/mass;
			return accelerationx;
		}

		public static double accelerationy ( double Fdragy, double Flifty )	
		{
			double accelerationy;
			double g = 9.8;
			double mass = 0.175;
			accelerationy = (Fdragy + Flifty - mass * g)/mass;
			return accelerationy;
		}
		
		public static double newvelx (double velx0, double accx, double dt)
		{
			double newvelx;
			newvelx = velx0 + accx * dt;
			return newvelx;
		}
		
		public static double newvely (double vely0, double accy, double dt)
		{
			double newvely;
			newvely = vely0 + accy * dt;
			return newvely;
		}
		
		public static double deltax ( double vel0x, double accx, double dt)
		{
			double deltax;
			deltax = vel0x * dt + ( accx * dt * dt)/2;
			return deltax;
		}
		
		public static double deltay ( double vel0y, double accy, double dt)
		{
			double deltay;
			deltay = vel0y * dt + ( accy * dt * dt)/2;
			return deltay;
		}

		public static double Cd ( double alpha )
		{
			double Cd;
			double alpha0 = -4 * Math.PI/180;
			Cd = 0.08 + 2.72 * (Math.pow((alpha - alpha0),2));
			return Cd;
		}
		
		public static double Cl ( double alpha )
		{
			double Cl;
			Cl = 0.15 + 1.4 * alpha;
			return Cl;
		}
		
		public static double Fdragx ( double Cd, double V, double Vx )
		{
			double Fdragx;
			double d = 0.26;
			double A = Math.PI * Math.pow(d,2)/4;
			Fdragx = ( Cd * 1.23 * A * V * Vx )/2;
			return Fdragx;
		}
		
		public static double Fdragy ( double Cd, double V, double Vy )
		{
			double Fdragy;
			double d = 0.26;
			double A = Math.PI * Math.pow(d,2)/4;
			Fdragy = ( Cd * 1.23 * A * V * Vy )/2;
			return Fdragy;
		}
		
		public static double Fliftx ( double Cl, double V, double Vx )
		{
			double Fliftx;
			double d = 0.26;
			double A = Math.PI * Math.pow(d,2)/4;
			Fliftx = ( Cl * 1.23 * A * V * Vx )/2;
			return Fliftx;
		}
		
		public static double Flifty ( double Cl, double V, double Vy )
		{
			double Flifty;
			double d = 0.26;
			double A = Math.PI * Math.pow(d,2)/4;
			Flifty = ( Cl * 1.23 * A * V * Vy )/2;
			return Flifty;
		}
}//public class end 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
