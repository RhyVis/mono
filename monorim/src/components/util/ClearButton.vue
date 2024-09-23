<script setup lang="ts">
import { DocumentRemove } from "@element-plus/icons-vue";

defineProps({
  target: {
    type: String,
    required: true,
  },
});

const emit = defineEmits(["update:target"]);
const action = () => {
  emit("update:target", "");
};
</script>

<template>
  <el-tooltip content="清空输入内容" placement="top">
    <el-button type="info" @click="action">
      <el-icon><DocumentRemove /></el-icon> </el-button
  ></el-tooltip>
</template>
