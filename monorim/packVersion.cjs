const fs = require("fs");

// Record build time
const version = {
  compileTime: new Date().getTime(),
};
fs.writeFile("./public/version.json", JSON.stringify(version), (err) => {
  if (err) {
    console.error(err);
  } else {
    console.log(`Version confirmed on ${new Date(version.compileTime)}`);
  }
});
