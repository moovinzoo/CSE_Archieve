package packet;

public class WithdrawRequest extends AmountPacket {
    public WithdrawRequest(String customerId, String bankId, int amount) {
        super(customerId, bankId, amount);
    }
}
