package com.busanit501.helloworld.member.service;

import com.busanit501.helloworld.jdbcex.dao.TodoDAO;
import com.busanit501.helloworld.jdbcex.dto.TodoDTO;
import com.busanit501.helloworld.jdbcex.util.MapperUtil;
import com.busanit501.helloworld.jdbcex.vo.TodoVO;
import com.busanit501.helloworld.member.dao.MemberDAO;
import com.busanit501.helloworld.member.dto.MemberDTO;
import com.busanit501.helloworld.member.vo.MemberVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

// 설정1
@Log4j2
public enum MemberService {
    INSTANCE;
    // 2가지, 다른 클래스에 의존함.
    // 1) 모델 맵퍼 기능
    // 2) DAO 기능

    private MemberDAO memberDAO;
    private ModelMapper modelMapper ;

    // 생성자 이용해서, 초기화하기.
    MemberService() {
        memberDAO = new MemberDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    //crud , 기본 테스트,
    // 직접 적인 디비 비지니스 로직, DAO 다 있어서,
    // 여기서는 기능 명세서 , 기능 모음짐,
    // DAO 에 의존해서 이용하기.

    //1
    // register
    // 화면에서 등록된 내용이 -> DTO 박스에 담아서-> 서비스 계층에 전달.
    public void register(MemberDTO memberDTO) throws SQLException {
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
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        // 기존 로깅 기록 출력
//        System.out.println("todoVo : "+ todoVO);
        log.info("memberVo : " + memberVO);

        // DAO 외주 맡기기,
        memberDAO.insert(memberVO);
    } // register

    //2
    // 전체 조회
    public List<MemberDTO> listAll() throws SQLException {
        List<MemberVO> voList = memberDAO.selectAll();
        log.info("voList : " + voList);

        // Stream 병렬 처리 안하면, 이런식으로 작업을 함.
//        List<TodoDTO> dtoList2 = new ArrayList<>();
//        for (TodoVO todoVo : voList) {
//            TodoDTO todoDTO = new TodoDTO();
//            todoDTO.setTno(todoVo.getTno());
//            todoDTO.setTitle(todoVo.getTitle());
//            todoDTO.setDueDate(todoVo.getDueDate());
//            todoDTO.setFinished(todoVo.isFinished());
//            dtoList2.add(todoDTO);
//        }

        // 병렬 처리를 하면, 똑같은 기능인데, 코드가 간결해짐.
        // 화면에 전달 할 때, VO -> DTO 변환.
        List<MemberDTO> dtoList = voList.stream().map(vo -> modelMapper.map(vo, MemberDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }
    public MemberDTO get(Long tno) throws SQLException {
        log.info("tno : " + tno);
        ///  디비에서 하나 조회 결과 받았음.
        MemberVO memberVO = memberDAO.selectOne(tno);
        // VO -> DTO 변환 작업.
        MemberDTO memberDTO = modelMapper.map(memberVO,MemberDTO.class);
        return memberDTO;

    }

    //4 수정 기능
    public void update(MemberDTO memberDTO) throws SQLException {
        // 화면에서 넘겨 받은 데이터는 TodoDTO 타입 박스에 담겨서 오고,
        // DAO 계층에서 박스의 타입 TodoVO 사용하니, 변환 작업 필요함.
        // 항상 데이터가 전달 유무 확인.
        log.info("memberDTO : " + memberDTO);
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        memberDAO.updateOne(memberVO);

    }

    //5 삭제 기능.
    public void delete(Long tno) throws SQLException {
        memberDAO.deleteMember(tno);
    }

    public void updateUuid(String name, String uuid) throws SQLException {
        memberDAO.updateUuid(name,uuid);
    }

}






