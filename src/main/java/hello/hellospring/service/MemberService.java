package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     * */
    public Long join(Member member) {
        // 종복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(mem -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        validDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(mem -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
