class Super {
    static String greeting() { return "Goodnight"; }
    String name() { return "Richard"; }
}
class Sub extends Super {
    static String greeting() { return "Hello"; }
    String name() { return "Henry"; }
}
public class Test {
    public static void main(String[] args) {
        Super s = new Sub();
        System.out.println(s.name() + " " + s.greeting());
    }
}