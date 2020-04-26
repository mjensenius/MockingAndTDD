package dk.cphbusiness.banking.interfaces;

import java.time.LocalDateTime;

public interface Movement {

    LocalDateTime getTime();

    long getAmount();
    
    int getTargetId();
     
    int getSourceId();

}