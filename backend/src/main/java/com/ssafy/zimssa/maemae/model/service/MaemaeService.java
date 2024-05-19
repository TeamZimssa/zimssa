package com.ssafy.zimssa.maemae.model.service;

import com.ssafy.zimssa.maemae.model.dto.response.MaemaeResponseDto;

import java.util.List;

public interface MaemaeService {

    List<MaemaeResponseDto> findAllByGu(String sggCd);

    List<MaemaeResponseDto> findAllByDong(String sggCd, String bjdongCd);
}
