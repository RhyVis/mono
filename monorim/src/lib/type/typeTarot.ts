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

type DeckInfo = {
  name: string;
  loc: string;
  full: boolean;
};

export type { Card, CardDisplay, DeckInfo };
