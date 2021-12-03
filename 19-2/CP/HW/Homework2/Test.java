import bank.Bank;
import toth.Toth;
import toth.TothAccount;

public class Test {
    public static void main(String[] args) {

        Bank bank = new Bank();
        int janeAccountId = bank.createAccount("Jane", "1234asdf"),
            evaAccountId = bank.createAccount("Eva", "5678ghkj", 1000);

        bank.deposit(janeAccountId, "abcdefg", 100);
        bank.deposit(janeAccountId, "1234asdf", 500);

        bank.withdraw(janeAccountId, "abcdefg", 100);
        bank.withdraw(janeAccountId, "1234asdf", 1000);
        bank.withdraw(janeAccountId, "1234asdf", 300);

        bank.transfer(evaAccountId, "abcdefg", 3, 300);
        bank.transfer(evaAccountId, "5678ghkj", 3, 300);
        bank.transfer(evaAccountId, "5678ghkj", janeAccountId, 300);

        bank.printEvents(janeAccountId, "1234asdf", 3);
        bank.printEvents(evaAccountId, "5678ghkj", 3);
        bank.printTransactions(janeAccountId, "1234asdf", 3);

        // Uncomment this part when you complete bank package, and implement TothAccount.
        TothAccount wrongIdTothAcount = Toth.createTothAccount(100, "1234asdf", bank),
            wrongPwdTothAccount = Toth.createTothAccount(janeAccountId, "abcdefg", bank),
            janeTothAccount = Toth.createTothAccount(janeAccountId, "1234asdf", bank);
        janeTothAccount.deposit(200);
        janeTothAccount.withdraw(100);
        janeTothAccount.transfer(evaAccountId, 100);
    }
}
