import { localAxios } from "@/util/http-commons";

const local = localAxios();

const searchKeyword = async (apartmentName) => {
  try {
    const response = await local.get(`/search-keywords?input=${encodeURIComponent(apartmentName)}`);
    return response.data.result;
  } catch (error) {
    console.error("Error fetching search keywords:", error);
    return [];
  }
};

const getApartmentData = async (aptcode, dongName) => {
  try {
    const response = await local.get(
      `/house-deals?apartment-name=${aptcode}&dongName=${dongName}&sort-option=DESC`
    );
    return response.data.result;
  } catch (error) {
    console.error("Error fetching apartment data:", error);
    alert("거래 정보가 없습니다.");
    throw error;
  }
};

const getDongData = async (dongName) => {
  try {
    const response = await local.get(`/house-deals/dongName?dongName=${dongName}`);
    return response.data.result;
  } catch (error) {
    console.error("Error fetching dong data:", error);
    return [];
  }
};

const getApartmentMarker = async (lat, lng) => {
  try {
    const response = await local.get(
      `/house-deals/near?latitude=${lat}&longitude=${lng}&distance=1000`
    );
    return response.data.result;
  } catch (error) {
    console.error("Error fetching apartment markers:", error);
    return [];
  }
};

const getDongMarker = async () => {
  try {
    const response = await local.get(`/location-deal/dong`);
    return response.data.result;
  } catch (error) {
    console.error("Error fetching dong markers:", error);
    return [];
  }
};

const getGuMarker = async () => {
  try {
    const response = await local.get(`/location-deal/gu`);
    return response.data.result;
  } catch (error) {
    console.error("Error fetching gu markers:", error);
    return [];
  }
};

export {
  searchKeyword,
  getApartmentData,
  getDongMarker,
  getGuMarker,
  getApartmentMarker,
  getDongData,
};
