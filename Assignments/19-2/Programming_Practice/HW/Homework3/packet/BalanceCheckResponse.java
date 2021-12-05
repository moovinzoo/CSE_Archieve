package packet;

public class BalanceCheckResponse extends AmountPacket {
    public BalanceCheckResponse(String customerId, String bankId, int amount) {
        super(customerId, bankId, amount);
    }
}
