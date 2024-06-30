import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import CreateOrderView from '../views/CreateOrderView.vue'
import CreateMenuView from '../views/CreateMenuView.vue'
import CreateTableView from '../views/CreateTableView.vue'
import SelectOrderView from '../views/SelectOrderView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: "/createOrder",
      name: "createOrder",
      component: CreateOrderView
    },
    {
      path: "/createMenu",
      name: "createMenu",
      component: CreateMenuView
    },
    {
      path: "/createTable",
      name: "createTable",
      component: CreateTableView
    },
    {
      path: "/selectOrder",
      name: "selectOrder",
      component: SelectOrderView
    },
    
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
})

export default router
