import { RouteRecordRaw } from "vue-router";

const routerTool: RouteRecordRaw[] = [
  {
    path: "/tarot",
    name: "Tarot",
    component: () => import("@/views/tool/TarotView.vue"),
  },
  {
    path: "/codex",
    name: "Codex",
    component: () => import("@/views/tool/CodexView.vue"),
  },
  {
    path: "/spam",
    name: "Spam",
    component: () => import("@/views/tool/SpamView.vue"),
  },
  {
    path: "/reverse",
    name: "Reverse",
    component: () => import("@/views/tool/ReverseView.vue"),
  },
  // Auth required
  {
    path: "/nav",
    name: "Nav",
    meta: { requireToken: true },
    component: () => import("@/views/tool/NavView.vue"),
  },
  {
    path: "/save",
    name: "Save",
    meta: { requireToken: true },
    component: () => import("@/views/tool/SaveView.vue"),
  },
];

export default routerTool;
