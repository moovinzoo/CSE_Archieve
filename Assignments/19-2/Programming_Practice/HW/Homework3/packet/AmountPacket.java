package packet;

abstract public class AmountPacket extends Packet {
    private int amount;
    public AmountPacket(String customerId, String bankId, int amount) {
        super(customerId, bankId);
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }
}
