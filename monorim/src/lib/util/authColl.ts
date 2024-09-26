import CryptoJS from "crypto-js";
import { apiPost } from "@/lib/util/apiMethods.ts";

const validateToken = async (token: string) => {
  if (token.length > 0) {
    try {
      let hashCode = CryptoJS.SHA256(token).toString(CryptoJS.enc.Hex);
      return (await apiPost("api/auth", { text: hashCode })).data as boolean;
    } catch (error) {
      console.error(error);
      return false;
    }
  } else {
    return false;
  }
};

export { validateToken };
