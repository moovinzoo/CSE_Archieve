import packet.*;

import java.util.HashMap;
import java.util.LinkedList;

interface BankInterface {
    String getId(); void setId(String id);
    Network getNetwork(); void setNetwork(Network network);

    void enqueueRequest(Packet request);
    boolean hasNextRequest();
    void processNextRequest();
}

public class Bank implements BankInterface {
    private String id;
    private LinkedList<Packet> requestQueue;
    private Network network;
    private HashMap<String, Integer> accounts;

    public Bank(String id) {
        this.id = id;
        requestQueue = new LinkedList<>();
        accounts = new HashMap<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        id = id;
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
    public void enqueueRequest(Packet request) {
        requestQueue.add(request);
    }

    @Override
    public boolean hasNextRequest() {
        return !requestQueue.isEmpty();
    }

    @Override
    public void processNextRequest() {
        Packet request = requestQueue.poll();
        if(request instanceof DepositRequest) {
            int balance = accounts.getOrDefault(request.getCustomerId(), 0);
            int amount = ((DepositRequest) request).getAmount();
            accounts.put(request.getCustomerId(), balance + amount);
            network.sendResponse(new DepositResponse(request.getCustomerId(), getId(), amount));
        } else if (request instanceof WithdrawRequest) {
            int balance = accounts.getOrDefault(request.getCustomerId(), 0);
            int amount = ((WithdrawRequest) request).getAmount();
            accounts.put(request.getCustomerId(), balance - amount);
            network.sendResponse(new WithdrawResponse(request.getCustomerId(), getId(), amount));
        } else if (request instanceof BalanceCheckRequest) {
            int balance = accounts.getOrDefault(request.getCustomerId(), 0);
            network.sendResponse(new BalanceCheckResponse(request.getCustomerId(), getId(), balance));
        }
    }
}
