import { defineStore } from "pinia";

type Query = {
  text: string;
  type: string;
  key: string;
  ref: string;
  decode: boolean;
};

export const useCodexStore = defineStore("codex", {
  state: () => ({
    text: "哦牛",
    type: "nmsl",
    key: "",
    ref: "曼波",
    decode: false,
  }),
  actions: {
    update(query: Query) {
      this.text = query.text;
      this.type = query.type;
      this.key = query.key;
      this.ref = query.ref;
      this.decode = query.decode;
    },
  },
  persist: true,
});
