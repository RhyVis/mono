<script setup lang="ts">
import { useAuthStore } from "@/stores/auth.ts";
import { ref } from "vue";

const auth = useAuthStore();

const routerList = {
  noAuth: [
    { index: "/tarot", label: "塔罗" },
    { index: "/codex", label: "秘典" },
    { index: "/spam", label: "弹药" },
    { index: "/reverse", label: "翻转" },
  ],
  auth: [{ index: "/save", label: "存储" }],
};

const authSigh = ref(false);
const authPass = () => (authSigh.value = !authSigh.value);
</script>

<template>
  <el-container>
    <el-header>
      <el-menu :default-active="1" :router="true" mode="horizontal">
        <el-menu-item index="/">Home</el-menu-item>
        <el-sub-menu index="#">
          <template #title>Tools</template>
          <div>
            <el-menu-item v-for="(item, index) in routerList.noAuth" :key="index" :index="item.index" :route="item.index">
              {{ item.label }}
            </el-menu-item>
          </div>
          <div v-if="auth.valid">
            <el-menu-item v-for="(item, index) in routerList.auth" :key="index" :index="item.index" :route="item.index">
              {{ item.label }}
            </el-menu-item>
          </div>
        </el-sub-menu>
        <div @click="authPass">
          <el-menu-item index="/info" disabled>Info</el-menu-item>
        </div>
        <div v-if="authSigh">
          <el-menu-item index="/auth">Auth</el-menu-item>
        </div>
      </el-menu>
    </el-header>
    <slot />
  </el-container>
</template>
