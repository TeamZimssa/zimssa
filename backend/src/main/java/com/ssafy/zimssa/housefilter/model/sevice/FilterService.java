package com.ssafy.zimssa.housefilter.model.sevice;

import com.ssafy.zimssa.housefilter.model.dto.request.KeywordRequestDto;
import com.ssafy.zimssa.housefilter.model.dto.response.GuDongResponseDto;
import com.ssafy.zimssa.housefilter.model.dto.response.KeywordResponseDto;

import java.util.List;

public interface FilterService {

    List<GuDongResponseDto> findAllGuDong();

    List<KeywordResponseDto> findAllAptByKeyword(KeywordRequestDto keywordDto);

}
