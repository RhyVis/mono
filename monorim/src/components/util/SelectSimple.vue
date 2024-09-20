<script setup lang="ts">
type Option = {
  label: string;
  value: string;
};

const select = defineModel("select", { required: true });

defineProps<{
  options: Option[];
}>();
</script>

<template>
  <el-select v-model="select" placeholder="Select" style="width: 240px">
    <el-option v-for="(item, index) in options" :key="index" :label="item.label" :value="item.value" />
  </el-select>
</template>
