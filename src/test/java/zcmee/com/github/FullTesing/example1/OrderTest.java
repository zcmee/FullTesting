package zcmee.com.github.FullTesing.example1;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    @BeforeEach
    void message() {
        System.out.println("come into @BeforeEach ");
    }

    @Test
    void testArrayRequest() {
        int[] inst1 = {1, 2, 3};
        int[] inst2 = {1, 2, 3};

        assertArrayEquals(inst1, inst2);
    }


    @Test
    void testArrayRequestTestForExtensions() {
        int[] inst1 = {31, 122, 33};
        int[] inst2 = {31, 122, 33};

        assertArrayEquals(inst1, inst2);
    }

}
