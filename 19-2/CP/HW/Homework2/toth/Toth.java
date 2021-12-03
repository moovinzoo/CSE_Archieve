package toth;

import bank.Bank;

public class Toth {
    public static TothAccount createTothAccount(int accountId, String password, Bank bank) {
        if (bank.authenticate(accountId, password)) {
            return new TothAccount(accountId, password, bank);
        } else {
            System.out.println("TOTH ERROR: Wrong account id or password");
            return null;
        }
    }
}