package packet;

public class DepositRequest extends AmountPacket {
    public DepositRequest(String customerId, String bankId, int amount) {
        super(customerId, bankId, amount);
    }
}
