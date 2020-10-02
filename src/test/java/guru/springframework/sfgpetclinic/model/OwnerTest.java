package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class OwnerTest implements ModelTests {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1l, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("1231231234");

        assertAll("Properties Test",
                () -> {
                    assertAll("Person Properties",
                            () -> assertEquals("Joe", owner.getFirstName(), "First name did not match"),
                            () -> assertEquals("Buck", owner.getLastName(), "Last name did not match"));
                },
                () -> {
                    assertAll("Owner Properties",
                            () -> assertEquals("Key West", owner.getCity(), "City did not match"),
                            () -> assertEquals("1231231234", owner.getTelephone(), "Telephone did not match"));
                });

        assertThat(owner.getCity(), is("Key West"));
    }

    @DisplayName("Value Source Test ")
    @ParameterizedTest(name="{displayName} - {{index}} {arguments}")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSourcce(String val) {
        System.out.println(val);
    }

}