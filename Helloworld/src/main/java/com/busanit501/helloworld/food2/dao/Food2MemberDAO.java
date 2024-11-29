package com.busanit501.helloworld.food2.dao;

import com.busanit501.helloworld.food2.vo.Food2MemberVO;

import com.busanit501.helloworld.member.util.ConnectionUtil;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Food2MemberDAO {

    public Food2MemberVO getMemberWithMpw(String name, String password) throws SQLException {

        String query = "select * from tbl_member where name=? and password=?";
        Food2MemberVO food2memberVO = null;

        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, password);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        food2memberVO = Food2MemberVO.builder()
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .build();

        return food2memberVO;

    }

    public void updateUuid(String name, String uuid) throws SQLException {
        String query = "update tbl_member set uuid=? where name=?";
        @Cleanup Connection connection = com.busanit501.helloworld.jdbcex.dao.ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);
        preparedStatement.setString(2, name);
        preparedStatement.executeUpdate();
    }

    public Food2MemberVO getMemberWithUuid(String uuid) throws SQLException {
        String query = "select * from tbl_member where uuid=?";
        // 결과 데이터를 담아둘 임시 박스 MemberVO
        Food2MemberVO food2memberVO = null;
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, uuid);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        food2memberVO = Food2MemberVO.builder()
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .dueDate(resultSet.getDate("dueDate").toLocalDate())
                .uuid(resultSet.getString("uuid"))
                .build();
        return food2memberVO;
    }//
}
