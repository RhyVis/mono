import { defineConfig } from 'vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'
import fs from 'fs'

// Record build time
const version = {
  compileTime: new Date().getTime(),
}
fs.writeFile("./public/version.json", JSON.stringify(version), (err) => {
  if (err) {
    console.error(err)
  } else {
    console.log(`Version confirmed on ${new Date(version.compileTime)}`)
  }
})

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
        '@': path.resolve(__dirname, 'src')
    }
  }
})
