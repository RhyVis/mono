<script setup lang="ts">
import axios from "axios";
import { reactive, ref } from "vue";
import CardFrame from "@/components/frame/CardFrame.vue";
import CopyButton from "@/components/util/CopyButton.vue";
import ReadButton from "@/components/util/ReadButton.vue";

const query = reactive({
  id: 0,
  text: "",
  note: "",
});
const result = reactive({
  text: "",
  note: "",
  sign: "---",
});

const cpBtnKey = ref(0);
const cpBtnReset = () => (cpBtnKey.value = new Date().getTime());

const actStore = async () => {
  cpBtnReset();
  if (query.text.length === 0) {
    alert("不要空置内容");
    return;
  }
  const dt = (await axios.put("/api/save", query)).data.result;
  if (dt === 0) {
    result.sign = "存储成功";
  } else {
    console.error(dt);
    result.sign = "存储失败";
  }
};

const actSelect = async () => {
  cpBtnReset();
  const r = (await axios.post("/api/save", query)).data.result;
  const { id, text, note } = r;
  if (id < 0) {
    result.sign = "读取失败";
  } else {
    result.text = query.text = text;
    result.note = query.note = note;
    result.sign = "读取成功";
  }
};
</script>

<template>
  <CardFrame title="存储字符">
    <el-form ref="formRef" :model="query" label-width="auto">
      <el-form-item label="存储内容">
        <el-input v-model="query.text" type="textarea" placeholder="保存内容" required />
      </el-form-item>
      <el-form-item label="存储备注">
        <el-input v-model="query.note" type="text" placeholder="保存备注" required />
      </el-form-item>
      <el-divider />
      <el-form-item label="读取内容">
        <el-input :value="result.text" type="textarea" placeholder="远程内容" readonly />
      </el-form-item>
      <el-form-item label="备注内容">
        <el-input :value="result.note" type="text" placeholder="远程备注" readonly />
      </el-form-item>
      <el-divider />
      <el-form-item label="ID">
        <el-input-number v-model="query.id" size="small" :min="0" :max="1000" />
      </el-form-item>
      <el-form-item label="操作结果">
        {{ result.sign }}
      </el-form-item>
      <el-form-item label="操作数据">
        <el-button type="primary" @click="actStore">存储</el-button>
        <el-button type="primary" @click="actSelect">读取</el-button>
        <CopyButton :target="result.text" :key="cpBtnKey" />
        <ReadButton v-model:target="query.text" />
      </el-form-item>
    </el-form>
  </CardFrame>
</template>
