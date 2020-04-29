package dk.cphbusiness.banking.implementations;

import dk.cphbusiness.banking.interfaces.Movement;

import java.time.LocalDateTime;

public class RealMovement implements Movement {

    private LocalDateTime time;
    private long amount;
    private int targetId;
    private int sourceId;

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public RealMovement(long amount) {
        this.time = LocalDateTime.now();
        this.amount = amount;
    }
    
    public RealMovement(int amount, int targetId, int sourceId){
        this.amount = amount;
        this.targetId = targetId;
        this.sourceId = sourceId;
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
