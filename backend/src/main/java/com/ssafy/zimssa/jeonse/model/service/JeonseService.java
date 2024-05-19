package com.ssafy.zimssa.jeonse.model.service;

import com.ssafy.zimssa.jeonse.model.dto.response.JeonseResponseDto;

import java.util.List;

public interface JeonseService {


    List<JeonseResponseDto> findAllByGu(String sggCd);

    List<JeonseResponseDto> findAllByDong(String sggCd, String bjdongCd);

}
