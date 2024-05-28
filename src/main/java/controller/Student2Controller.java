package controller;

import jdk.jshell.spi.SPIResolutionException;
import model.StudentDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//데이터베이스를 화리용한 StudentController
public class Student2Controller {
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/board";
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // 0.
    public Student2Controller() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. selectAll()
    public ArrayList<StudentDTO> selectAll() {
        // 쿼리를 보내기 위해서 String 처리를 해줌.
        ArrayList<StudentDTO> list = new ArrayList<>();
        String query = "SELECT * FROM student";

        try {
            //SQL 로 보내줌.
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //결과
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(resultSet.getInt("id"));
                studentDTO.setName(resultSet.getString("name"));
                studentDTO.setKorean(resultSet.getInt("korean"));
                studentDTO.setEnglish(resultSet.getInt("english"));
                studentDTO.setMath(resultSet.getInt("math"));

                list.add(studentDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. selectOne(id)
    public StudentDTO selectOne(int id) {
        // 만약 값이 유효하지 않으면
        StudentDTO studentDTO = null;

        String query = "SELECT * FROM student WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                studentDTO = new StudentDTO();
                studentDTO.setId(resultSet.getInt("id"));
                studentDTO.setName(resultSet.getString("name"));
                studentDTO.setKorean(resultSet.getInt("korean"));
                studentDTO.setEnglish(resultSet.getInt("english"));
                studentDTO.setMath(resultSet.getInt("math"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentDTO;
    }

    // 3. insert(StudentDTO studentDTO)
    public void insert(StudentDTO studentDTO) {
        String query = "INSERT INTO student(name, korean, english, math) VALUES(?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentDTO.getName());
            preparedStatement.setInt(2, studentDTO.getKorean());
            preparedStatement.setInt(3, studentDTO.getEnglish());
            preparedStatement.setInt(4, studentDTO.getMath());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4.  update(StudentDTO studentDTO)
    public void update(StudentDTO studentDTO) {
        String query = "UPDATE student SET name = ?, korean = ?, english = ?, math = ? WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentDTO.getName());
            preparedStatement.setInt(2, studentDTO.getKorean());
            preparedStatement.setInt(3, studentDTO.getEnglish());
            preparedStatement.setInt(4, studentDTO.getMath());
            preparedStatement.setInt(5, studentDTO.getId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 5. delete (int id)
    public void delete(int id){
        String query = "DELETE FROM student WHERE id = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
