<script setup lang="ts">
import { ref } from 'vue';
import { RouterLink, RouterView } from 'vue-router'

//const imageUrl = ref('https://i.namu.wiki/i/gMIVzOVPel38eXAW8IgHWZZIq_BM9idpX0XGxw2Ue2YjjK59-daAJuntCYSGh5v5sHFoiz53S-u7yQAWxpfOo0QdmcyyFTk1iUAdwC3oqPkXu57QudEIjbcxuMwOtNs9fs70bknEWdgDj6kz5kpmMM2StH6eE7c_ZN5iu5l1K14.webp');
const imageUrl = ref('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRLKwYc1EQ8weV84zusROdOYGI9O2yfyiMQWw&s');

</script>

<template>
  <header>
    <div class="header-content">
      <div class="header-image">  
        <img :src="imageUrl" alt="Sample Image">
      </div>  
      <nav>
        <RouterLink to="/createOrder" class="button-primary">주문하기</RouterLink>
        <RouterLink to="/createMenu" class="button-secondary">메뉴등록</RouterLink>
        <RouterLink to="/createTable" class="button-third">테이블등록</RouterLink>
        <RouterLink to="/selectOrder" class="button-forth">주문조회</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />

  <footer class="footer">
      @copyright - kdn(전력거래사업개발팀)
  </footer>
</template>

<style scoped>
.header-content {
  display: flex; /* Flexbox를 사용하여 내부 요소를 가로로 정렬 */
  justify-content: center; /* 가로 방향으로 가운데 정렬 */
  align-items: center; /* 세로 방향으로 가운데 정렬 */
  flex-direction: column; /* 세로 방향으로 요소를 배열 */
  margin-top: 50px;
  text-align: center;
}

.header-image {
  margin-bottom: 20px; /* 이미지 아래 여백 추가 */
}

.header-image img {
  max-width: 100%; /* 이미지가 부모 요소 너비에 맞게 조정 */
  height: auto; /* 이미지의 높이를 자동으로 조정 */
}

nav {
  margin-top : 100px;
  margin-left : 70px;
  display: flex; /* Flexbox를 사용하여 버튼을 가로로 정렬 */
  gap: 60px; /* 버튼 사이의 간격을 설정합니다 */
}

.button-primary, .button-secondary, .button-third, .button-forth {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 60px 60px; /* 버튼의 패딩 조정 */
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 0 10px; /* 버튼 사이의 간격 조정 */
  cursor: pointer;
  border-radius: 10px;
}

.button-secondary {
  background-color: #008CBA;
}

.button-third{
  background-color: #d3897c;
}

.button-forth{
  background-color: #21a3ff;
}


.button-primary:hover, .button-secondary:hover, .button-third:hover, .button-third:hover {
  background-color: #3e2e40;
}

.router-link {
  text-decoration: none; /* 밑줄 제거 */
  color: white; /* 텍스트 색상 검정색으로 변경 */
  font-size : 18px;
}

.router-link:hover {
  color: black; /* 호버 시 텍스트 색상 검정색 유지 */
}

.footer {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  text-align: center;
  padding: 10px 0;
  margin-left : 30px;
}

</style>
