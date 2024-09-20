type Card = {
  index: number;
  name: string;
  loc: string;
  img: string;
  rev: boolean;
  desc: {
    upright: string;
    reverse: string;
    desc: string[];
  };
};

type CardDisplay = {
  data: Card;
  showImg: boolean;
  showDesc: boolean;
};

export type { Card, CardDisplay };
