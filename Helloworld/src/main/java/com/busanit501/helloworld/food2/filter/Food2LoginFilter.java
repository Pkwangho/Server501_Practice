package com.busanit501.helloworld.food2.filter;

import com.busanit501.helloworld.food2.dto.Food2MemberDTO;
import com.busanit501.helloworld.food2.service.Food2MemberService;
import com.busanit501.helloworld.jdbcex.dto.MemberDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

//톰캣 서버에서,
// 한글로 입력된 내용을, UTF8로 변환해서 보내기.
// 필터, 서버에 작업을 실행하기전에, 먼저 검사한다.
// 유효성 체크.
@WebFilter(urlPatterns = {"/food/*"})
@Log4j2
public class Food2LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter ,/food2/* 하위로 들어오는 모든 url 에 대해서 로그인 체크함");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 세션 정보를 호출 및 가져오기.
        HttpSession session = request.getSession();

        // 만약에, 서버에서 최초로 접근했다면,
        // 서버에서, JSESSIONID 발급을 해준다.
        if(session.isNew()) {
            log.info("최초로 서버에 요청을함.");
            // 로그인 컨트롤러가 아직 없음, 곧 만들 예정.
            response.sendRedirect("/login2");
            return;
        }
        // 2번째 이후의 방문, 하지만, 세션이라는 저장공간,
        // 여기에 키 : loginInfo , 값: 로그인한 유저의 아이디를 기록.
        if(session.getAttribute("loginInfo2") != null) {
            log.info("2번째 이후로 서버에 요청을했고, 하지만, 로그인 정보는 없는 경우.");
            // 로그인 컨트롤러가 아직 없음, 곧 만들 예정.
//            response.sendRedirect("/login");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 자동로그인, 구조
        // Page 범위, -> HttpServletRequest -> HttpSestion -> Application
        // HttpServletRequest 에서, 모든 쿠키를 검색해서, rememberMe  이름 찾기
        // 만약, 해당 쿠키 가 있고, 값도 있다면,
        // 디비에 조회를 해서, 쿠키의 값, 디비의 값 일치성 확인?
        // loginInfo MemberDTO의 값을 설정.

        // 내일 로그인 필터에서 처리하는 부분 보기.
        //findCookie, 해당 유저의 uuid  있음.
        Cookie findCookie = findCookie(request.getCookies(), "rememberMe");
        // rememberMe 가 체크가 안되서, null 경우, 다시 로그인 직접하기.
        if(findCookie == null) {
            response.sendRedirect("/login2");
            return;
        }
        // 쿠키에 등록된 uuid 가져오기
        String getUuid = findCookie.getValue();

        try {
            // 쿠키에 등록된  uuid 이용해서, 디비에서 조회하기.
            Food2MemberDTO food2memberDTO = Food2MemberService.INSTANCE.getMemberWithUuidService(getUuid);
            log.info("food2memberDTO : ", food2memberDTO);

            if(food2memberDTO == null) {
                throw new Exception("쿠키값이 유효하지 않습니다.");
            }
            // 회원정보를 , 세션에 추가하기.
            session.setAttribute("loginInfo2", food2memberDTO);
            //계속 필터 동작을 진행하겠다.
            // 만약 필터가 더이상 없다면, 원래대로 서버에서 나타내는 화면으로 이동함.
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/login2");

        }


        //임시로, 최초도 아니고, 로그인 처리가 되었다면, 그러면,
        // 정상적으로 접근하는 페이지로 이동 시켜 줄게.
        if(session.getAttribute("loginInfo2") != null) {
            // 앞에서 임시로 테스트 할 때, mid+mpw 붙여서 확인.
//            String result  = (String) session.getAttribute("loginInfo");
            Food2MemberDTO food2memberDTO  = (Food2MemberDTO) session.getAttribute("loginInfo2");
            log.info("session.getAttribute(\"loginInfo2\") food2memberDTO : " + food2memberDTO);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Cookie findCookie(Cookie[] cookies, String name) {
        Cookie findCookie = null;
        // 쿠키가 있는 경우
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                // cookie.getName(): 전체 쿠키 목록 요소의 이름
                // name : 찾고자하는 쿠키 이름.
                if (cookie.getName().equals(name)) {
                    findCookie = cookie;
                    break;
                } //if
            } // for
        } // if
        return findCookie;
    } // method
}
