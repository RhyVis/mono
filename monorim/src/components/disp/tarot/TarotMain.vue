<script setup lang="ts">
import type { CardDisplay } from "@/lib/typeTarot.ts";

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
      <div @click="card.showImg = !card.showImg">
        <div class="h5 strong text-primary-emphasis">
          {{ card.data.loc }}
        </div>
        <i class="small">{{ index + 1 }} - {{ revText(card.data.rev) }}</i>
      </div>
    </template>
    <transition name="el-zoom-in-top">
      <div v-show="card.showImg">
        <div @click="card.showDesc = !card.showDesc" class="div-full">
          <div v-show="!card.showDesc">
            <img class="img-full" :class="{ reversed: card.data.rev }" :alt="card.data.name" :src="card.data.img" lazy />
          </div>
          <div v-show="card.showDesc" style="text-align: left">
            <div class="h6">
              {{ revDesc(card) }}
            </div>
            <div class="text-muted" style="font-size: smaller">
              {{ " " + card.data.desc.desc.join("") }}
            </div>
          </div>
        </div>
      </div>
    </transition>
  </el-card>
</template>

<style scoped>
.reversed {
  transform: rotate(180deg);
}
.div-full {
  width: 100%;
  height: 360px;
  display: flex;
  justify-content: center;
  align-items: center;
  .img-full {
    max-width: 100%;
    max-height: 360px;
  }
}
</style>
