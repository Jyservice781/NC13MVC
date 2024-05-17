package model;

import lombok.Data;

@Data
public class BoardDTO {

    private int id;
    private String title;
    private String content;
    private int writerId;

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o instanceof BoardDTO){
            BoardDTO b = (BoardDTO) o;
            return id == b.id;
        }
        return false;
    }
}
