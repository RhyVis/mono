type ApiResponse = {
  code: number;
  data: any;
};

class PostU {
  sArr!: string[];
  iArr!: number[];
  bArr!: boolean[];

  constructor(sArr: string[], iArr: number[], bArr: boolean[]) {
    this.sArr = sArr;
    this.iArr = iArr;
    this.bArr = bArr;
  }
}

export { PostU };
export type { ApiResponse };
