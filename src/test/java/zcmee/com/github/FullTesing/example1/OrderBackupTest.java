package zcmee.com.github.FullTesing.example1;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

class OrderBackupTest {
    private static OrderBackup orderBackup;

    @BeforeAll
    //Metody BeforeAll and AfterAll musza byc statyczne
    //W JUnit nazywało się to BeforeClass
    static void setUp() throws FileNotFoundException {
        orderBackup = new OrderBackup();
        orderBackup.createFile();
    }

    @AfterAll
    static void tearDown() throws IOException {
        orderBackup.closeFile();
    }

    @Test
    void backupOrderWithOneMeal() throws IOException {
        //given
        Order order = new Order();
        Meal meal = new Meal(13, "Golonka");
        order.addMealToOrder(meal);
        //when
        orderBackup.backupOrder(order);
        //then
        System.out.println("Order " + order.toString() + " backed up");
    }


}
