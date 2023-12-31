package kr.seula.HelloSpring.repository;

import kr.seula.HelloSpring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // Memory MAP
    private static Map<Long, Member> store = new HashMap<>();
    // Memory ID
    private static long sequence = 0L;

    // Map에 저장하기
    @Override
    // name 속성이 설정된 Member 객체를 받음
    public Member save(Member member) {
        // member 객체에 id 할당
        member.setId(++sequence);
        // Map에 객체를 저장하여 관리
        store.put(member.getId(), member);
        // id 할당된 객체 반환
        return member;
    }

    // Map에서 id로 찾기
    @Override
    public Optional<Member> findById(Long id) {
        // Optional 객체를 반환
        return Optional.ofNullable(store.get(id));
    }

    // Map에서 name으로 찾기
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 모두 꺼내기
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // Map 비우기
    public void clearStore() {
        store.clear();
    }
}
