import java.util.ArrayList;

public class HW3 {
    public static void main(String args[]) {
        Network network = new Network();
        Customer minsu = new Customer("Minsu", 10000);
        Bank hanabank = new Bank("Hanabank");
        Bank kakaobank = new Bank("Kakaobank");
        network.addCustomer(minsu);
        network.addBank(hanabank);
        network.addBank(kakaobank);

        minsu.requestDeposit(hanabank, 3000);
        minsu.requestDeposit(kakaobank, 5000);
        minsu.requestBalanceCheck(hanabank);
        minsu.requestBalanceCheck(kakaobank);

        while(hanabank.hasNextRequest()) hanabank.processNextRequest();
        while(kakaobank.hasNextRequest()) kakaobank.processNextRequest();
        while(minsu.hasNextResponse()) minsu.processNextResponse();

        minsu.requestWithdraw(hanabank, 2000);
        minsu.requestWithdraw(kakaobank, 3000);
        minsu.requestBalanceCheck(hanabank);
        minsu.requestBalanceCheck(kakaobank);

        while(hanabank.hasNextRequest()) hanabank.processNextRequest();
        while(kakaobank.hasNextRequest()) kakaobank.processNextRequest();
        while(minsu.hasNextResponse()) minsu.processNextResponse();

        System.out.println("Minsu's cash: " + minsu.getMoney());
    }

}
