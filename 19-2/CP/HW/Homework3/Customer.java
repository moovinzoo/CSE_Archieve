import packet.*;

import java.util.LinkedList;

interface CustomerInterface {
    String getId();
    int getMoney(); void setMoney(int money);
    Network getNetwork(); void setNetwork(Network network);

    void enqueueResponse(Packet packet);
    void processNextResponse();
    boolean hasNextResponse();

    void requestDeposit(Bank bank, int money);
    void requestWithdraw(Bank bank, int money);
    void requestBalanceCheck(Bank bank);

}

public class Customer implements CustomerInterface {
    private String id;
    private int money;
    private LinkedList<Packet> responseQueue;
    private Network network;

    public Customer(String id, int money) {
        this.id = id;
        this.money = money;
        responseQueue = new LinkedList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public Network getNetwork() {
        return network;
    }

    @Override
    public void setNetwork(Network network) {
        this.network = network;
    }

    @Override
    public void enqueueResponse(Packet packet) {
        responseQueue.add(packet);
    }

    @Override
    public void processNextResponse() {
        Packet response = responseQueue.poll();
        if (response instanceof DepositResponse) {
            int amount = ((DepositResponse) response).getAmount();
            System.out.println("Response: Successfully deposit " + amount + " to " + response.getBankId());
        } else if (response instanceof WithdrawResponse) {
            int amount = ((WithdrawResponse) response).getAmount();
            money += amount;
            System.out.println("Response: Successfully withdrew " + amount + " to " + response.getBankId());
        } else if (response instanceof BalanceCheckResponse) {
            int money = ((BalanceCheckResponse) response).getAmount();
            System.out.println("Response: " + getId() + " now has " + money + " in " + response.getBankId());
        }
    }

    @Override
    public boolean hasNextResponse() {
        return !responseQueue.isEmpty();
    }

    @Override
    public void requestDeposit(Bank bank, int amount) {
        this.money -= amount;
        network.sendRequest(new DepositRequest(getId(), bank.getId(), amount));
        System.out.println(getId() + " made deposit request to " + bank.getId() + " with amount " + amount);
    }

    @Override
    public void requestWithdraw(Bank bank, int amount) {
        network.sendRequest(new WithdrawRequest(getId(), bank.getId(), amount));
        System.out.println(getId() + " made withdrawal request to " + bank.getId() + " with amount " + amount);
    }

    @Override
    public void requestBalanceCheck(Bank bank) {
        network.sendRequest(new BalanceCheckRequest(getId(), bank.getId()));
        System.out.println(getId() + " made balance check request to " + bank.getId());
    }
}
