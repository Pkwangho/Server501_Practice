package com.busanit501.helloworld.food2.dao;


import com.busanit501.helloworld.food2.vo.Food2VO;
import com.busanit501.helloworld.jdbcex.dao.ConnectionUtil;
import lombok.Cleanup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Food2DAO {

    public void insert(Food2VO food2VO) throws SQLException {
        String sql = "insert into tbl_food(name,price,dueDate,finished)" + "values(?,?,?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, food2VO.getName());
        preparedStatement.setDouble(2, food2VO.getPrice());
        preparedStatement.setDate(3, Date.valueOf(food2VO.getDueDate()));
        preparedStatement.setBoolean(4, food2VO.isFinished());
        preparedStatement.executeUpdate();
    }

    //2
    // select , DB에서 전체 조회.
    public List<Food2VO> selectAll() throws SQLException {
        String sql = "select * from tbl_food";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        // 넘어온 데이터를 임시로 보관할 리스트 인스턴스 만들고,
        // 반복문 통해서, 넘어온 각행을 리스트에 요소로 하나씩 담기.
        List<Food2VO> list = new ArrayList<>();
        while (resultSet.next()) {
            Food2VO food2VO = Food2VO.builder()
                    .tno(resultSet.getLong("tno"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getDouble("price"))
                    .dueDate(resultSet.getDate("dueDate").toLocalDate())
                    .finished(resultSet.getBoolean("finished"))
                    .build();
            list.add(food2VO);
        }
        return list;
    }

    public Food2VO selectOne(Long tno) throws SQLException {
        String sql = "select * from tbl_food where tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        // 하나만 받아온 상태,
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        // 임시 TotoVO , 인스턴스 만들어서, 한행의 각 컬럼 4개를 담기.
        // 0행에서 -> 1행으로 조회를 해야하는데, 요게 누락됨.
        resultSet.next();
        Food2VO food2VO = Food2VO.builder()
                .tno(resultSet.getLong("tno"))
                .name(resultSet.getString("name"))
                .price(resultSet.getDouble("price"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .finished(resultSet.getBoolean("finished"))
                .build();
        return food2VO;
    }

    // 수정.
    // update,
    //  화면에서 낱개로 넘어온 데이터는 DTO 담아서 전달,
    // 서비스 계층에서, DTO -> VO(Value Object) 데이터베이스 직접적인 연관있음.
    // 예시, VO 클래스는 , 테이블과 컬럼과 동일.
    // 예시2) ,DTO 화면( 출력에서 전달하고 싶은 데이터만 골라서 사용할수 있음. )
    // 화면에서 받아옴, 테스트 , 더미 데이터 확인.
    public void updateOne(Food2VO food2VO) throws SQLException {
        String sql = " update tbl_food set name=?,price=?, dueDate=?, finished=?" +
                " where tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 화면에서 넘겨받은 변경할 데이터를 DTO -> VO 변환 후에(서비스에서 할 예정.)
        // VO 에서 꺼내서, 디비로 데이터 전달하는 과정.
        preparedStatement.setString(1, food2VO.getName());
        preparedStatement.setDouble(2, food2VO.getPrice());
        preparedStatement.setDate(3, Date.valueOf(food2VO.getDueDate()));
        preparedStatement.setBoolean(4, food2VO.isFinished());
        preparedStatement.setLong(5, food2VO.getTno());
        preparedStatement.executeUpdate();

    }

    //삭제,
    // delete,
    public void deleteFood(Long tno) throws SQLException {
        String sql = "delete from tbl_food where tno =?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, tno);
        preparedStatement.executeUpdate();

    }

}
