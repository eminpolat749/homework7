package csd;

class App {
	public static void main(String [] args)
	{
		PrintRandomDateTest.run();		
	}
}

class PrintRandomDateTest {
	public static void run()
	{
		java.util.Scanner kb = new java.util.Scanner(System.in);
		java.util.Random r = new java.util.Random();
		
		System.out.print("Bir sayı giriniz:");
		int count = kb.nextInt();
		
		while (count-- > 0)
			DateUtil.printRandomDate(r);		
		
	}
}

class DateUtil {
	
	public static void printRandomDate(java.util.Random r)
	{
		int year = r.nextInt(1900, 2101);
		int month = r.nextInt(1, 13);		
		int day = r.nextInt(1, getDays(month, year) + 1);	
		
		printDate(day, month, year);
	}
	
	public static void printDate(int day, int month, int year)
	{
		System.out.print(day);
		printDaySuffix(day);
		System.out.print(' ');
		printMonth(month);
		System.out.print(' ');
		System.out.println(year);	
		
	}
	
	public static void printDaySuffix(int day)
	{
		System.out.print(
			switch (day) {
			case 1, 21, 31 -> "st";
			case 2, 22 -> "nd";
			case 3, 23 -> "rd";
			default -> "th";
			}
		);
	}
	
	public static void printMonth(int month)
	{
		System.out.print(
			switch (month) {
			case 1 -> "Jan";
			case 2 -> "Feb";
			case 3 -> "Mar";
			case 4 -> "Apr";
			case 5 -> "May";
			case 6 -> "Jun";
			case 7 -> "Jul";
			case 8 -> "Aug";
			case 9 -> "Sep";
			case 10 -> "Oct";
			case 11 -> "Nov";
			default -> "Dec";			
			}
		);
	}
	
	public static int getDayOfWeek(int day, int month, int year)
	{
		int totalDays;
		
		if (year < 1900 || (totalDays = getDayOfYear(day, month, year)) == -1)
			return -1;
		
				
		return getTotalDays(year, totalDays) % 7;
	}	
	public static int getTotalDays(int year, int totalDays)
	{
		for (int i = 1900; i < year; ++i) {
			totalDays += 365;
			if (isLeapYear(i))
				++totalDays;
		}
		return totalDays; 
	}
	
	public static boolean isWeekend(int day, int month, int year)
	{
		int dayOfWeek = getDayOfWeek(day, month, year);
		
		return dayOfWeek == 0 || dayOfWeek == 6;
	}
	
	public static boolean isWeekDay(int day, int month, int year)
	{
		return !isWeekend(day, month, year);
	}	
	public static int getDayOfYear(int day, int month, int year)
	{
		if (!isValidDate(day, month, year))
			return -1;
		
		int total = day;
		
		switch (month - 1) {
		case 11:
			total += 30;
		case 10:
			total += 31;
		case 9:
			total += 30;
		case 8:
			total += 31;
		case 7:
			total += 31;
		case 6:
			total += 30;
		case 5:
			total += 31;
		case 4:
			total += 30;
		case 3:
			total += 31;
		case 2:
			total += 28;
			if (isLeapYear(year))
				++total;
		case 1: 
			total += 31;
		}		
		
		return total;
	}
	public static boolean isValidDate(int day, int month, int year)
	{
		return 1 <= day && day <= 31 && 1 <= month && month <= 12 && day <= getDays(month, year);		
	}
	
	public static int getDays(int month, int year)
	{
		int days = 31;
		
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			days = 28;
			if (isLeapYear(year))
				++days;
		}
		
		return days;
	}
	
	public static boolean isLeapYear(int year)
	{
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}
}
