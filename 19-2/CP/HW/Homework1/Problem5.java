import java.util.Scanner;

public class Problem5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        for (int i = 0; i < size; i++) {
            int numSlash = 20 - 4 * i,
                numStar = 8 * i;
            for (int j = 0; j < numSlash; j++) {
                System.out.print("/");
            }
            for (int j = 0; j < numStar; j++) {
                System.out.print("*");
            }
            for (int j = 0; j < numSlash; j++) {
                System.out.print("\\");
            }
            System.out.println();
        }
    }
}