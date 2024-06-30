<script setup lang="ts">
import { ref, computed } from 'vue';
import axios from 'axios';

const orders = ref([]);
const selectedOrder = ref(null); // 선택한 주문 상세 정보를 저장할 변수

// 주문 조회 API 호출
axios.get("http://localhost:8080/orders").then((response) => {
  orders.value = response.data;
});

// 테이블 별로 주문을 그룹화하는 computed 속성
const ordersByTable = computed(() => {
  const ordersByTable = {};
  orders.value.forEach(order => {
    if (!ordersByTable[order.dinningTableName]) {
      ordersByTable[order.dinningTableName] = [];
    }
    ordersByTable[order.dinningTableName].push(order);
  });
  return ordersByTable;
});

// 각 테이블 별 주문 목록의 총 가격을 계산하는 computed 속성
const tableTotalPrices = computed(() => {
  const tableTotalPrices = {};
  for (const tableName in ordersByTable.value) {
    const orders = ordersByTable.value[tableName];
    let total = 0;
    orders.forEach(order => {
      order.orderMenuList.forEach(menu => {
        total += menu.totalPrice;
      });
    });
    tableTotalPrices[tableName] = total;
  }
  return tableTotalPrices;
});

// 주문 클릭 시 상세 정보 표시 메서드
const showOrderDetail = (order) => {
  if (selectedOrder.value === order) {
    // 이미 선택된 주문이면 상세 정보를 숨김
    selectedOrder.value = null;
  } else {
    // 새로운 주문을 선택하면 해당 주문의 상세 정보를 표시
    selectedOrder.value = order;
  }
};

// 주문 취소 메서드
const cancelOrder = (orderId) => {
  const confirmed = confirm('정말로 이 주문을 취소하시겠습니까?');
  if (confirmed) {
    axios.delete(`http://localhost:8080/orders/${orderId}`)
      .then(() => {
        axios.get("http://localhost:8080/orders").then((response) => {
          orders.value = response.data;
        });
      })
      .catch((error) => {
        console.error('주문 취소 실패:', error);
      });
  }
};
</script>




<template>
  <div class="kiosk-container">
    <h1>테이블별 주문 목록 조회</h1>
    
    <!-- 테이블 별 주문 목록 -->
    <div v-for="(tableOrders, tableName) in ordersByTable" :key="tableName">
      <h2>[ {{ tableName }} 테이블 주문 목록 ]</h2>
      <div v-for="order in tableOrders" :key="order.id" class="order-item" @click="showOrderDetail(order)">
        <div class="order-basic-info">
          <strong>주문 ID:</strong> {{ order.id }} /
          <strong>주문 상태:</strong> {{ order.status }}
          <button class="cancel-button" @click="cancelOrder(order.id)">주문 취소</button>
        </div>
        
        <!-- 선택된 주문의 상세 정보 -->
        <div v-if="selectedOrder === order" class="order-detail">
          <div class="order-info">
            <strong>주문 ID:</strong> {{ order.id }}
          </div>
          <div class="order-info">
            <strong>테이블 이름:</strong> {{ order.dinningTableName }}
          </div>
          <div class="order-info">
            <strong>주문 일시:</strong> {{ order.orderData }}
          </div>
          <div class="order-info">
            <strong>주문 상태:</strong> {{ order.status }}
          </div>
          
          <!-- 주문 메뉴 목록 -->
          <h3>주문 메뉴 목록</h3>
          <ul>
            <li v-for="menu in order.orderMenuList" :key="menu.name">
              <div class="menu-info">
                <strong>메뉴 이름:</strong> {{ menu.name }}
              </div>
              <div class="menu-info">
                <strong>가격:</strong> {{ menu.price }}원
              </div>
              <div class="menu-info">
                <strong>주문 수량:</strong> {{ menu.count }}개
              </div>
              <div class="menu-info">
                <strong>총 가격:</strong> {{ menu.totalPrice }}원
              </div>
            </li>
          </ul>
        </div>
      </div>
      
      <!-- 각 테이블별 주문 목록의 총 가격 표시 -->
      <div class="table-total-price">
        <strong>테이블 별 총 주문 금액:</strong> {{ tableTotalPrices[tableName] }}원
      </div>
    </div>
  </div>
</template>



   


<style scoped>
.kiosk-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  margin-top: 50px;
}

.order-item {
  margin-bottom: 20px;
  padding: 10px;
  background-color: #f0f0f0;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

.order-basic-info {
  margin-bottom: 5px;
}

.order-detail {
  margin-top: 10px;
  padding: 10px;
  background-color: #ffffff;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.order-info {
  margin-bottom: 5px;
}

.menu-info {
  margin-bottom: 5px;
}

ul {
  list-style-type: none;
  padding: 0;
}

/* 테이블별 총 주문 금액 스타일 */
.table-total-price {
  padding: 10px;
  background-color: #ec7058;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  display: inline-block; /* 인라인 블록 요소로 배치 */
  font-weight: bold;
  color: #333;
}

.cancel-button {
  background-color: #ec7058;
  color: #fff;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  margin-left: 10px; /* Adjust as needed */
}
</style>

