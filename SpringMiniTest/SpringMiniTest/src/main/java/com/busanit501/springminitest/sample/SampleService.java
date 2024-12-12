package com.busanit501.springminitest.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service // 일반 객체 타입을 시스템 등록,
@ToString
@RequiredArgsConstructor
public class SampleService {
    @Autowired

    @Qualifier("event")
    private final SampleDAO sampleDAO;

}
