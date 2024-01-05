package kr.seula.HelloSpring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    // 디비가 자동으로 만들어주는걸 Identity 전략이라고 한다
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
