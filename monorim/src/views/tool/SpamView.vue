<script setup lang="ts">
import { onMounted, reactive, ref } from "vue";
import { useSpamStore } from "@/stores/tool/spam.ts";
import { uApiPost } from "@/lib/util/apiMethods.ts";
import CardFrame from "@/components/frame/CardFrame.vue";
import SelectSimple from "@/components/util/SelectSimple.vue";
import CopyButton from "@/components/util/CopyButton.vue";

const store = useSpamStore();

const query = reactive({
  type: "spam_min",
  code: "none",
  limit: 1,
});
const result = ref(["快乐生活每一天，请不要用这个工具的结果来攻击他人哦😊", "仅供学习交流使用，由您不当使用造成的后果，将由您承担"]);

const action = async () => {
  const r = (await uApiPost("api/spam", [query.type, query.code], [query.limit])).data as Entry[];
  result.value = r.map((entry) => entry.text);
  store.update(query, activeTab.value);
  cpBtnReset();
};

const codeTypes = [
  { value: "none", label: "直白对决😅" },
  { value: "nmsl", label: "抽象加密🤗" },
  { value: "trad", label: "繁体传统🤔" },
  { value: "sprk", label: "火星密文😘" },
  { value: "unid", label: "形近转换🧐" },
];

const activeTab = ref("spam");
const changeTab = () => {
  switch (activeTab.value) {
    case "spam":
      query.type = "spam_min";
      break;
    case "mmr":
      query.type = "genshin";
      break;
    case "meme":
      query.type = "acgn";
      break;
    default:
  }
};

const cpBtnKey = ref(0);
const cpBtnReset = () => (cpBtnKey.value = new Date().getTime());

type Entry = {
  id: number;
  text: string;
};

onMounted(() => {
  query.type = store.type;
  query.code = store.code;
  query.limit = store.limit;
  activeTab.value = store.tab;
});
</script>

<template>
  <CardFrame title="弹药库">
    <el-form :model="query" label-width="auto">
      <el-tabs v-model="activeTab" @tab-change="changeTab">
        <!--Spam-->
        <el-tab-pane label="祖安特区" name="spam">
          <el-form-item label="使用说明">
            <span style="text-align: left">高强度的版本很容易被夹，建议加上转义</span>
          </el-form-item>
          <el-form-item label="选择强度">
            <el-radio-group v-model="query.type">
              <el-radio-button label="小喷怡情😋" value="spam_min" />
              <el-radio-button label="火力全开😠" value="spam_max" />
            </el-radio-group>
          </el-form-item>
        </el-tab-pane>
        <!--MMR-->
        <el-tab-pane label="二游笑话" name="mmr">
          <el-form-item label="使用说明">
            <span style="text-align: left">介于各路孝子挂对面的时候都是截图挂人，所以这数据库里面很多东西也都是OCR扫出来的，有错字就当二游痴子没文化吧</span>
          </el-form-item>
          <el-form-item label="选择游戏">
            <el-radio-group v-model="query.type">
              <el-tooltip content="原神" placement="top">
                <el-radio-button label="原神怎么你了" value="genshin" />
              </el-tooltip>
              <el-tooltip content="明日方舟" placement="top">
                <el-radio-button label="二游半壁江山" value="arknights" />
              </el-tooltip>
            </el-radio-group>
          </el-form-item>
        </el-tab-pane>
        <!--Meme-->
        <el-tab-pane label="复制粘贴" name="meme">
          <el-form-item label="使用说明">
            <span style="text-align: left">我喜欢复制粘贴</span>
          </el-form-item>
          <el-form-item label="选择主题">
            <el-radio-group v-model="query.type">
              <el-tooltip content="二次元欠图了" placement="top">
                <el-radio-button label="反二圣经" value="acgn" />
              </el-tooltip>
              <el-tooltip content="不知道该怎么分类了" placement="top">
                <el-radio-button label="纯正低能" value="dinner" />
              </el-tooltip>
            </el-radio-group>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      <el-form-item label="加密方式">
        <SelectSimple v-model:select="query.code" :options="codeTypes" />
      </el-form-item>
      <el-form-item label="妙语连珠">
        <el-input-number v-model="query.limit" size="small" :min="1" :max="10" />
      </el-form-item>
      <el-form-item label="准备开火">
        <el-button type="danger" @click="action">😤</el-button>
        <CopyButton :target="result.join('\n')" :key="cpBtnKey" />
      </el-form-item>
    </el-form>
    <!-- 结果 -->
    <el-divider />
    <div class="mt-2 mb-2 text-muted">
      <div v-for="(item, index) in result" :key="index">{{ item }}</div>
    </div>
  </CardFrame>
</template>
