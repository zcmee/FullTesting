package zcmee.com.github.FullTesing.example1;

public class Account {

    private boolean active;

    public Account() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

}
