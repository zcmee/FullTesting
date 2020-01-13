package zcmee.com.github.FullTesing.example1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @ParameterizedTest
    @CsvSource({"Fabryczna, 10", "Armi Krajowej, 29", "'Romka, Tomka, Atomka', 106"})
    void givenAddressesShouldNotBeEmptyAndHAveProperName(String streetName, int number) {
        assertThat(streetName, notNullValue());
        assertThat(streetName, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number, greaterThanOrEqualTo(10));
        assertThat(number, instanceOf(Number.class));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/addresses.csv")
    void methodAsAboveWithExcptionThatGetDataFromCsvFile(String streetName, int number) {
        assertThat(streetName, notNullValue());
        assertThat(streetName, containsString("a"));
        assertThat(number, notNullValue());
        assertThat(number, greaterThanOrEqualTo(10));
        assertThat(number, instanceOf(Number.class));
    }
}
