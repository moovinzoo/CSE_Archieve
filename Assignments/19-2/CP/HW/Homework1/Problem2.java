public class Problem2 {
    public static void main(String[] args) {
        int fBefore = 1, fCurrent = 1, fAfter;

        for (int i = 0; i < 12; i++) {
            if (i == 0 || i == 1) {
                System.out.println(1);
            } else {
                fAfter = fBefore + fCurrent;
                System.out.println(fAfter);
                fBefore = fCurrent;
                fCurrent = fAfter;
            }
        }
    }
}
