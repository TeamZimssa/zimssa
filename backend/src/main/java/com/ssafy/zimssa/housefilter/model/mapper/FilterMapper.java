package com.ssafy.zimssa.housefilter.model.mapper;

import com.ssafy.zimssa.housefilter.model.dto.GuDong;
import com.ssafy.zimssa.housefilter.model.dto.request.KeywordRequestDto;
import com.ssafy.zimssa.housefilter.model.dto.response.KeywordResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilterMapper {

    List<GuDong> findAllGuDong();

    List<KeywordResponseDto> findAllAptByKeyword(KeywordRequestDto keywordDto);
}
