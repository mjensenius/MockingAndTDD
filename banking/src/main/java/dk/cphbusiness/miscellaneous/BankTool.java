package dk.cphbusiness.miscellaneous;


public class BankTool {
  /*public void readFromFile(Bank bank, String filename) throws IOException {
    List<String> lines = Files.readAllLines(Path.of(filename));
    for (String line : lines) {
      if (line.startsWith("A:")) {
        String[] parts = line.split(":");
        if (parts.length != 3) throw new RuntimeException();
        Account account = new Account(bank, bank.getCustomer(parts[2]), parts[1]);
        bank.registerAccount(account);
        }
      else if (line.startsWith("C:")){
        String[] customerParts = line.split(":");
        if (customerParts.length != 3) throw new RuntimeException();
        Customer customer = new BaseCustomer(customerParts[0], customerParts[1]);
        bank.registerCustomer(customer);
        }
      else if (line.startsWith("M:")) {
        String[] movementParts = line.split(":");
        if (movementParts.length != 5) throw new RuntimeException();
        String sourceNumber = movementParts[0];
        String targetNumber = movementParts[1];
        long amount = Integer.parseInt(movementParts[2]);
        long timestamp = Integer.parseInt(movementParts[3]);
        Movement movement = new Movement(bank.getAccount(sourceNumber), bank.getAccount(targetNumber), amount, timestamp);
        }
      else throw new RuntimeException();
      }


    }
*/
  }
