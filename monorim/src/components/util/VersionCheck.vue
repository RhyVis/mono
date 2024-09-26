<script setup lang="ts">
import axios from "axios";
import moment from "moment";
import versionJson from "@/../public/version.json";
import { ref, computed, onMounted } from "vue";

interface State {
  type: string;
  value: string;
}

const versionState = ref(0);
const versionLocal = versionJson.compileTime;
const versionLocalF = moment(new Date(versionLocal)).format("YYYY-MM-DD (HH:mm:ss)");

const versionRemote = ref(0);
const versionRemoteF = computed<State>(() => {
  switch (versionState.value) {
    case 1:
      return {
        type: "success",
        value: `${moment(new Date(versionRemote.value)).format("YYYY/MM/DD (HH:mm:ss)")} 最新版本`,
      };
    case 2:
      return {
        type: "warning",
        value: `${moment(new Date(versionRemote.value)).format("YYYY/MM/DD (HH:mm:ss)")} 点击刷新`,
      };
    case -1:
      return {
        type: "danger",
        value: "版本获取失败",
      };
    default:
      return {
        type: "info",
        value: "等待版本获取",
      };
  }
});

const update = () => {
  if (versionState.value != 1) {
    location.reload();
  }
};

onMounted(async () => {
  try {
    const versionFetch = (await axios.get("/version.json")).data.compileTime as number;
    if (versionFetch != 0) {
      versionRemote.value = versionFetch;
      console.log(`Remote version: ${versionRemote.value} | Local version: ${versionLocalF}`);
      if (versionLocal != versionFetch) {
        versionState.value = 2;
      } else {
        versionState.value = 1;
      }
    } else {
      versionState.value = -1;
    }
  } catch (e) {
    console.error(e);
  }
});
</script>

<template>
  <div @click="update">
    <el-tag style="user-select: none" :type="versionRemoteF.type">
      {{ versionRemoteF.value }}
    </el-tag>
  </div>
</template>
