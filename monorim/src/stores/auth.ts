import { defineStore } from "pinia";
import { validateToken } from "@/lib/util/authColl.ts";

export const useAuthStore = defineStore("auth", {
  state: () => ({
    token: "",
    valid: false,
  }),
  getters: {
    async validateToken(state) {
      state.valid = await validateToken(state.token);
      return state.valid ? 1 : 2;
    },
  },
  actions: {
    updateToken(token: string) {
      this.token = token;
    },
  },
  persist: true,
});
