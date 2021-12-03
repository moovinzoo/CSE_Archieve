package bank;

class BankAccount {
    private String owner, password;
    private int id, balance, numEvents;
    private Event[] events = new Event[100];

    BankAccount(String owner, String password, int id, int balance) {
        /* Complete here */
    }

    boolean authenticate(String password) {
        /* Complete here */
    }

    void deposit(int amount) {
        /* Complete here */
    }

    boolean withdraw(int amount) {
        /* Complete here */
    }

    void receive(int amount, String sourceOwner) {
        /* Complete here */
    }

    boolean send(int amount, String targetOwner) {
        /* Complete here */
    }

    int getId() {
        return id;
    }

    int getBalance() {
        return balance;
    }

    String getOwner() {
        return owner;
    }

    int getNumEvents() {
        return numEvents;
    }

    Event getEvent(int i) {
        return events[i];
    }
}
