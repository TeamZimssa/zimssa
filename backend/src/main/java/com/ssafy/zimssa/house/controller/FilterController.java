package com.ssafy.zimssa.house.controller;

import com.ssafy.zimssa.house.model.dto.request.KeywordRequestDto;
import com.ssafy.zimssa.house.model.dto.response.GuDongResponseDto;
import com.ssafy.zimssa.house.model.dto.response.KeywordResponseDto;
import com.ssafy.zimssa.house.model.sevice.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/house")
public class FilterController {

    private final FilterService filterService;

    @GetMapping("/searchGuDong")
    public ResponseEntity<List<GuDongResponseDto>> searchGuDong() {
        List<GuDongResponseDto> response = filterService.findAllGuDong();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/keyword")
    public ResponseEntity<List<KeywordResponseDto>> searchByKeyword(@ModelAttribute KeywordRequestDto keywordDto) {
        System.out.println(keywordDto.getKeyword()+" "+keywordDto.getTradeType());
        List<KeywordResponseDto> response = filterService.findAllAptByKeyword(keywordDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
