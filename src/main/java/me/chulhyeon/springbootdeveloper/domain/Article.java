package me.chulhyeon.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키가 자동으로 1씩 증가.
    @Column(name = "id", updatable = false) //해당 필드는 업데이트 시 수정할 수 없음을 의미합니다.
    private Long id;

    @Column(name = "title", nullable = false) // 해당 필드는 null 값을 허용하지 않음을 의미합니다.
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article update(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }
}
