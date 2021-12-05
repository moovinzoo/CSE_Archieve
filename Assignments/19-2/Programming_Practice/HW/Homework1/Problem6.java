import java.util.Scanner;

public class Problem6 {
    static int characterToIndex(char c) {
        return c - 'a';
    }

    static char indexToCharacter(int i) {
        return (char) (i + 'a');
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        // Assume that the input string contains only lower alphabets
        int numCharacter = 26;

        // Count the number of characters in the input string
        int[] alphabetCounts = new int[26];
        for (int i = 0; i < inputString.length(); i++) {
            alphabetCounts[characterToIndex(inputString.charAt(i))]++;
        }

        // Print the outputs in order
        for (int i = 0; i < numCharacter; i++) {
            int maxCount = 0;
            char maxChar = ' ';
            for (int j = 0; j < numCharacter; j++) {
                if (alphabetCounts[j] > maxCount) {
                    maxCount = alphabetCounts[j];
                    maxChar = indexToCharacter(j);
                }
            }

            // handle the value of maxCount 
            if (maxCount == 0) {
                break;
            } else alphabetCounts[characterToIndex(maxChar)] = 0;
            
            System.out.println(maxChar + " " + maxCount);
        }
    }
}
