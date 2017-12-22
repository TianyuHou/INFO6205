public class assignment2{


/**
 * This Main class aims to test all the questions;
 * @author TianyuHou
 *
 */

public static void main(String[] args) {
		// test question 1
		System.out.println(employeeSalary(46.0));

		// test question 2
		System.out.println(addDigits(98));

		// test question 3
		printPerfectNumbers(500);
		System.out.println();

		// test question 6 extra credit
		printIsoscelesTriangle(6);
		System.out.println();

		// test question 4 and 5
		pizza pizza6inch = new pizza("6inch", 1.6, 6.0, 2);
		pizza pizza8inch = new pizza("8inch", 2.5, 8.0, 3);
		pizza pizza10inch = new pizza("10inch", 3.2, 10.0, 1);
		pizza[] orderPizza = { pizza6inch, pizza8inch, pizza10inch };

		customer peter = new customer("name", orderPizza);
		System.out.println(String.format("%.2f", peter.moneySpend()));
}





	/**
	 * 1. Write a java function to calculate the salary of an employee based on the
	 * following rules.
	 * 
	 * i. function takes input of number of hours an employee worked and returns the
	 * salary. ii. The first 36 hours worked are paid at a rate of 15.0, then the
	 * next 5 hours are paid at a rate of 15 * 1.5. Hours after that up to a max of
	 * 48 are paid at a rate of 15 * 2.
	 * 
	 * @param hours
	 * @return
	 * @author TianyuHou
	 */

	
	public static double employeeSalary(double hours) {
		if(hours<0) {
			System.out.println("hours must be larger than 0");
			return -1;
		}
		
		double firstRate = 15;
		double secondRate = 15 * 1.5;
		double thirdRate = 15 * 2;

		if (hours >= 48) {
			return 36 * firstRate + 5 * secondRate + 7 * thirdRate;
		} else if (hours >= 36 + 5) {
			return 36 * firstRate + 5 * secondRate + (hours - 36 - 5) * thirdRate;
		} else if (hours >= 36) {
			return 36 * firstRate + (hours - 36) * secondRate;
		} else {
			return hours * firstRate;
		}
	}



	/**
	 * 2.Write a java function that adds all the digits of an integer until it i
	 * single digit. i. function takes an integer as input and returns its sum of
	 * digits. ii. for example input = 37, sum = 3+7 = 10, sum = 1+0 = 1. result =
	 * 1.
	 * 
	 * @param input
	 * @return
	 * @author TianyuHou
	 */

	public static int addDigits(int input) {
		int sum = 0;
		while (true) {
			while (input != 0) {
				sum += input % 10;
				input = input / 10;
			}
			if (sum / 10 != 0) {
				input = sum;
				sum = 0;
			} else {
				return sum;
			}
		}
	}



/**
 * 3. Write a java function to print all perfect number between 1 and n. i.
 * Perfect number is a positive integer which is equal to the sum of its proper
 * positive divisors. ii. For example: 6 is the first perfect number, Proper
 * divisors of 6 are 1, 2, 3. Sum of its proper divisors = 1 + 2 + 3 = 6.
 * 
 * @param n
 * @author TianyuHou
 */

	public static void printPerfectNumbers(int n) {
		if(n<0) {
			System.out.println("n must larger than 1");
			return;
		}
		
		int sum = 0;
		int temp = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					temp += j;
				}
			}
			sum = temp;
			temp = 0;
			if (sum == i) {
				System.out.print(i + " ");
			}
		}
	}

/**
 * 6. Write a Java program that generates an isosceles right angled triangle
 * made of asterisks. i. function should take input of one equal side as
 * integer. Other than the edges the inner part of the triangle should be empty.
 * ii. For example input is 6. the function should print¡ª 
 **
 **
 * * * *
 ******
 * @author TianyuHou
 *
 */

	public static void printIsoscelesTriangle(int n) {
		if(n<1) {
			System.out.println("N must be larger than 1");
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				if (i != n) {
					if(j == 1 && j!=i) {
						System.out.print("*");
					}else if(j==i){
						System.out.println("*");
					}else {
						System.out.print(" ");
					}
				} else {
					System.out.print("*");
				}
			}
		}
	}

}