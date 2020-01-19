package zcmee.com.github.FullTesing.example1.account;

public class Account {

    private boolean active;
    private Address address;

    public Account() {
        this.active = false;
    }

    public Account(Address address) {
        this.address = address;
        if(address != null) {
            this.activate();
        } else {
            this.active = false;
        }
    }

    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
