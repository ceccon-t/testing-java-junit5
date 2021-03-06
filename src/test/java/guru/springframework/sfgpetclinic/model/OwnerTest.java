package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

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

    @ParameterizedTest(name="{displayName} - {{index}} {arguments}")
    @DisplayName("Value Source Test ")
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSourcce(String val) {
        System.out.println(val);
    }

    @ParameterizedTest(name="{displayName} - {{index}} {arguments}")
    @DisplayName("Enum Source Test ")
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @ParameterizedTest(name="{displayName} - {{index}} {arguments}")
    @DisplayName("CSV Input Test")
    @CsvSource({
        "FL, 1, 1",
        "OH, 2, 2",
        "MI, 3, 1"
    })
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    @ParameterizedTest(name="{displayName} - {{index}} {arguments}")
    @DisplayName("CSV From File Test")
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    @ParameterizedTest(name="{displayName} - {{index}} {arguments}")
    @DisplayName("Method Provider Test")
    @MethodSource("getArgs")
    void fromMethodTest(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(Arguments.of("CH", 99, 98),
                Arguments.of("DE", 97, 96));
    }

    @ParameterizedTest(name="{displayName} - {{index}} {arguments}")
    @DisplayName("Custom Provider Test")
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int val1, int val2){
        System.out.println(stateName + " = " + val1 + ":" + val2);
    }

}