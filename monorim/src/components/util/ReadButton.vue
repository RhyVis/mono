<script setup lang="ts">
import { computed, ref } from "vue";
import { DocumentAdd } from "@element-plus/icons-vue";

defineProps({
  target: {
    type: String,
    required: true,
  },
});

const btnState = ref(0);
const btnDisplay = computed(() => {
  switch (btnState.value) {
    case 0:
      return "info";
    case 1:
      return "danger";
    default:
      return "warning";
  }
});

const emit = defineEmits(["update:target"]);
const action = async () => {
  btnState.value = 0;
  try {
    const text = await navigator.clipboard.readText();
    emit("update:target", text);
  } catch (err) {
    btnState.value = 1;
    console.error("Failed to read clipboard contents: ", err);
  }
};
</script>

<template>
  <el-tooltip content="读取剪贴板内容" placement="top">
    <el-button :type="btnDisplay" @click="action">
      <el-icon><DocumentAdd /></el-icon> </el-button
  ></el-tooltip>
</template>
