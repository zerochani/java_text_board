package com.pyc.java.board.interceptor;

import com.pyc.java.board.base.Rq;

public class NeedLoginInterceptor implements Interceptor{

    @Override
    public boolean run(Rq rq) {
        if(rq.isLogined()) return true;

        return switch(rq.getUrlPath()){
            case "/user/article/write",
                 "/user/article/modify",
                 "/user/article/delete",
                 "/user/member/logout",
                 "/user/member/mypage"->{
                System.out.println("로그인 후 이용해주세요.");
                yield false;
            }
            default -> true;
        };
    }
}
