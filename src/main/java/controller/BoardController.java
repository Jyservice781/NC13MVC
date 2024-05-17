package controller;

import model.BoardDTO;

import java.util.ArrayList;

public class BoardController {
    // ArrayList  유사 DB 역할 필드 생성.
    private ArrayList<BoardDTO> list;

    // 다음에 입력될 게시판 번호 자동 올라감.
    private int nextId;

    public BoardController() {
        list = new ArrayList<>();
        nextId = 1;
    }
    // insert 로 값 저장할 것 번호가 하나씩 차례로 올라감(자동).
    public void insert(BoardDTO boardDTO) {
        boardDTO.setId(nextId++);
        list.add(boardDTO);
    }
    //selectOne -> 파라미터 id 값으로 index 의 값을 가져와서 저장시킴.
    public BoardDTO selectOne(int id) {
        BoardDTO temp = new BoardDTO();
        temp.setId(id);

        return list.get(list.indexOf(temp));
    }
    // selectAll -> 모든 리스트의 값들을 리턴함.
    public ArrayList<BoardDTO> selectAll() {
        return list;
    }

    public void update(BoardDTO boardDTO) {
        list.set(list.indexOf(boardDTO), boardDTO);
    }

    public void delete(int id) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(id);
        list.remove(boardDTO);
    }

    public boolean validateInput(int input) {
        if (input == 0) {
            return true;
        }

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(input);

        return list.contains(boardDTO);
    }
}