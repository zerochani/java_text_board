package com.pyc.java.board.boundedContext.board.service;
import com.pyc.java.board.boundedContext.board.dto.Board;
import com.pyc.java.board.boundedContext.board.repository.BoardRepository;
import com.pyc.java.board.container.Container;

public class BoardService {
    private BoardRepository boardRepository;

    public BoardService(){
        boardRepository = Container.boardRepository;
    }

    public Board findByBoardId(int boardId){
        return boardRepository.findByBoardId(boardId);
    }
}
