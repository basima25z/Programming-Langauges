
public class FoodBankPatrons {
	
	public static void main(String [] args)
	{
		FoodBank bank = new FoodBank();
		FoodProducer fp = new FoodProducer(bank);
		FoodConsumer fc = new FoodConsumer(bank);
		
		fp.start();
		fc.start();
	}

}
