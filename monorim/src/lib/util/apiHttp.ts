import axios from "axios";
import type { AxiosRequestConfig, AxiosResponse, AxiosError } from "axios";

const instance = axios.create();

instance.interceptors.request.use(
  (config: AxiosRequestConfig): any => {
    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  (response: AxiosResponse) => {
    // 对响应数据进行处理
    return response;
  },
  (error: AxiosError) => {
    // 处理响应错误
    return Promise.reject(error);
  }
);

export default instance;
