package com.busanit501.springminitest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

// <E> 제너릭으로, Element, 해당 타입을 유연하게 설정.
// PageResponseDTO 응답을 하는 건, 페이징 처리
// 페이징 처리 도메인(예시, Todo, Member, Reply, Product 등.)
@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;
    private int start;
    private int end;
    private boolean prev;
    private boolean next;


    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, int total,
                           PageRequestDTO pageRequestDTO) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = ((int) Math.ceil(page / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int)(Math.ceil(total/10.0));
        this.end = end > last ? last :end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;

    }


}