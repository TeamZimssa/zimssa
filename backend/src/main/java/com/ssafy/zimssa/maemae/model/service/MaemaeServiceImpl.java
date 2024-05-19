package com.ssafy.zimssa.maemae.model.service;

import com.ssafy.zimssa.maemae.model.dto.Maemae;
import com.ssafy.zimssa.maemae.model.dto.response.MaemaeResponseDto;
import com.ssafy.zimssa.maemae.model.mapper.MaemaeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaemaeServiceImpl implements MaemaeService {

    private final MaemaeMapper maemaeMapper;

    @Override
    public List<MaemaeResponseDto> findAllByGu(String sggCd) {
        List<Maemae> maemaeList = maemaeMapper.findAllByGu(sggCd);

        return maemaeList.stream()
                .map(MaemaeResponseDto::fromMaemae)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaemaeResponseDto> findAllByDong(String sggCd, String bjdongCd) {
        List<Maemae> maemaeList = maemaeMapper.findAllByDong(sggCd, bjdongCd);

        return maemaeList.stream()
                .map(MaemaeResponseDto::fromMaemae)
                .collect(Collectors.toList());
    }
}
