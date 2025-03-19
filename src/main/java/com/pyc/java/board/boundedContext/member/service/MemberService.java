package com.pyc.java.board.boundedContext.member.service;

import com.pyc.java.board.boundedContext.member.dto.Member;
import com.pyc.java.board.boundedContext.member.repository.MemberRepository;
import com.pyc.java.board.container.Container;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(){
        memberRepository = Container.memberRepository;
        makeMemberTestData();
    }

    private void makeMemberTestData() {
        join("user1", "1234", "박영찬");
        join("user2", "12345", "박영훈");
        join("user3","1111", "박아리");
    }

    public void join(String username, String password, String name){
        memberRepository.join(username, password, name);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
