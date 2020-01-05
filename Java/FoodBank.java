
public class FoodBank
{
	int food;
	
	public FoodBank()
	{
		food =0;
	}
	
	public void takeFood(int amount)
	{
		food = food - amount;
		System.out.println("Taking " + amount + " amount of food, the total amount is " + food); //may change to bank.food
	}
	
	public void giveFood(int amount)
	{
		food = food + amount; 
		System.out.println("Adding " + amount + " amount of food, the total amount is " + food);
		
	}

}
