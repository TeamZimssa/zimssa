package com.ssafy.zimssa.maemae.model.dto.response;

import com.ssafy.zimssa.maemae.model.dto.Maemae;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaemaeResponseDto {

    private String sggCd;
    private String buildYear;
    private Float bldgArea;
    private String bjdongCd;
    private String sggNm;
    private String bubeon;
    private String bjdongNm;
    private String rightGbn;
    private String objAmt;
    private String bldgNm;
    private String houseType;
    private Integer floor;
    private String accYear;
    private String reqGbn;
    private String bonbeon;
    private String dealYmd;
    private String lat;
    private String lng;
    private String apiBuildingName;

    public static MaemaeResponseDto fromMaemae(Maemae maemae) {
        MaemaeResponseDto dto = new MaemaeResponseDto();
        dto.setSggCd(maemae.getSggCd());
        dto.setBuildYear(maemae.getBuildYear());
        dto.setBldgArea(maemae.getBldgArea());
        dto.setBjdongCd(maemae.getBjdongCd());
        dto.setSggNm(maemae.getSggNm());
        dto.setBubeon(maemae.getBubeon());
        dto.setBjdongNm(maemae.getBjdongNm());
        dto.setRightGbn(maemae.getRightGbn());
        dto.setObjAmt(maemae.getObjAmt());
        dto.setBldgNm(maemae.getBldgNm());
        dto.setHouseType(maemae.getHouseType());
        dto.setFloor(maemae.getFloor());
        dto.setAccYear(maemae.getAccYear());
        dto.setReqGbn(maemae.getReqGbn());
        dto.setBonbeon(maemae.getBonbeon());
        dto.setDealYmd(maemae.getDealYmd());
        dto.setLat(maemae.getLat());
        dto.setLng(maemae.getLng());
        dto.setApiBuildingName(maemae.getApiBuildingName());
        return dto;
    }

}
