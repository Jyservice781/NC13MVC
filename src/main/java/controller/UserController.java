package controller;

import model.UserDTO;

import java.util.ArrayList;

public class UserController {
    // 유사 DB 역할을 할 ArrayList 필드
    private ArrayList<UserDTO> list;

    // 다음 입력될 회원의 번호를 저장할 int 필드
    private int nextId;

    //생성자를 통해서 list
    public UserController() {
        list = new ArrayList<>();
        nextId = 1;
    }

    public void insert(UserDTO userDTO) {
        userDTO.setId(nextId++);
        list.add(userDTO);
    }

    public boolean validateUsername(String username) {
        for (UserDTO u : list) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                // 대소문자 상관없이 로그인한다.
                return false;
            }
        }
        return true;
    }

    public UserDTO selectOne(int id) {
        UserDTO temp = new UserDTO();
        temp.setId(id);
        if (list.contains(temp)) {
            return list.get(list.indexOf(temp));
        }
        return null;
    }

    public void update(UserDTO userDTO) {
        list.set(list.indexOf(userDTO), userDTO);
    }

    public void delete(int id) {
        UserDTO temp = new UserDTO();
        temp.setId(id);

        list.remove(temp);
    }

    public UserDTO auth(String username, String password) {
        // list 를 확인하면서 찾아냄.
        for (UserDTO u : list) {
            if (username.equalsIgnoreCase(u.getUsername())) {
                if (password.equals(u.getPassword())) {
                    return u;
                }
            }
        }
        return null;
    }
}
