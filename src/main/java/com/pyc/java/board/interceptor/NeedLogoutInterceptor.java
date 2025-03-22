package com.pyc.java.board.interceptor;

import com.pyc.java.board.base.Rq;

public class NeedLogoutInterceptor implements Interceptor{
    @Override
    public boolean run(Rq rq) {
        if(rq.isLogout()) return true;

        return switch(rq.getUrlPath()){
            case "/user/member/join",
                 "/user/member/login",
                 "/user/member/findByUsername",
                 "/user/member/findByPassword"->{
                System.out.println("로그아웃 후 이용해주세요.");
                yield false;
            }
            default->true;
        };
    }
}
