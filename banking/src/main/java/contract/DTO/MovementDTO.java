/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contract.DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Mikkel
 */
public class MovementDTO {

    private LocalDateTime time;
    private long amount;

    public MovementDTO(LocalDateTime time, long amount) {
        this.time = time;
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
