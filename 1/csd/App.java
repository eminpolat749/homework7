package csd;

class App {
	public static void main(String [] args)
	{
		LottoSimulationApp.run();		
	}
}

class LottoSimulationApp {
	public static void run()
	{
		java.util.Scanner kb = new java.util.Scanner(System.in);
		
		for (;;) {
			System.out.print("Kaç kez oynatmak istersiniz:");
			int count = Integer.parseInt(kb.nextLine());
			
			if (count <= 0)
				break;
			
			LottoSimulation simulation = new LottoSimulation();
			simulation.run(count);
			
			System.out.printf("1. oyun kazanma olasılığı:%f%n", simulation.p1);
			System.out.printf("2. oyun kazanma olasılığı:%f%n", simulation.p2);
			System.out.printf("3. oyun kazanma olasılığı:%f%n", simulation.p3);
		}		
	}
}

class LottoSimulation {
	public double p1;
	public double p2;
	public double p3;
	
	public void run(int count)
	{
		java.util.Random r = new java.util.Random();
		
		int winCount1, winCount2, winCount3;
		
		winCount1 = winCount2 = winCount3 = 0;
		
		for (int i = 0; i < count; ++i) {
			Lotto lotto = new Lotto(r);
			
			lotto.play();
			
			if (lotto.winGame1)
				++winCount1;
			
			if (lotto.winGame2)
				++winCount2;
			
			if (lotto.winGame3)
				++winCount3;
		}
		
		p1 = (double)winCount1 / count;
		p2 = (double)winCount2 / count;
		p3 = (double)winCount3 / count;		
	}	
}

class Lotto {
	public boolean winGame1;
	public boolean winGame2;
	public boolean winGame3;
	public java.util.Random random;
	
	public int getFirst()
	{
		return random.nextInt(1, 100);		
	}
	
	public int getSecond(int first)
	{
		int second;
		
		while ((second = random.nextInt(1, 100)) == first)
			;
		
		return second;		
	}
	
	public int getThird(int first, int second)
	{
		int third;
		
		while ((third = random.nextInt(1, 100)) == first || third == second)
			;		
		
		return third;
	}
	
	public void playGame1(int first, int second, int third)
	{
		winGame1 = first + second + third < 150;
	}
	
	public void playGame2(int first, int second, int third)
	{
		winGame2 = NumberUtil.isPrime(first + second + third);
	}
	
	public void playGame3(int first, int second, int third)
	{
		int min = Math.min(Math.min(first, second), third);
		int max = Math.max(Math.max(first, second), third);
		int mid = first + second + third - max - min;
		
		winGame3 = max - min > mid;
		
	}
	
	public Lotto(java.util.Random r)
	{
		random = r;
	}
	
	public void play()
	{
		int first = getFirst();
		int second = getSecond(first);
		int third = getThird(first, second);
		
		playGame1(first, second, third);
		playGame2(first, second, third);
		playGame3(first, second, third);
	}	
}

class NumberUtil {
	public static boolean isPrime(long val)
	{
		if (val <= 1)
			return false;
		
		if (val % 2 == 0)
			return val == 2;
		
		if (val % 3 == 0)
			return val == 3;
		
		if (val % 5 == 0)
			return val == 5;
		
		for (long i = 7; i * i <= val; i += 2)
			if (val % i == 0)
				return false;
		
		return true;			
	}
}



