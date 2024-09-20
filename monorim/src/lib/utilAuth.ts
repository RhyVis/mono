import axios from "axios";
import CryptoJS from "crypto-js";

const validateToken = async (token: string) => {
  if (token.length > 0) {
    try {
      let hashCode = CryptoJS.SHA256(token).toString(CryptoJS.enc.Hex);
      return (await axios.post("api/auth", { text: hashCode })).data.result as boolean;
    } catch (error) {
      console.error(error);
      return false;
    }
  } else {
    return false;
  }
};

export { validateToken };
