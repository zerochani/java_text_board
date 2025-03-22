package com.pyc.java.board.interceptor;

import com.pyc.java.board.base.Rq;

public interface Interceptor {
    boolean run(Rq rq);
}
