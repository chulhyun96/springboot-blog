import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    @DisplayName("1 + 2 = 3")
    @Test
    void junitTest() {
        // given
        int a = 1;
        int b = 2;
        // when
        int result = a + b;

        // then
        Assertions.assertThat(result).isEqualTo(3);
    }

}
