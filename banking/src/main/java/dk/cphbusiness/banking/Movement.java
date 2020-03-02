package dk.cphbusiness.banking;

public class Movement {
    private Account source;
    private Account target;
    private long amount;
    private long timestamp;


    public Movement(Account source, Account target, long amount, long timestamp) {
        this.amount = amount;
        this.source = source;
        this.target = target;
        this.timestamp = timestamp;
    }

    public long getAmount() {
        return amount;
    }

    public Account getSource() {
        return source;
    }

    public Account getTarget() {
        return target;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
