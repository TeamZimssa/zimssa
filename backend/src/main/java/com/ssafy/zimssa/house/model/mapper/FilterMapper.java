package com.ssafy.zimssa.house.model.mapper;

import com.ssafy.zimssa.house.model.dto.GuDong;
import com.ssafy.zimssa.house.model.dto.request.KeywordRequestDto;
import com.ssafy.zimssa.house.model.dto.response.KeywordResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FilterMapper {

    List<GuDong> findAllGuDong();

    List<KeywordResponseDto> findAllAptByKeyword(KeywordRequestDto keywordDto);
}
