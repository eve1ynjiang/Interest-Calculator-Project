import java.text.NumberFormat;

public class Interest {
	protected double principal;
	protected double rate;
	protected double years;
	
	public Interest(double principal, double rate, double years) {
		this.principal = principal;
		this.rate = rate;
		this.years = years;
	}
	
	//returns the numeric value of the interest
	public static double simpleInterest(double principal, double rate, double 
			years) {
		return principal + (principal * (rate/100) * years);
	}
	
	//calculates the simple interest and loops through depending on the year
	public static String simpleInterestString(double principal, double rate, 
			double years) {
		String output = "Principal: " + principal + ", Rate: " + rate + "\n";
		output += "Year ----> Simple Interest Amount\n";
		int count = 1;
		
		for (int i = 1; i <= years; i++) {
			output += count + " ----> " + 
			NumberFormat.getCurrencyInstance().format(simpleInterest(
					principal, rate, i))
			+ "\n"; 
			
			count++;
		}
		return output;
	}
	
	//returns the numeric value of the interest
	public static double compoundInterest(double principal, double rate, 
			double years) {
		return principal * Math.pow((1 + rate/100), years);
	}
	
	//calculates the compound interest and loops through depending on the year
	public static String compoundInterestString(double principal, double rate, 
			double years) {
		String output = "Principal: " + principal + ", Rate: " + rate + "\n";
		output += "Year ----> Compound Interest Amount\n";
		int count = 1;
		
		for (int i = 1; i <= years; i++) {
			output += count + " ----> " + 
			NumberFormat.getCurrencyInstance().format(compoundInterest(
					principal, rate, i))
			+ "\n"; 
			
			count++;
		}
		
		return output;
	}
	
	//combines the compound and simple interest string methods
	public static String bothInterestString(double principal, double rate, 
			double years) {
		String output = "Principal: " + principal + ", Rate: " + rate + "\n";
		output += "Year ----> Simple Interest Amount ----> "
				+ "Compound Interest Amount\n";
		int count = 1;
		
		for (int i = 1; i <= years; i++) {
			output += count + " ----> "
			+ NumberFormat.getCurrencyInstance().format(simpleInterest(
					principal, rate, i))
			+ " ----> " +
			NumberFormat.getCurrencyInstance().format(compoundInterest(
					principal, rate, i))
			+ "\n"; 
			
			count++;
		}
		
		return output;
	}
	
	
}