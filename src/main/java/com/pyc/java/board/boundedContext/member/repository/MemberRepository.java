package com.pyc.java.board.boundedContext.member.repository;

import com.pyc.java.board.boundedContext.member.dto.Member;
import com.pyc.java.board.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private List<Member> members;
    private int lastId;

    public MemberRepository(){
        members = new ArrayList<>();
        lastId = 0;
        makeMemberTestData();
    }

    private void makeMemberTestData() {
        join("user1", "1234", "박영찬");
        join("user2", "12345", "박영훈");
        join("user3","1111", "박아리");
    }


    public void join(String username, String password, String name) {
        int id = ++lastId;

        String regDate = Util.getNowDateStr();
        String updateDate = Util.getNowDateStr();

        Member member = new Member(id,regDate,updateDate, username, password, name);

        members.add(member);
    }

    public Member findByUsername(String username) {
        return members.stream()
                .filter(member-> member.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public Member findById(int id){
        return members.stream()
                .filter(member-> member.getId()==id)
                .findFirst()
                .orElse(null);
    }
}
