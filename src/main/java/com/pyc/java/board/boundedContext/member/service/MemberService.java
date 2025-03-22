package com.pyc.java.board.boundedContext.member.service;

import com.pyc.java.board.boundedContext.member.dto.Member;
import com.pyc.java.board.boundedContext.member.repository.MemberRepository;
import com.pyc.java.board.container.Container;

public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(){
        memberRepository = Container.memberRepository;
    }

    public void join(String username, String password, String name){
        memberRepository.join(username, password, name);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findById(int id){
        return memberRepository.findById(id);
    }
}
