package com.ssafy.zimssa.maemae.controller;

import com.ssafy.zimssa.maemae.model.dto.response.MaemaeResponseDto;
import com.ssafy.zimssa.maemae.model.service.MaemaeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/maemae")
public class MaemaeController {

    private final MaemaeService maaemaeService;

    @GetMapping("/search-apt")
    public ResponseEntity<List<MaemaeResponseDto>> searchAllByGuDong(
            @RequestParam(name = "gunCode") String sggCd,
            @RequestParam(name = "dongCode", required = false) String bjdongCd) {
        List<MaemaeResponseDto> response = bjdongCd == null ? maaemaeService.findAllByGu(sggCd) : maaemaeService.findAllByDong(sggCd, bjdongCd);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
