<script setup lang="ts">
import {ref} from "vue";
import axios from 'axios';

const name = ref("")
const price = ref("")
const stockQuantity = ref("")

const write = function(){
    console.log(name.value, price.value, stockQuantity.value);

    axios.post("http://localhost:8080/menus", {
        name : name.value,
        price : price.value,
        stockQuantity : stockQuantity.value
    })
    .then(response => {
        alert("메뉴 등록이 완료되었습니다.");
        name.value = "";
        price.value = "0";
        stockQuantity.value = "";
    })
    .catch(error => {
        alert("메뉴 등록에 실패하였습니다");
        console.log("메뉴 등록 중 오류 발생 : ", error);
    });

}
</script>

<template>
  <div class="menu-form">
    <div class="form-group">
      <label for="menuName">메뉴명</label>
      <input v-model="name" type="text" id="menuName" placeholder="메뉴명을 입력해주세요">
    </div>
    <div class="form-group">
      <label for="price">가격</label>
      <input v-model="price" type="text" id="price" placeholder="8900">
    </div>
    <div class="form-group">
      <label for="stock">재고 수량</label>
      <input v-model="stockQuantity" type="number" id="stock" placeholder="0">
    </div>
    <button @click="write()" class="button-primary">메뉴 등록 완료</button>
  </div>
</template>

<style scoped>
.menu-form {
  max-width: 700px;
  margin: 0 auto;
  padding: 20px;
  padding-left : 80px;
  border: 1px solid #ccc;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  margin-top : 100px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input[type="text"],
input[type="number"] {
  width: 90%;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 12px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  cursor: pointer;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #45a049;
}

</style>