package dk.cphbusiness.banking;

public class BankStub implements Bank {
    private Account account;

    void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public Account getAccount(String number) {
        return account;
    }

    @Override
    public void registerAccount(Account account) {

    }

    @Override
    public Customer getCustomer(String number) {
        return null;
    }

    @Override
    public void registerCustomer(Customer customer) {

    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException();
    }
}
