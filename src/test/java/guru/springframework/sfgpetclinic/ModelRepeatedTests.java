package guru.springframework.sfgpetclinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("Models")
public interface ModelRepeatedTests {
    @BeforeEach
    default void beforeEachModel(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Model repeated test, display name: " + testInfo.getDisplayName() + " | Iteration: " + repetitionInfo.getCurrentRepetition());
    }
}
