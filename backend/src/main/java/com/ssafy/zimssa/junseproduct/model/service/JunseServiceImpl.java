package com.ssafy.zimssa.junseproduct.model.service;

import com.ssafy.zimssa.junseproduct.model.dto.JunseInfoResponseDto;
import com.ssafy.zimssa.junseproduct.model.mapper.JunseMapper;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class JunseServiceImpl implements JunseService
{

    private JunseMapper junseMapper;

    public JunseServiceImpl(JunseMapper junseMapper) {
        this.junseMapper = junseMapper;
    }

    @Override
    public void save(JunseInfoResponseDto junseInfoResponseDto) throws Exception {

        junseMapper.save(junseInfoResponseDto);
    }

    @Override
    public JunseInfoResponseDto getproduct(String userId) throws SQLException {

        System.out.println(userId);

        return junseMapper.getproduct(userId);
    }


}
