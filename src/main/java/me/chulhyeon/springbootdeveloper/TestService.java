package me.chulhyeon.springbootdeveloper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
