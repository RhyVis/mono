<script setup lang="ts">
import { computed, ref } from "vue";
import useClipboard from "vue-clipboard3";

interface State {
  type: string;
  value: string;
}

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
const btnDisplay = computed<State>(() => {
  switch (btnState.value) {
    case 0:
      return {
        type: "",
        value: "复制",
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
        type: "warning",
        value: "未知",
      };
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
  <el-button :type="btnDisplay.type" @click="action">
    {{ btnDisplay.value }}
  </el-button>
</template>
