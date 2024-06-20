import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll // 전체 테스트를 시작하기 전에 1회 실행되므로 메서드는 static으로 선언
    // 예를 들어 DB 연결, 테스트 환경 초기화. 테스트 실행 주기에서 한번만 호출되어야 하기 때문에 static으로 선언
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
        // 테스트 케이스를 시작하기 전마다 실행
        // 예를 들어 테스트 메서드에서 사용하는 객체를 초기화 하거나 테스트에 필요한 값을 미리 넣을 때, 각 인스턴스에 대해 메서드를 호출해야 하므로 static이 아니여야 한다.
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }

    @Test
    void test3() {
        System.out.println("test3");
    }

    @AfterAll // 전체 테스트를 마치고 종료하기 전에 1회 실행하므로 메서드는 static으로 선언
    // DB 연결 종료, 공통적으로 사용하는 자원 해제시.
    static void afterAll() {
        System.out.println("@AfterAll");
    }

    @AfterEach
        // 테스트 케이스를 종료하기 전마다 실행
        // 예를 들어 테스트 이후에 특정 데이터를 삭제해야 하는 경우.
    void afterEach() {
        System.out.println("@AfterEach");
    }

}
