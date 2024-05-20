package controller;

import model.ReplyDTO;

import java.util.ArrayList;

public class ReplyController {
    //ReplyDTO 한테 값 받아올 리스트
    private ArrayList<ReplyDTO> list;

    //index 번호값을 자동적으로 123 늘리기 때문
    private  int nextId;

    // 리스트 자체 댓글로 이루어 지는 리스트
    public ReplyController(){
        list = new ArrayList<>();
        nextId = 1;
    }
    // index 값을 뜻함
    public void insert(ReplyDTO replyDTO){
        replyDTO.setId(nextId++);
        list.add(replyDTO);
    }
    // 사용자가 댓글 입력
    // public ReplyDTO search(String content, int writerId){
    // 댓글들 확인
    public ArrayList<ReplyDTO> selectAll(){
        return list;
    }

    public ReplyDTO selectOne(int id){
        ReplyDTO temp = new ReplyDTO();
        temp.setId(id);
        if(list.contains(temp)){
            return list.get(list.indexOf(temp));
        }
        return null;
    }


    // 입력한 사람이 댓글 수정
    //update()
    public void update(ReplyDTO replyDTO){
        list.set(list.indexOf(replyDTO), replyDTO);
    }
    // 입력한 사람이 댓글 삭제
    //delete()
    public void delete(int id){
        ReplyDTO temp = new ReplyDTO();
        temp.setId(id);

        list.remove(temp);
    }
}
