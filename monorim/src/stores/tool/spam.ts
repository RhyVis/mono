import { defineStore } from "pinia";

type Query = {
  type: string;
  code: string;
  limit: number;
};

export const useSpamStore = defineStore("spam", {
  state: () => ({
    type: "spam_min",
    code: "none",
    limit: 1,
    tab: "spam",
  }),
  actions: {
    update(query: Query, tab: string) {
      this.type = query.type;
      this.code = query.code;
      this.limit = query.limit;
      this.tab = tab
    },
  },
  persist: true,
});
