import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

import java.io.*;
import java.util.concurrent.locks.ReentrantLock;

import java.util.Random;

public class RaceTrack extends JPanel
{
	private static JFrame frame;
	private static JButton start;
	private static JButton reset;
	private static JButton pause;
	//private static BufferedImage carPic;
	private static boolean running;
	private static boolean finished;
	private static boolean paused;
	
	private static int racer1;
	private static int racer2;
	private static int racer3;	
	
	final private static int startLine = 15;
	final private static int finishLine = 400;
	private static ReentrantLock thread = new ReentrantLock();
	
	private static Thread racer1Thread;
	private static Thread racer2Thread;
	private static Thread racer3Thread;

	public static void initalize()
	{
		finished = false;
		paused = false;
		running = false;
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		RaceTrack rc = new RaceTrack();
		frame.setContentPane(rc);
		
		JPanel button = new JPanel();
		button.setLayout(new FlowLayout());
		start = new JButton("Start");
		addStart();
		button.add(start);
		pause = new JButton("Pause");
		addPause();
		button.add(pause);
		reset = new JButton("Reset");
		addReset();
		button.add(reset);
		
		frame.add(button);
		frame.setTitle("Race Track");
		
		racer1 = startLine;
		racer2 = startLine;
		racer3 = startLine;
	}
	
	public static void main(String args[])
	{
		initalize();
		frame.setSize(500,200);
		frame.repaint();
		frame.setVisible(true);
	}

	public static void addStart()
	{
		start.addActionListener(new ActionListener()
				{ @Override
					public void actionPerformed(ActionEvent e)
					{
					
						thread.lock();
						if (!finished) {
							if (!paused && !running) {
								running = true;
								runRace();	
							}
							paused = false;
						}
						frame.repaint();
						thread.unlock();
					}
					
				}
		);
		
	}
	
	public static void addPause()
	{
		pause.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						thread.lock();
						if (running) {
							paused = true;
						}
						thread.unlock();
					}
			
				}	
				
				);
		
	}
	
	public static void addReset()
	{
		reset.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						thread.lock();
						running = false;
						finished = false;
						paused = false;
						racer1 = startLine;
						racer2 = startLine;
						racer3 = startLine;
						thread.unlock();
						
					}
				}
				
				);
	}
	
	public static void runRace()
	{
		racer1 = startLine;
		racer2 = startLine;
		racer3 = startLine;
		
		racer1Thread = new Thread(()->
		{	
			Random rand = new Random();
			
			while(running)
			{
				if(racer1<finishLine && !paused)
				{
					int randVal = rand.nextInt(11);
					int adjustedVal = racer1+randVal;
					
					if(adjustedVal > finishLine)
					{
						racer1=finishLine;
					}
					else
					{
						racer1=adjustedVal;
					}
				}
				try
				{
					Thread.sleep(50);
				}
				catch(InterruptedException e)
				{
					
				}
			}
		}
		
	
	
	);
		racer1Thread.start();
		
		
		racer2Thread = new Thread(()->
		{
			Random rand = new Random();
			
			while(running)
			{
				if(racer2<finishLine && !paused)
				{
					int randVal = rand.nextInt(11);
					int adjustedVal = racer2+randVal;
					
					if(adjustedVal > finishLine)
					{
						racer2=finishLine;
					}
					else
					{
						racer2=adjustedVal;
					}
				}
				try
				{
					Thread.sleep(50);
				}
				catch(InterruptedException e)
				{
					
				}
			}
		}
		
				
				
				
				);
		racer2Thread.start();
		
		
		racer3Thread = new Thread(()->
		{
			
			Random rand = new Random();
			
			while(running)
			{
				if(racer3<finishLine && !paused)
				{
					int randVal = rand.nextInt(11);
					int adjustedVal = racer3+randVal;
					
					if(adjustedVal > finishLine)
					{
						racer3 = finishLine;
					}
					else
					{
						racer3=adjustedVal;
					}
				}
				try
				{
					Thread.sleep(50);
				}
				catch(InterruptedException e)
				{
					
				}
			}
		}		
				);
		racer3Thread.start();
		
	}// end of runrace method
	
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		BufferedImage carPic=null;
		File file = null;
		
		try
		{
			//System.out.println(System.getProperty("user.dir"));
			//carPic = ImageIO.read(getClass().getResource("src/sportive-car.png"));
			carPic = ImageIO.read(new File("src/sportive-car.png"));
			//carPic = ImageIO.read(getClass().getResourceAsStream("/sportive-car.png"));
		}
		catch(IOException e)
		{
			System.out.println("Car image is unable to be loaded");
			System.exit(0);
		}
		
		//File file = new File("/sportive-car.png");
//////////////////////////////////////////////////////////////////
//		try
//		{
//			file = new File("sportive-car.png");
//			//carPic = new BufferedImage(10, 10,BufferedImage.TYPE_INT_ARGB);
//		    carPic = ImageIO.read(file);
//		}
//		catch(IOException e)
//		{
//			System.out.println("Car image is unable to be loaded");
//			System.exit(0);
//		}
//////////////////////////////////////////////////////////////////
		g.setColor(Color.GRAY);
		
		int begin = startLine + 35;
		int end = finishLine - 20;
		
		g.drawRect(begin, 50, end, 12);
		g.drawRect(begin, 100, end, 12);
		g.drawRect(begin, 150, end, 12);
		
		g.fillRect(begin, 50, end, 12);
		g.fillRect(begin, 100, end, 12);
		g.fillRect(begin, 150, end, 12);
		g.drawImage(carPic, racer1, 40, this);
		g.drawImage(carPic, racer2, 90, this);
		g.drawImage(carPic, racer3, 140, this);
		thread.lock();
		if (!finished) {
			int win = winner();
			alertWin(win);	
		} else {
			frame.repaint();
		}
		thread.unlock();
		
	}
	
	public static int winner()
	{
		if(racer1 >= finishLine)
		{
			return 1;
		}
		if(racer2 >= finishLine)
		{
			return 2;
			
		}
		if(racer3 >= finishLine)
		{
			return 3;
		}
		return -1;
	}
	
	public synchronized void alertWin(int winner)
	{
		if(winner > 0)
		{
			finished = true;
//			racer1Thread.stop();
//			racer2Thread.stop();
//			racer3Thread.stop();
			
			racer1Thread.interrupt();
			racer2Thread.interrupt();
			racer3Thread.interrupt();
			
			
			frame.repaint();
			JOptionPane.showMessageDialog(frame, "We have a winner: car " + winner);
		}
		frame.repaint();
		
	}
		
}// end of class