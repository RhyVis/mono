import { defineStore } from "pinia";

export const useTarotStore = defineStore("tarot", {
  state: () => ({
    deck: "waite",
  }),
  persist: true,
});
