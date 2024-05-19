package com.ssafy.zimssa.house.model.sevice;

import com.ssafy.zimssa.house.model.dto.GuDong;
import com.ssafy.zimssa.house.model.dto.request.KeywordRequestDto;
import com.ssafy.zimssa.house.model.dto.response.GuDongResponseDto;
import com.ssafy.zimssa.house.model.dto.response.KeywordResponseDto;
import com.ssafy.zimssa.house.model.mapper.FilterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final FilterMapper filterMapper;

    @Override
    public List<GuDongResponseDto> findAllGuDong() {
        List<GuDong> allGuDong = filterMapper.findAllGuDong();

        return allGuDong.stream()
                .map(GuDongResponseDto::fromGuDong)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeywordResponseDto> findAllAptByKeyword(KeywordRequestDto keywordDto) {

        return filterMapper.findAllAptByKeyword(keywordDto);
    }

}
