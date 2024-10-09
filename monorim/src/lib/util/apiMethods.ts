import instance from "@/lib/util/apiHttp.ts";
import { ApiResponse } from "@/lib/type/typeApi.ts";

/**
 * Get Method
 * @param url
 * @param params
 */
async function apiGet(url: string, params?: any): Promise<ApiResponse> {
  return (await instance.get(url, params)).data as ApiResponse;
}

/**
 * Post Method
 * @param url
 * @param data
 */
async function apiPost(url: string, data?: any): Promise<ApiResponse> {
  return (await instance.post(url, data)).data as ApiResponse;
}

/**
 * Put Method
 * @param url
 * @param data
 */
async function apiPut(url: string, data?: any): Promise<ApiResponse> {
  return (await instance.put(url, data)).data as ApiResponse;
}

/**
 * Delete Method
 * @param url
 */
async function apiDelete(url: string): Promise<ApiResponse> {
  return (await instance.delete(url)).data as ApiResponse;
}

/**
 * Unified post method
 * @param url
 * @param sArr
 * @param iArr
 * @param bArr
 */
async function uApiPost(url: string, sArr?: string[], iArr?: number[], bArr?: boolean[]): Promise<ApiResponse> {
  return (
    await instance.post(url, {
      sArr: sArr || [],
      iArr: iArr || [],
      bArr: bArr || [],
    })
  ).data as ApiResponse;
}

export { apiGet, apiPost, apiPut, apiDelete, uApiPost };
