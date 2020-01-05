import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class FoodConsumer extends Thread
{
	FoodBank bank;
	ReentrantLock thread = new ReentrantLock();
	
	
	public FoodConsumer(FoodBank bank)
	{
		this.bank = bank;
	}
	
	@Override
	public synchronized void run()
	{
		while(true)
		{
			Random rand = new Random();
			int random = rand.nextInt(100)+1;
			System.out.println("Waiting to retrieve food");
			
			thread.lock();
			
			if(random>=0)
			{
				bank.takeFood(random); 
			}
			else
			{
				System.out.println("Waiting to retrieve food, consumer attempted to take " + random + " from the food bank");
			}
			
			thread.unlock(); 
			
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException e)
			{
				
			}
			
			
			
		}
	}
	

}// end of class