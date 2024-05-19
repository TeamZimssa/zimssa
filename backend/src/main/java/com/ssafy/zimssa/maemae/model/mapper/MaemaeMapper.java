package com.ssafy.zimssa.maemae.model.mapper;

import com.ssafy.zimssa.maemae.model.dto.Maemae;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaemaeMapper {

    List<Maemae> findAllByGu(String sggCd);

    List<Maemae> findAllByDong(String sggCd, String bjdongCd);
}
