package dk.cphbusiness.banking;

import dk.cphbusiness.banking.implementations.RealMovement;
import dk.cphbusiness.banking.interfaces.Movement;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Test;
import static org.junit.Assert.*;

public class MovementMockingTest {

    @Test
    public void testGetTime() {
        Movement instance = new RealMovement(1000);
        System.out.println("getTime");
        //Creating static time
        Instant instant = Instant.parse("2020-03-02T12:00:00.00Z");
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        Clock clock = Clock.fixed(instant, zoneId);
        LocalDateTime dateAndTime = LocalDateTime.now(clock);

        LocalDateTime expResult = dateAndTime;
        LocalDateTime result = instance.getTime();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAmount() {
        Movement instance = new RealMovement(1000);
        System.out.println("getAmount");
        long expResult = 1000;
        long result = instance.getAmount();
        assertEquals(expResult, result);
    }

}