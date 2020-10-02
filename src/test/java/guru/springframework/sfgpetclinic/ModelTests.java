package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("Models")
public interface ModelTests {

    @BeforeEach
    default void beforeEachModel(TestInfo testInfo) {
        System.out.println("Model test, display name: " + testInfo.getDisplayName());
    }
}
