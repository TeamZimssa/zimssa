import { createRouter, createWebHistory } from "vue-router";
import IndexView from "../views/IndexView.vue";
import HouseView from "../views/HouseView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "index",
      component: () => import("../views/IndexView.vue"),
    },
    {
      path: "/house",
      name: "house",
      component: () => import("../views/HouseView.vue"),
    },
  ],
});

export default router;
