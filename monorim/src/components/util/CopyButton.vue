<script setup lang="ts">
import { computed, ref } from "vue";
import { CopyDocument } from "@element-plus/icons-vue";
import useClipboard from "vue-clipboard3";

const props = defineProps({
  target: {
    type: String,
    default: "",
  },
  mode: {
    type: Number,
    default: 0,
  },
});

const btnState = ref(0);
const btnDisplay = computed<String>(() => {
  switch (btnState.value) {
    case 0:
      return "";
    case 1:
      return "success";
    case 2:
      return "danger";
    default:
      return "warning";
  }
});

const { toClipboard } = useClipboard();
const action = async () => {
  const { target } = props;
  if (target.length > 0) {
    try {
      await toClipboard(target);
      btnState.value = 1;
    } catch (e) {
      console.error(e);
      btnState.value = 2;
    }
  } else {
    btnState.value = 2;
  }
};
</script>

<template>
  <el-tooltip content="复制输出内容" placement="top">
    <el-button :type="btnDisplay" @click="action">
      <el-icon><CopyDocument /></el-icon> </el-button
  ></el-tooltip>
</template>
