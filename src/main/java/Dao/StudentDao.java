package Dao;

import Connection_MySQL.ConnectionMySQL;
import Model.ClassSt;
import Model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class StudentDao implements CRUDSTUDENT<Student> {
    ClassStDao classStDao = new ClassStDao();

    @Override
    public List<Student> selectAll() {
        String spl = "SELECT * FROM STUDENT;";
        List<Student> students = new ArrayList<>();
        try (Connection connection = ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(spl);) {
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date birthDayDate = resultSet.getDate("birthDay");
                LocalDate birthDay = birthDayDate.toLocalDate();
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                ClassSt classSt = classStDao.select(resultSet.getInt("classId"));

                students.add(new Student(id, name, birthDay, address, phone, email, classSt));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    //----------------------------SELECT A Student----------------------------------


    public Student select(int id) {
        String sql = "select * from STUDENT where id =" + id;
        try (Connection connection = ConnectionMySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            int idS = resultSet.getInt("id");
            String name = resultSet.getString("name");
            LocalDate birthDay = LocalDate.parse(resultSet.getString("birthDay"));
            String phone = resultSet.getString("phone");
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            ClassSt classSt = classStDao.select(resultSet.getInt("classId"));

            return new Student(idS, name, birthDay, address, email, phone, classSt);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    //----------------------------CREATE----------------------------------


    public boolean creat(Student student) throws SQLException {
        String spl = "INSERT INTO STUDENT (name , birthDay,address,phone,email,classid) VALUES (?,?,?,?,?,?);";
        try (Connection connection = ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(spl);) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, String.valueOf(student.getBirthDay()));
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setInt(6, student.getClassSt().getClassId());

            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------EDIT----------------------------------

    public boolean edit(Student student) throws SQLException {
        String sql = "update Student set name = ?,birthDay = ?,address=?,phone=?,email=?,classid=? where Id = ?;";

        try (Connection connection = ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(7, student.getId());
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, String.valueOf(student.getBirthDay()));
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setInt(6, student.getClassSt().getClassId());

            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    //----------------------------DELETE----------------------------------
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM Student WHERE id = ? ";
        boolean rowDelete;

        try (Connection connection = ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        }
        return rowDelete;
    }

    //----------------------------FIND----------------------------------
    public List<Student> selectName(String name) {
        List<Student> findStudents = new ArrayList<>();
        String sql = "select * from Student where name like concat('%',?,'%')";
        try (Connection connection = ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int ids = resultSet.getInt("id");
                String name1 = resultSet.getString("name");
                LocalDate birthDay = LocalDate.parse(resultSet.getString("birthDay"));
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                ClassSt classSt = classStDao.select(resultSet.getInt("classId"));


                Student student = new Student(ids, name1, birthDay, phone, address, email, classSt);
                findStudents.add(student);
            }


            return findStudents;


        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;

    }

}


