import java.util.Scanner;
import static java.lang.Math.*;
public class TripPlanner {
    public static void main(String[] args){
        hello();
        plans();
        timeDifference();
        countryArea();
        distances();
    }
    public static void hello(){
        Scanner input = new Scanner(System.in);
        System.out.print("Welcome to your trip planner! What is your name? ");
        String name = input.nextLine();
        System.out.println("Nice to meet you, " + name + "!");
        System.out.print("Where would you like to go on your trip? ");
        String destination = input.nextLine();
        System.out.println(destination + " sounds like a great destination for a trip!");
    }
    public static void plans(){
        Scanner input = new Scanner(System.in);
        System.out.println("Lets make a plan for your trip!");
        System.out.print("How many days are you planning to stay there? ");
        int duration = input.nextInt();
        System.out.print("What is your total budget in USD? ");
        double budgetUSD = input.nextDouble();
        double dailyUSD = ((int)((budgetUSD / duration) * 100))/ 100.0;
        System.out.print("What is the currency symbol for your destination? ");
        String currency = input.next();
        System.out.print("What is the conversion rate between USD and " + currency + "? If, for example, one USD \nis worth 0.9 " + currency + " then type 0.9. ");
        double conversionRate = input.nextDouble();
        double dailyCurrency = ((int)((budgetUSD * conversionRate / duration) * 100))/ 100.0;
        System.out.println("Your trip will last " + duration + " days, which is " + (duration * 24) + " hours, or " + (duration * 24 * 60) + " minutes, or " + (duration * 24 *60 * 60) + " seconds!");
        System.out.println("Your budget is " + (int)budgetUSD + " USD, and you can spend " + dailyUSD + "USD per day!");
        System.out.println("In local currency, you have " + (int)(budgetUSD * conversionRate) + " " + currency + "! That amounts to " + dailyCurrency + " " + currency + " you can spend per day!");
    }
    public static void timeDifference(){
        Scanner input = new Scanner(System.in);
        System.out.print("What is the time zone difference between your country and your destination? ");
        double timeDifference = input.nextDouble();
        System.out.println("When it's midnight in your country, it will be " + ((24 + (int)timeDifference) % 24) + ":00 at your destination. When it's noon in your country, it will be " + (int)((36 + timeDifference) % 24) + ":00 at your destination!");
    }
    public static void countryArea(){
        Scanner input = new Scanner(System.in);
        System.out.print("What is the area of your destination country (expressed in square km)? ");
        double area = input.nextDouble();
        double areaRounded = ((int)(0.386102 * area * 100)) / 100.0;
        System.out.println("Area of your destination country is " + areaRounded + " square miles!");
    }
    public static void distances(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your latitude in degrees: ");
        double lat1 = input.nextDouble();
        double radlat1 = toRadians(lat1);
        System.out.print("Please enter your longitude in degrees: ");
        double long1 = input.nextDouble();
        double radlong1 = toRadians(long1);
        System.out.print("Please enter your destination latitude in degrees: ");
        double lat2 = input.nextDouble();
        double radlat2 = toRadians(lat2);
        System.out.print("Please enter your destination longitude in degrees: ");
        double long2 = input.nextDouble();
        double radlong2 = toRadians(long2);
        double distance = 2 * 6371 * asin(sqrt(pow((sin((radlat2 - radlat1)/2)), 2) + cos(radlat1) * cos(radlat2) * pow((sin((radlong2 - radlong1)/2)), 2)));
        double distanceRounded = ((int)((distance) * 100))/ 100.0;
        System.out.println("Distance between your location and your destination is " + distanceRounded + " km!");
    }
}