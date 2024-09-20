<script setup lang="ts">
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
  <el-button type="info" @click="action">清除</el-button>
</template>
