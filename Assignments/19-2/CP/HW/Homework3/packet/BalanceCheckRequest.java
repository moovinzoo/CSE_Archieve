package packet;

public class BalanceCheckRequest extends Packet {
    public BalanceCheckRequest(String customerId, String bankId) {
        super(customerId, bankId);
    }
}
