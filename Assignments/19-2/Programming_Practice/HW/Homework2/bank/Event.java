package bank;

abstract class Event {
    final int amount, balance;

    Event(int amount, int balance) {
        /* Complete here */
    }
}

class WithdrawEvent extends Event {
    /* Complete here */
}

class DepositEvent extends Event {
    /* Complete here */
}

class SendEvent extends Event {
    private String targetOwner;
    /* Complete here */
}

class ReceiveEvent extends Event {
    private String sourceOwner;
    /* Complete here */
}