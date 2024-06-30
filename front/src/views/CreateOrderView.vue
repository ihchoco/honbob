<script setup lang="ts">
  import { ref } from 'vue';
  import axios from "axios";
  
  const menus = ref([]);
  const selectedMenu = ref(null);
  const orderQuantity = ref(1); // 주문 수량을 위한 ref
  const tables = ref([]);
  const selectedTable = ref(null);
  
  // 메뉴 선택 메서드
  const selectMenu = (menu) => {
    selectedMenu.value = menu;
    // 메뉴 선택 시 테이블 선택 초기화
    selectedTable.value = null;
  };
  
  // 주문하기 메서드
  const placeOrder = () => {
    if (!selectedMenu.value || !selectedTable.value || selectedTable.value.name === '테이블 없음') {
      return;
    }
    console.log(`주문할 메뉴: ${selectedMenu.value.id}, 수량: ${orderQuantity.value}, 테이블: ${selectedTable.value.id}`);
    // 여기에 주문을 완료하는 로직 추가

    axios.post("http://localhost:8080/orders", {
        menuId : selectedMenu.value.id,
        orderCnt : orderQuantity.value,
        dinningTableId : selectedTable.value.id
    })
    .then(response => {
        alert("주문이 완료되었습니다.");
    })
    .catch(error => {
        alert("주문에 실패하였습니다");
        console.log("주문 중 오류 발생 : ", error);
    });
  };
  
  // 테이블 선택 메서드
  const selectTable = (table) => {
    selectedTable.value = table;
  };
  
  axios.get("http://localhost:8080/menus").then((response) => {
    menus.value = response.data;
  });
  
  axios.get("http://localhost:8080/tables").then((response) => {
    tables.value = response.data;
  });
  </script>


<template>
    <div class="kiosk-container">
      <h1>1. 음식 메뉴를 선택하세요</h1>
      <div class="menu-list">
        <div v-for="menu in menus" :key="menu.id" class="menu-item">
          <div class="menu-info">
            <h2>{{ menu.name }}</h2>
            <p>가격: {{ menu.price }}원</p>
            <p>재고 수량: {{ menu.stockQuantity }}</p>
          </div>
          <div class="menu-actions">
            <button @click="selectMenu(menu)" class="menu-button" :class="{ 'selected': selectedMenu === menu }">
              선택
            </button>
          </div>
        </div>
      </div>
      <div v-if="selectedMenu">
        <h2>선택한 메뉴</h2>
        <p>{{ selectedMenu.name }}</p>
        <p>가격: {{ selectedMenu.price }}원</p>
        <label for="orderQuantity">주문 수량:</label>
        <input type="number" id="orderQuantity" v-model="orderQuantity" min="1">
        <div class="table-selection">
          <h2>2. 테이블 선택</h2>
          <div class="table-buttons">
            <button v-for="table in tables" :key="table.id"
                    @click="selectTable(table)"
                    :class="{ 'selected': selectedTable === table }"
                    :disabled="table.name === '테이블 없음'">
              {{ table.name }}
            </button>
          </div>
          <div v-if="!tables.length">
            테이블 없음
          </div>
        </div>
        <button @click="placeOrder" :disabled="!selectedMenu || !selectedTable || selectedTable.name === '테이블 없음'" class="place-order-button">
          주문하기
        </button>
      </div>
    </div>
  </template>
  
  <style>
  .kiosk-container {
    max-width: 1000px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f0f0f0;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-top: 50px;
  }
  
  .menu-list {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
    gap: 20px;
  }
  
  .menu-item {
    padding: 10px;
    background-color: #ffffff;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .menu-item:hover {
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .menu-info {
    padding: 10px;
  }
  
  .menu-actions {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .menu-button {
    background-color: #007BFF;
    color: white;
    border: none;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .menu-button:hover {
    background-color: #0056b3;
  }
  
  .table-selection {
    margin-top: 20px;
  }
  
  .table-buttons {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
    gap: 10px;
  }
  
  .table-buttons button {
    background-color: transparent;
    color: #007BFF;
    border: 1px solid #007BFF;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease, color 0.3s ease;
    margin-bottom : 10px;  
  }
  
  .table-buttons button:hover {
    background-color: #007BFF;
    color: white;
  }
  
  .table-buttons button.selected {
    background-color: #007BFF;
    color: white;
  }
  
  .table-buttons button:disabled {
    background-color: #ccc;
    color: #999;
    cursor: not-allowed;
  }

  .place-order-button{
      width: 200px;
      height: 50px;
      margin-top : 20px;
      cursor: pointer;
      /* background-color: #c75252; */
      border-radius : 5px;
      /* color : white; */
  }


  </style>
  