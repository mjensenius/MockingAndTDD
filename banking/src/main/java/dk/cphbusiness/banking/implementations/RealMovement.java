package dk.cphbusiness.banking.implementations;

import dk.cphbusiness.banking.interfaces.Movement;

import java.time.LocalDateTime;

public class RealMovement implements Movement {

    private LocalDateTime time;
    private long amount;

    public RealMovement(long amount) {
        this.time = LocalDateTime.now();
        this.amount = amount;
    }

    @Override
    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public long getAmount() {
        return this.amount;
    }

}
