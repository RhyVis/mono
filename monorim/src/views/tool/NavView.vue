<script setup lang="ts">
import { Ref, ref } from "vue";
import { apiGet } from "@/lib/util/apiMethods.ts";
import CardFrame from "@/components/frame/CardFrame.vue";

type NavData = {
  id: number;
  data: string;
  label: string;
  note: string;
};

let data: Ref<NavData[]> = ref([]);

apiGet("/api/nav").then((res) => {
  data.value = res.data;
});
</script>

<template>
  <CardFrame title="导航页">
    <el-table :data="data" style="width: 100%" stripe border>
      <el-table-column prop="id" label="ID" width="70" sortable />
      <el-table-column label="Label" width="100">
        <template #default="scope">
          <el-tag type="primary" size="small">{{ scope.row.label }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Link">
        <template #default="scope">
          <el-link :href="scope.row.data" target="_blank">
            <div class="text-ellipsis">{{ scope.row.data }}</div>
          </el-link>
        </template>
      </el-table-column>
      <el-table-column prop="note" label="Note" show-overflow-tooltip />
    </el-table>
  </CardFrame>
</template>

<style scoped>
.text-ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
