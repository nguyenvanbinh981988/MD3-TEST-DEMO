package Dao;

import Connection_MySQL.ConnectionMySQL;
import Model.ClassSt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClassStDao implements CRUD<ClassSt> {

    public ClassStDao() {}

    @Override
    public List<ClassSt> selectAll() {
        String spl = "SELECT * FROM CLASS;";
        List<ClassSt> classSts = new ArrayList<>();
        try (Connection connection =  ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(spl);) {
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("classId");
                String className = resultSet.getString("className");
                int number = resultSet.getInt("number");

                classSts.add(new ClassSt(id, className, number));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classSts;
    }

    //----------------------------SELECT A CLASS----------------------------------

    public ClassSt select(int id){
        String sql = "select * from CLASS where classId =?;";
        ClassSt classSt = null;

        try (Connection connection =  ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String classname = rs.getString("className");
                int number = rs.getInt("number");
                classSt = new ClassSt(id,classname,number);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classSt;
    }


    //----------------------------CREATE----------------------------------


    public boolean creat(ClassSt classSt) throws SQLException {
        String spl = "INSERT INTO CLASS (className, Number) VALUES (?,?);";
        try (Connection connection =  ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(spl);) {
            preparedStatement.setString(1, classSt.getClassName());
            preparedStatement.setInt(2, classSt.getNumber());

            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------------EDIT----------------------------------

    public boolean edit(ClassSt classSt) throws SQLException{
            String sql = "update CLASS set className = ?, Number =? where classid = ?;";
            boolean rowEdit;
            try (Connection connection =  ConnectionMySQL.getConnect();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, classSt.getClassName());
                preparedStatement.setInt(2, classSt.getNumber());
                preparedStatement.setInt(3, classSt.getClassId());

                rowEdit = preparedStatement.executeUpdate() > 0;
            }

            return rowEdit;
        }

    //----------------------------DELETE----------------------------------
    public boolean delete(int id) throws SQLException{
        String sql = "DELETE FROM CLASS WHERE classId = ?;";
        boolean rowDelete;

        try (Connection connection =  ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        }
        return rowDelete;
    }

//----------------------------FIND----------------------------------

    public ClassSt selectName(String className){
        String sql = "select * from CLASS where className =?;";
        ClassSt classSt = null;

        try (Connection connection =  ConnectionMySQL.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, className);
            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

        rs.next();
                int classId = rs.getInt("classId");
                int number = rs.getInt("number");
                classSt = new ClassSt(classId,className,number);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return classSt;
    }
}


