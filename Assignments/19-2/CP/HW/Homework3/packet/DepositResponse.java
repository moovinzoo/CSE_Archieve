package packet;

public class DepositResponse extends AmountPacket {
    public DepositResponse(String customerId, String bankId, int amount) {
        super(customerId, bankId, amount);
    }
}
