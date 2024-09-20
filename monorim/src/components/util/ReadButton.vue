<script setup lang="ts">
import { computed, ref } from "vue";

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
      return {
        type: "info",
        value: "读取",
      };
    case 1:
      return {
        type: "danger",
        value: "失败",
      };
    default:
      return {
        type: "warning",
        value: "未知",
      };
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
  <el-button :type="btnDisplay.type" @click="action">
    {{ btnDisplay.value }}
  </el-button>
</template>
