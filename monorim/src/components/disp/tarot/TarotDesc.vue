<script setup lang="ts">
import type { CardDisplay } from "@/lib/type/typeTarot.ts";

defineProps<{
  card: CardDisplay;
  index: number;
}>();

const revText = (rev: boolean) => (rev ? "(逆位)" : "(正位)");
const revDesc = (card: CardDisplay) => (card.data.rev ? card.data.desc.reverse : card.data.desc.upright);
</script>

<template>
  <el-card class="mb-2">
    <template #header>
      <div class="title-merge">
        <span class="h6">
          <span class="h5 text-primary mt-1">{{ index + 1 + "号位 " }}</span>
          <span>
            {{ card.data.loc }}
            <span style="font-size: smaller">{{ card.data.name }}</span>
          </span>
        </span>
        <i class="small mt-1">{{ revText(card.data.rev) }}</i>
      </div>
    </template>
    <div style="text-align: left">
      <div class="text-black h6 sp2">
        {{ revDesc(card) }}
      </div>
      <div v-if="card.data.desc.desc.length > 0">
        <div class="text-muted" v-for="(line, index) in card.data.desc.desc" :key="index">
          {{ line }}
        </div>
      </div>
    </div>
  </el-card>
</template>

<style scoped>
.sp2 {
  text-indent: 2em;
}
.title-merge {
  display: flex;
  justify-content: space-between;
}
</style>
