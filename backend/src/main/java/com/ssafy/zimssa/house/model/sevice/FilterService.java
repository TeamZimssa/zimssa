package com.ssafy.zimssa.house.model.sevice;

import com.ssafy.zimssa.house.model.dto.request.KeywordRequestDto;
import com.ssafy.zimssa.house.model.dto.response.GuDongResponseDto;
import com.ssafy.zimssa.house.model.dto.response.KeywordResponseDto;

import java.util.List;

public interface FilterService {

    List<GuDongResponseDto> findAllGuDong();

    List<KeywordResponseDto> findAllAptByKeyword(KeywordRequestDto keywordDto);

}
