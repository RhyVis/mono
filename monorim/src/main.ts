import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.js";
import { createApp } from "vue";
import store from "@/stores";
import router from "@/routers";
import App from "@/App.vue";

const app = createApp(App);
app.use(store);
app.use(router);
app.mount("#app");
