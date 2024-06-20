package me.chulhyeon.springbootdeveloper;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // 테스트용 애플리케이션 컨텍스트 생성
// SpringBootApplication 애노테이션을 찾아서 빈으로 등록된 객체들을 찾아 테스트용 컨텍스트를 만듦
@AutoConfigureMockMvc // MockMVC 생성
// 애플리케이션을 서버에 배포하지 않고도 테스트용 MVC환경을 만들어 요청 민 정송, 응답 기능을 제공하는 유틸 클래스. 컨트롤러 클래스를 테스트 할 때 사용
// 보통 setMockMVC를 사용해 초기화 해줌.
class TestControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach // 테스트 메서드 실행 전 호출 되는 메서드
    public void setMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("getAllMembers")
    @Test
    void getAllMembers() throws Exception {
        // given
        final String url = "/test";
        Member savedMember = memberRepository.save(new Member(1L, "홍길동"));

        // when
        //perform 메서드는 요청을 전송하는 메서드이다. 입력받은 인자로 요청을 보냄.
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));
        //accpet 메서드는 perform 메서드 (요청)을 통해 나온 응답을 어떤 타입으로 받을 지 결정하는 메서드 이다.


        // then
        // andExcpet 메서드는 응답을 검증하는 메서드.
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));

    }
    @AfterEach // 테스트 메서드 종료 후 호출되는 메서드
    public void cleanUp() {
        memberRepository.deleteAll();
    }
}