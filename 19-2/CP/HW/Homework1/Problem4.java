public class Problem4 {

    static boolean longerThan(String input, int length) {
        return (input == null) ? 0 > length : input.length() > length;
    }

    public static void main(String[] args) {
        System.out.println(longerThan("Homework", 7));
        System.out.println(longerThan("Homework", 8));
        System.out.println(longerThan(null, -1));
        System.out.println(longerThan(null, 3));
    }
}
