<script setup lang="ts">
import { computed, onMounted, reactive, ref } from "vue";
import { useAuthStore } from "@/stores/auth";

import CardFrame from "@/components/frame/CardFrame.vue";

const auth = useAuthStore();

const query = reactive({
  token: "",
});

const verifyState = ref(0);
const verifyElement = computed(() => {
  switch (verifyState.value) {
    case 0:
      return {
        type: "primary",
        value: "更新",
      };
    case 1:
      return {
        type: "success",
        value: "成功",
      };
    case 2:
      return {
        type: "danger",
        value: "失败",
      };
    default:
      return {
        type: "info",
        value: "未知",
      };
  }
});

const verify = async () => {
  const { token } = query;
  clearState.value = 0;
  if (token.length > 0 && token != "不要空置") {
    try {
      auth.updateToken(token);
      verifyState.value = await auth.validateToken;
    } catch (e) {
      console.error(e);
      verifyState.value = 2;
    }
  } else {
    query.token = "不要空置";
    verifyState.value = 2;
  }
};

const clearState = ref(0);
const clearElement = computed(() => {
  switch (clearState.value) {
    case 0:
      return {
        type: "warning",
        value: "移除",
      };
    case 1:
      return {
        type: "danger",
        value: "成功",
      };
    default:
      return {
        type: "info",
        value: "未知",
      };
  }
});

const clear = () => {
  auth.$reset();
  clearState.value = 1;
};

onMounted(() => {
  verifyState.value = 0;
  clearState.value = 0;
});
</script>

<template>
  <CardFrame title="校验">
    <el-form :model="query" label-width="auto">
      <el-form-item label="后端校验码">
        <el-input v-model="query.token" type="text" placeholder="输入后端设置的Token" />
      </el-form-item>
      <el-form-item label="验证">
        <el-button :type="verifyElement.type" @click="verify">
          {{ verifyElement.value }}
        </el-button>
        <el-button :type="clearElement.type" @click="clear">
          {{ clearElement.value }}
        </el-button>
      </el-form-item>
    </el-form>
  </CardFrame>
</template>
