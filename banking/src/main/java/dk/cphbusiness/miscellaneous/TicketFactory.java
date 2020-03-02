package dk.cphbusiness.miscellaneous;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicketFactory {
  private final int count;

  public TicketFactory(int count) {
    this.count = count;
    }

  public Set<Integer> createTickets() {
    return IntStream.range(1, 10)
        .boxed()
        .collect(Collectors.toSet());
    }
  }
