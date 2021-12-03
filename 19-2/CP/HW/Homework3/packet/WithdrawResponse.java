package packet;

public class WithdrawResponse extends AmountPacket {
    public WithdrawResponse(String customerId, String bankId, int amount) {
        super(customerId, bankId, amount);
    }
}
