import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int year = scanner.nextInt();
            String word;
            switch (year) {
                case 1:
                    word = "freshman";
                    break;
                case 2:
                    word = "sophomore";
                    break;
                case 3:
                    word = "junior";
                    break;
                case 4:
                    word = "senior";
                    break;
                default:
                    word = null;
            }
            if (word == null) {
                System.out.println("There is no year " + year + " in university.");
            } else {
                System.out.println("Year " + year + " student is " + word + ".");
            }
        }
    }

}
