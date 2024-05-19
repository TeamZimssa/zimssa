import { localAxios } from "@/util/http-commons";

const local = localAxios();

const API_BASE_URL = "http://localhost:8080/house-filter";

export const searchKeyword = async (keyword) => {
  const response = await axios.get(`${API_BASE_URL}`, { params: { keyword, tradeType } });
  return response.data;
};

const searchKeyword = async (apartmentName) => {
  try {
    const response = await local.get(`/search-keywords?input=${encodeURIComponent(apartmentName)}`);
    return response.data.result;
  } catch (error) {
    console.error("Error fetching search keywords:", error);
    return [];
  }
};
