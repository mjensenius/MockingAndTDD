package dk.cphbusiness.miscellaneous;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Raffle {
  private Set<Integer> tickets;

  public int getCount() { return tickets.size(); }

  public Raffle(TicketFactory factory) {
    this.tickets = factory.createTickets();
    }

  }

