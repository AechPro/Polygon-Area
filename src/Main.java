import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Main extends JPanel implements Runnable
{
	ArrayList<Integer> x = new ArrayList<Integer>();
	ArrayList<Integer> y = new ArrayList<Integer>();
	private Graphics2D g;
	private BufferedImage image;
	private static final int width=1280,height=720;
	private Thread thread;
	private boolean running;
	int area=0;
	public Main()
	{
		
		
	}
	public void addNotify()
	{
		super.addNotify();
		if(thread==null)
		{
			thread = new Thread(this);
			thread.start();
		}
	}
	public void init()
	{
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		running=true;
	}
	public void run()
	{
		init();
		while(running)
		{
			update();
			delay(1000);
		}
	}
	public void update()
	{
		initArrays();
		findArea();
		System.out.println(area);
		initArrays();
		System.out.println(findPerimeter(x,y));
	}
	public void render()
	{
		clearCanvas();
		for(int i=0;i<x.size()-1;i++)
		{
			g.drawLine(x.get(i)+(width>>1),y.get(i)+(height>>1),x.get(i+1)+(width>>1),y.get(i+1)+(height>>1));
		}
	}
	public void draw()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image,0,0,null);
		g2.dispose();
	}
	
	public int findArea()
	{
		if(x.size()<=2)
		{
			return area;
		}
		int yMax=y.get(0);
		int index=0;
		int p1=0;
		int p2=0;
		for(int i=0;i<y.size();i++)
		{
			if(y.get(i)<yMax){yMax=y.get(i); index=i;}
		}
		if(index-1<0){p1=y.size()-1;}
		else{p1=index-1;}
		if(index==y.size()-1){p2=0;}
		else{p2=index+1;}
		area+=calcArea(x.get(p1),x.get(index),x.get(p2),y.get(p1),y.get(index),y.get(p2))/2;
		x.remove(index);
		y.remove(index);
		render();
		draw();
		delay(100);
		return findArea();
	}
	public int findPerimeter(ArrayList<Integer> x, ArrayList<Integer> y)
	{
		int perimeter = 0;
		for(int i=0;i<x.size()-1;i++)
		{
			perimeter+=Math.sqrt(Math.pow(x.get(i)-x.get(i+1),2)+Math.pow(y.get(i)-y.get(i+1),2));
		}
		return perimeter;
	}
	public int calcArea(int x1, int x2, int x3, int y1, int y2, int y3)
	{
		return ((x1*Math.abs(y2-y3))+(x2*Math.abs(y3-y1))+(x3*Math.abs(y1-y2)))/2;
	}
	public void initArrays()
	{
		x = new ArrayList<Integer>();
		y = new ArrayList<Integer>();
		for(int i=0,stop=(int) (Math.random()*100);i<stop;i++)
		{
			x.add((int)(Math.random()*100));
			y.add((int)(Math.random()*100));
		}
	}
	public void clearCanvas()
	{
		g.clearRect(0,0,width,height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
	}
	public void delay(int millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch(Exception e){e.printStackTrace();}
	}
	public static void main(String[] args) 
	{
		JFrame f = new JFrame("Main");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new Main());
		f.setSize(1280,720);
		f.setVisible(true);
	}

}
