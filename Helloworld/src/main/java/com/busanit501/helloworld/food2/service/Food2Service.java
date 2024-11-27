package com.busanit501.helloworld.food2.service;

import com.busanit501.helloworld.food2.dao.Food2DAO;
import com.busanit501.helloworld.food2.dto.Food2DTO;
import com.busanit501.helloworld.food2.vo.Food2VO;

import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.util.MapperUtil;

import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

// 설정1
@Log4j2
public enum Food2Service {
    INSTANCE, Food2DTO;
    // 2가지, 다른 클래스에 의존함.
    // 1) 모델 맵퍼 기능
    // 2) DAO 기능

    private Food2DAO food2DAO;
    private ModelMapper modelMapper;

    Food2Service() {
        food2DAO = new Food2DAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }


    //crud , 기본 테스트,
    // 직접 적인 디비 비지니스 로직, DAO 다 있어서,
    // 여기서는 기능 명세서 , 기능 모음짐,
    // DAO 에 의존해서 이용하기.

    //1
    // register
    // 화면에서 등록된 내용이 -> DTO 박스에 담아서-> 서비스 계층에 전달.
    public void register(Food2DTO food2DTO) throws SQLException {
        // DAO 작업할 때, 디비에 직접적인 영향을 주는 객체,
        // VO, 실제 비지니스 로직에서만 사용함.
        // 서블릿 > DTO 전달 받고, -> DAO 한테 전달할 때, 다시, VO 변환해야함.
        // 변환 하는 도구,
        // 도구를 사용안하면,
//        TodoVO todoVO = new TodoVO();
//        todoVO.setTno(todoDTO.getTno());
//        todoVO.setTitle(todoDTO.getTitle());
//        todoVO.setDueDate(todoDTO.getDueDate());
//        todoVO.setFinished(todoDTO.isFinished());

        // 모델 맵퍼 이용시.
        Food2VO food2VO = modelMapper.map(food2DTO,Food2VO.class);
        log.info("food2VO : " + food2VO);


        food2DAO.insert(food2VO);
    }
    // 전체 조회
    public List<Food2DTO> listAll() throws SQLException {
        List<Food2VO> voList = food2DAO.selectAll();
        log.info("voList : " + voList);


        List<Food2DTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, Food2DTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    public Food2DTO get(Long tno) throws SQLException {
        log.info("tno : " + tno);
        ///  디비에서 하나 조회 결과 받았음.
        Food2VO food2VO = food2DAO.selectOne(tno);
        // VO -> DTO 변환 작업.
        Food2DTO food2DTO = modelMapper.map(food2VO,Food2DTO.class);
        return food2DTO;

    }

    //4 수정 기능
    public void update(Food2DTO food2DTO) throws SQLException {
        // 화면에서 넘겨 받은 데이터는 TodoDTO 타입 박스에 담겨서 오고,
        // DAO 계층에서 박스의 타입 TodoVO 사용하니, 변환 작업 필요함.
        // 항상 데이터가 전달 유무 확인.
        log.info("food2DTO : " + food2DTO);
        Food2VO food2VO = modelMapper.map(food2DTO,Food2VO.class);
        food2DAO.updateOne(food2VO);

    }

    //5 삭제 기능.
    public void delete(Long tno) throws SQLException {
        food2DAO.deleteFood(tno);
    }

}






