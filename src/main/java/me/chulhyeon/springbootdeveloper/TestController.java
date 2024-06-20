package me.chulhyeon.springbootdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {
    final TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMembers() {
        return testService.getAllMembers();
    }
}
