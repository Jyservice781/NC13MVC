package model;

import lombok.Data;

@Data
public class ReplyDTO {

    private int id;
    private String content;
    private int writerId;
    private int boardId;

    // ==
    @Override
    //  ArrayList 를 사용하기 위해서 equals의 재정의를 해줌
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o instanceof ReplyDTO) {
            ReplyDTO r = (ReplyDTO) o;
            return id == r.id;
        }
        return false;
    }
}
