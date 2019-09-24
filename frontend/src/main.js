import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import VueRouter from 'vue-router'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
import Main from './Main.vue'

Vue.use(BootstrapVue)
Vue.use(VueRouter)

Vue.prototype.$http = axios
Vue.prototype.$baseUrl = "http://localhost:1234/"

const routes = [
  {
    path: '/', component: Main
  }
]

const router = new VueRouter({
  routes
})

new Vue({
  router
}).$mount('#app')
