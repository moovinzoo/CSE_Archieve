package packet;

abstract public class Packet {
    private String customerId;
    private String bankId;

    public Packet(String customerId, String bankId) {
        this.customerId = customerId;
        this.bankId = bankId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getBankId() {
        return bankId;
    }

}

