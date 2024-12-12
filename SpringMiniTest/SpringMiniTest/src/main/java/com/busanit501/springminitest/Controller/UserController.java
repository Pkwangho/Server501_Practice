package com.busanit501.springminitest.Controller;

import com.busanit501.springminitest.dto.PageRequestDTO;
import com.busanit501.springminitest.dto.PageResponseDTO;
import com.busanit501.springminitest.dto.UserDTO;
import com.busanit501.springminitest.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @RequestMapping("/list")
    public String list(@Valid PageRequestDTO pageRequestDTO,
                     BindingResult bindingResult,
                     RedirectAttributes redirectAttributes,
                     Model model) {
        log.info("userController list : 화면제공은 해당 메서드 명으로 제공함.");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/user/list";
        }
        PageResponseDTO<UserDTO> pageResponseDTO = userService.selectList(pageRequestDTO);
        log.info("UserController list 데이터 유무 확인 :" + pageResponseDTO);
        model.addAttribute("pageResponseDTO", pageResponseDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "/user/list";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {
        log.info("UserController register : 화면제공은 해당 메서드 명으로 제공함.");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid UserDTO userDTO, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("UserController register post 로직처리: ");
        log.info("UserController register post  userDTO : " + userDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/user/register";
        }
        userService.register(userDTO);
        return "redirect:/user/list";
    }


    @RequestMapping("/read")
    public String read(Long uno, @Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,
                       Model model) {
        log.info("UserController read :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("uno", uno);
            return "redirect:/user/read";
        }
        UserDTO userDTO = userService.getOne(uno);
        log.info("UserController read 데이터 유무 확인 :" + userDTO);
        log.info("UserController read 데이터 유무 확인 pageRequestDTO :" + pageRequestDTO);
        model.addAttribute("userDTO", userDTO);
        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size",pageRequestDTO.getSize());
        return "/user/read";
    }


    // 수정 1) 폼 2) 로직 처리
    @RequestMapping("/update")
    public String update(Long uno, @Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, Model model) {
        log.info("UserController update :");
        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함.");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("uno", uno);
            return "redirect:/user/update";
        }
        UserDTO userDTO = userService.getOne(uno);
        log.info("UserController update 데이터 유무 확인 :" + userDTO);
        model.addAttribute("userDTO", userDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "/user/update";
    }

    @PostMapping("/update")
    public String updateLogic(@Valid UserDTO userDTO, BindingResult bindingResult,
                              @Valid PageRequestDTO pageRequestDTO,
                              BindingResult pageBindingResult,
                              RedirectAttributes redirectAttributes) {
        log.info("userDTO확인 finished의 변환 여부 확인1. : " + userDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :UserDTO ");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("uno", userDTO.getUno());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/user/update"+pageRequestDTO.getLink();
        }

        if (pageBindingResult.hasErrors()) {
            log.info("has errors : 유효성 에러가 발생함. 검사 대상 :PageRequestDTO ");
            redirectAttributes.addFlashAttribute("errors2", pageBindingResult.getAllErrors());
            redirectAttributes.addAttribute("uno", userDTO.getUno());
            redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
            redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
            return "redirect:/user/update"+pageRequestDTO.getLink();
        }

        log.info("UserDTO확인 finished의 변환 여부 확인2. : " + userDTO);
        log.info("UserController update pageRequestDTO : "+ pageRequestDTO);

        userService.update(userDTO);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/user/list?"+pageRequestDTO.getLink();
    }


    // 삭제
    @PostMapping("/delete")
    public String delete(Long uno, PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes
    ) {
        userService.delete(uno);
        log.info("UserController delete : pageRequestDTO " + pageRequestDTO);

        return "redirect:/user/list?"+pageRequestDTO.getLink();
    }

}








