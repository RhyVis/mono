<script setup lang="ts">
import { reactive, ref } from "vue";
import { apiPost } from "@/lib/util/apiMethods.ts";
import { VideoPlay } from "@element-plus/icons-vue";
import CardFrame from "@/components/frame/CardFrame.vue";
import CopyButton from "@/components/util/CopyButton.vue";
import ReadButton from "@/components/util/ReadButton.vue";
import ClearButton from "@/components/util/ClearButton.vue";

const query = reactive({
  text: "哦牛",
  type: "nmsl",
  decode: false,
});
const result = ref();

const cpBtnKey = ref(0);
const cpBtnKeyReset = () => (cpBtnKey.value = new Date().getTime());

const action = async () => {
  if (query.text.length === 0) {
    result.value = "你不输入你转什么";
  } else {
    const r = await apiPost("api/codex", {
      text: query.text,
      type: query.type,
      code: !query.decode,
    });
    result.value = r.data;
    cpBtnKeyReset();
  }
};
</script>

<template>
  <CardFrame title="抽象翻译器">
    <el-form :model="query" label-width="auto">
      <el-tabs v-model="query.type">
        <!-- Abstract -->
        <el-tab-pane label="抽象转换" name="nmsl">
          <el-form-item label="原始文本">
            <el-input v-model="query.text" type="textarea" placeholder="你不输入你用什么工具？" required></el-input>
          </el-form-item>
          <el-form-item label="解码抽象">
            <el-tooltip content="只能解析成拼音，并且不全" placement="top">
              <el-switch v-model="query.decode" />
            </el-tooltip>
          </el-form-item>
        </el-tab-pane>
        <!-- Traditional Chinese -->
        <el-tab-pane label="繁体转换" name="trad">
          <el-form-item label="原始文本">
            <el-input v-model="query.text" type="textarea" placeholder="你不输入你用什么工具？" required></el-input>
          </el-form-item>
          <el-form-item label="解码抽象">
            <el-switch v-model="query.decode" />
          </el-form-item>
        </el-tab-pane>
        <!-- Spark -->
        <el-tab-pane label="火星转换" name="sprk">
          <el-form-item label="原始文本">
            <el-input v-model="query.text" type="textarea" placeholder="你不输入你用什么工具？" required></el-input>
          </el-form-item>
          <el-form-item label="解码抽象">
            <el-switch v-model="query.decode" />
          </el-form-item>
        </el-tab-pane>
        <!-- Unicode diff -->
        <el-tab-pane label="形近转换" name="unid">
          <el-form-item label="原始文本">
            <el-input v-model="query.text" type="textarea" placeholder="你不输入你用什么工具？" required></el-input>
          </el-form-item>
        </el-tab-pane>
      </el-tabs>
      <el-form-item label="输出结果">
        <span>{{ result }}</span>
      </el-form-item>
    </el-form>
    <el-form-item label="操作">
      <el-button type="primary" @click="action">
        <el-icon><VideoPlay /></el-icon>
      </el-button>
    </el-form-item>
    <el-form-item label="工具">
      <el-button-group>
        <CopyButton :target="result" :key="cpBtnKey" />
        <ReadButton v-model:target="query.text" />
        <ClearButton v-model:target="query.text" />
      </el-button-group>
    </el-form-item>
  </CardFrame>
</template>
