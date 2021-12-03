package bank;

public class Bank {
    private int numAccounts = 0, lastId = -1;
    private BankAccount[] accounts = new BankAccount[100];

    public int createAccount(String owner, String password) {
        /* Complete here */
    }

    public int createAccount(String owner, String password, int balance) {
        /* Complete here */
    }

    public void deposit(int accountId, String password, int amount) {
        /* Complete here */
    }

    public void withdraw(int accountId, String password, int amount) {
        /* Complete here */
    }

    public void transfer(int accountId, String password, int targetAccountId, int amount) {
        /* Complete here */
    }

    public void printEvents(int accountId, String password, int maxEvents) {
        /* Complete here */
    }

    public void printTransactions(int accountId,
                                  String password, int maxEvents) {
        /* Complete here */
    }

    public boolean authenticate(int accountId, String password) {
        /* Complete here */
    }
}
