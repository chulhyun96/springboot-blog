package me.chulhyeon.springbootdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    MemberRepository memberRepository;

    @GetMapping("/test")
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
