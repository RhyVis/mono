import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import { useAuthStore } from "@/stores/auth.ts";
import { validateToken } from "@/lib/utilAuth.ts";
import routerTool from "@/routers/routerTool.ts";
import HomeView from "@/views/HomeView.vue";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "Home",
    component: HomeView,
  },
  ...routerTool,
  {
    path: "/auth",
    name: "Auth",
    component: () => import("@/views/auth/AuthView.vue"),
  },
  {
    path: "/:pathMatch(.*)",
    name: "404",
    component: () => import("@/views/ErrorView.vue"),
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

router.beforeEach(async (to, _, next) => {
  const auth = useAuthStore();
  if (to.meta.requireToken) {
    const { token } = auth;
    if (token.length == 0) {
      next("/auth");
    } else {
      const r = await validateToken(token);
      if (r) {
        next();
      } else {
        next("/");
      }
    }
  } else {
    next();
  }
});

export default router;
