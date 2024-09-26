<script setup lang="ts">
import { reactive, ref, computed, onMounted, Ref } from "vue";
import { useTarotStore } from "@/stores/tool/tarot.ts";
import { apiGet, apiPost } from "@/lib/util/apiMethods.ts";
import { Card, CardDisplay, DeckInfo } from "@/lib/type/typeTarot.ts";
import CardFrame from "@/components/frame/CardFrame.vue";
import TarotMain from "@/components/disp/tarot/TarotMain.vue";
import TarotDesc from "@/components/disp/tarot/TarotDesc.vue";
import SelectSimple from "@/components/util/SelectSimple.vue";

const store = useTarotStore();

const query = reactive({
  deck: "waite",
  full: true,
  count: 1,
});

const activeTab = ref("simple");

interface DeckInfoInterface {
  [name: string]: DeckInfo;
}

let deckInfoMap: Ref<DeckInfoInterface> = ref({});
let deckInfoList: Ref<DeckInfo[]> = ref([]);
let deckInfoSelect: Ref<any[]> = ref([]);

apiGet("api/tarot/info").then((res) => {
  deckInfoMap.value = res.data;
  const list = Object.values(res.data) as DeckInfo[];
  deckInfoList.value = list;
  deckInfoSelect.value = list.map((item) => {
    return {
      value: item.name,
      label: item.loc,
    };
  });
});

const deckResult = ref([] as CardDisplay[]);

const deckShowDesc = ref(true);
const deckFull = computed(() => deckInfoMap.value[query.deck]?.full ?? false);
const deckMax = computed(() => (deckFull.value && query.full ? 78 : 22));
const deckFullTooltip = computed(() => (deckFull.value ? "完整卡组包括小阿尔卡那" : "该卡组只包括大阿尔卡那"));

const drawDeck = async () => {
  store.deck = query.deck;
  const respond = (await apiPost("/api/tarot", query)).data as Card[];
  deckResult.value = respond.map((card) => {
    return {
      data: card,
      showImg: true,
      showDesc: false,
    };
  });
};

onMounted(() => {
  query.deck = store.deck;
});
</script>

<template>
  <CardFrame title="塔罗牌">
    <!-- 选项表单 -->
    <el-form :model="query" label-width="auto">
      <el-tabs v-model="activeTab" animated>
        <!-- 简单类型抽牌 -->
        <el-tab-pane label="简单模式" name="simple">
          <el-form-item label="卡面类型">
            <SelectSimple :options="deckInfoSelect" v-model:select="query.deck" />
          </el-form-item>
          <el-form-item label="完整卡组">
            <el-tooltip :content="deckFullTooltip" placement="top">
              <el-switch v-model="query.full" :disabled="!deckFull" />
            </el-tooltip>
          </el-form-item>
          <el-form-item label="展示提示">
            <el-tooltip content="在下方展示所有抽到牌的提示" placement="top">
              <el-switch v-model="deckShowDesc" />
            </el-tooltip>
          </el-form-item>
          <el-form-item label="抽牌数量">
            <el-input-number v-model="query.count" size="small" min="1" :max="deckMax" />
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      <el-form-item label="准备完成">
        <el-button type="primary" @click="drawDeck">抽牌</el-button>
      </el-form-item>
    </el-form>
    <!-- 结果显示 -->
    <div v-if="deckResult.length > 0">
      <el-divider />
      <!-- 图像 -->
      <el-row :gutter="10">
        <el-col :xs="24" :sm="12" :lg="6" v-for="(card, index) in deckResult">
          <TarotMain :key="index" :card="card" :index="index" />
        </el-col>
      </el-row>
      <!-- 信息 -->
      <div v-if="deckShowDesc">
        <el-divider />
        <el-row :gutter="10">
          <el-col :md="24" :lg="12" v-for="(card, index) in deckResult">
            <TarotDesc :key="index" :card="card" :index="index" />
          </el-col>
        </el-row>
      </div>
    </div>
  </CardFrame>
</template>
