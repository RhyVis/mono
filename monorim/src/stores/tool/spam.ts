import { defineStore } from "pinia";

export const useSpamStore = defineStore("spam", {
  state: () => ({
    type: "spam_min",
    code: "none",
    limit: 1,
    tab: "spam",
  }),
  persist: true,
});
