import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;



public class FoodProducer extends Thread 
{
	private FoodBank bank;
	ReentrantLock thread = new ReentrantLock();

	public FoodProducer(FoodBank bank)
	{
		this.bank= bank;
	}
	
	@Override
	public synchronized void run()
	{
		while(true)
		{
			Random ran = new Random();
			int random = ran.nextInt(100)+1;
			System.out.println("Waiting to add food");
			thread.lock();
			
			if(random>=0)
			{
			this.bank.giveFood(random);
			}
			else
			{
				System.out.println("Waiting to add food, producer attempted to add " + random + " to the food bank");
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
}
