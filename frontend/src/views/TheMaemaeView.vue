<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { debounce } from "lodash";
import { searchByKeyword, getDetail } from "@/api/house"; // 서버 API 함수들

const map = ref(null);
const markers = ref([]);
const searchQuery = ref("");
const searchResults = ref([]);
const selectedApartment = ref(null);
const selectedApartmentDetails = ref([]);
const showSearchResults = ref(true);
const selectedArea = ref("전체");

const center = ref({ lat: 37.5665, lng: 126.978 }); // 초기 지도 중심 (서울시청 기준)
const roadViewContainer = ref(null);

const initMap = () => {
  const mapContainer = document.getElementById("map");
  const mapOption = {
    center: new kakao.maps.LatLng(center.value.lat, center.value.lng),
    level: 3,
  };
  map.value = new kakao.maps.Map(mapContainer, mapOption);

  kakao.maps.event.addListener(map.value, "tilesloaded", () => {
    addEventListeners();
  });
};

const initRoadView = (lat, lng) => {
  if (roadViewContainer.value) {
    const roadview = new kakao.maps.Roadview(roadViewContainer.value);
    const roadviewClient = new kakao.maps.RoadviewClient();
    const position = new kakao.maps.LatLng(lat, lng);

    roadviewClient.getNearestPanoId(position, 50, function (panoId) {
      roadview.setPanoId(panoId, position);
    });
  }
};

const addEventListeners = () => {
  kakao.maps.event.addListener(map.value, "dragend", () => {
    const mapCenter = map.value.getCenter();
    center.value.lat = mapCenter.getLat();
    center.value.lng = mapCenter.getLng();
    updateMarkers();
  });

  kakao.maps.event.addListener(map.value, "zoom_changed", () => {
    updateMarkers();
  });
};

const updateMarkers = async () => {
  clearMarkers();
  if (selectedApartment.value) {
    const data = await getDetail(
      "maemae_info",
      selectedApartment.value.dongName,
      selectedApartment.value.aptName
    );
    displayMarkers(data);
  }
};

const clearMarkers = () => {
  markers.value.forEach((marker) => marker.setMap(null));
  markers.value = [];
};

const displayMarkers = (markersData) => {
  markersData.forEach((data) => {
    const position = new kakao.maps.LatLng(data.lat, data.lng);
    const marker = new kakao.maps.Marker({
      position,
      map: map.value,
    });

    const infoWindow = new kakao.maps.InfoWindow({
      content: `<div style="padding:5px;font-size:12px;">${selectedApartment.value.aptName}</div>`,
    });
    infoWindow.open(map.value, marker);

    markers.value.push(marker);
  });
};

const handleSearch = debounce(async () => {
  if (searchQuery.value.trim() !== "") {
    try {
      searchResults.value = await searchByKeyword("maemae_info", searchQuery.value);
      showSearchResults.value = true;
      console.log(searchResults.value);
    } catch (error) {
      console.error("Error fetching search results:", error);
    }
  } else {
    searchResults.value = [];
    showSearchResults.value = false;
  }
}, 500);

const handleSelectApartment = async (apartment) => {
  selectedApartment.value = apartment;
  showSearchResults.value = false;
  selectedArea.value = "전체";
  try {
    const detail = await getDetail("maemae_info", apartment.dongName, apartment.aptName);
    if (detail && detail.length > 0) {
      selectedApartmentDetails.value = detail;
      const apartmentDetail = detail[0];
      if (map.value) {
        const position = new kakao.maps.LatLng(apartmentDetail.lat, apartmentDetail.lng);
        map.value.setCenter(position);
        map.value.setLevel(3);
        displayMarkers([apartmentDetail]);
        initRoadView(apartmentDetail.lat, apartmentDetail.lng);
      }
    } else {
      console.error("No details found for the selected apartment.");
    }
  } catch (error) {
    console.error("Error fetching apartment details:", error);
  }
};

const uniqueAreas = computed(() => {
  const areas = new Set(selectedApartmentDetails.value.map((detail) => detail.area));
  return ["전체", ...Array.from(areas).sort((a, b) => parseFloat(a) - parseFloat(b))];
});

const filteredDetails = computed(() => {
  if (selectedArea.value === "전체") {
    return selectedApartmentDetails.value;
  }
  return selectedApartmentDetails.value.filter((detail) => detail.area === selectedArea.value);
});

const priceStats = computed(() => {
  if (filteredDetails.value.length === 0) return { min: 0, max: 0, avg: 0 };

  const prices = filteredDetails.value.map((detail) =>
    parseInt(detail.dealPrice.replace(/,/g, ""), 10)
  );
  const min = Math.min(...prices);
  const max = Math.max(...prices);
  const avg = prices.reduce((sum, price) => sum + price, 0) / prices.length;

  return {
    min: (min / 10000).toFixed(2),
    max: (max / 10000).toFixed(2),
    avg: (avg / 10000).toFixed(2),
  };
});

watch(searchQuery, handleSearch);

onMounted(() => {
  initMap();
});
</script>

<template>
  <div style="position: relative; height: 100vh">
    <div
      style="
        width: 30%;
        height: 100%;
        float: left;
        padding: 16px;
        box-sizing: border-box;
        overflow-y: auto;
        background-color: #f8f8f8;
      "
    >
      <input
        v-model="searchQuery"
        type="text"
        placeholder="아파트 이름 검색"
        style="
          padding: 8px;
          font-size: 16px;
          width: 100%;
          margin-bottom: 16px;
          border: 1px solid #ccc;
          border-radius: 4px;
        "
      />
      <div
        v-if="showSearchResults && searchResults.length"
        style="
          background: white;
          border: 1px solid #ccc;
          max-height: 200px;
          overflow-y: auto;
          margin-bottom: 16px;
          box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        "
      >
        <ul style="list-style-type: none; padding: 0; margin: 0">
          <li
            v-for="apartment in searchResults"
            :key="apartment.aptName"
            @click="handleSelectApartment(apartment)"
            style="padding: 8px; cursor: pointer"
            @mouseover="event.target.style.backgroundColor = '#f0f0f0'"
            @mouseleave="event.target.style.backgroundColor = ''"
          >
            {{ apartment.dongName }} - {{ apartment.aptName }}
          </li>
        </ul>
      </div>
      <div v-if="selectedApartmentDetails.length">
        <h2>{{ selectedApartment.aptName }}</h2>
        <p>
          <strong>{{ selectedApartment.dongName }}</strong>
        </p>
        <div v-if="filteredDetails.length">
          <div>
            <p>
              {{ priceStats.min }}억원 ~ {{ priceStats.max }}억원 (평균: {{ priceStats.avg }}억원)
            </p>
          </div>
        </div>
        <div
          ref="roadViewContainer"
          style="
            width: 100%;
            height: 300px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
          "
        ></div>
        <div>
          <select
            v-model="selectedArea"
            style="
              padding: 8px;
              margin-bottom: 16px;
              width: 100%;
              border: 1px solid #ccc;
              border-radius: 4px;
            "
          >
            <option v-for="area in uniqueAreas" :key="area" :value="area">{{ area }}</option>
          </select>
        </div>
        <ul style="padding: 0; list-style-type: none; max-height: 300px; overflow-y: auto">
          <li
            style="
              display: flex;
              justify-content: space-between;
              padding: 8px;
              border-bottom: 1px solid #ccc;
              background: #f0f0f0;
              font-weight: bold;
            "
          >
            <span>거래 날짜</span>
            <span>면적</span>
            <span>거래 가격</span>
            <span>층</span>
          </li>
          <li
            v-for="detail in filteredDetails"
            :key="detail.dealDate + detail.area + detail.floor"
            style="
              display: flex;
              justify-content: space-between;
              padding: 8px;
              border-bottom: 1px solid #eee;
              transition: background-color 0.2s;
              cursor: pointer;
            "
            @mouseover="event.target.style.backgroundColor = '#f9f9f9'"
            @mouseleave="event.target.style.backgroundColor = ''"
          >
            <span>{{ detail.dealDate }}</span>
            <span>{{ detail.area }} ㎡</span>
            <span
              >{{
                (parseInt(detail.dealPrice.replace(/,/g, ""), 10) / 10000).toFixed(2)
              }}
              억원</span
            >
            <span>{{ detail.floor }}층</span>
          </li>
        </ul>
      </div>
    </div>
    <div id="map" style="width: 70%; height: 100%"></div>
  </div>
</template>

<style scoped>
html,
body {
  margin: 0;
  padding: 0;
  height: 100%;
  overflow: hidden;
}

ul {
  padding: 0;
  margin: 0;
}

li {
  list-style-type: none;
}
</style>
