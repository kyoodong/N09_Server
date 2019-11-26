import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import VueRouter from 'vue-router'
import Vuex from 'vuex'
import VueCookies from 'vue-cookies'
import SHA512 from 'js-sha512'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import axios from 'axios'
import App from './App.vue'
import Main from './Main.vue'
import Login from './Login.vue'

Vue.use(BootstrapVue)
Vue.use(VueRouter)
Vue.use(Vuex)
Vue.use(VueCookies)
// Vue.use(RSAJSEncrypt)

VueCookies.config('7d')

axios.defaults.withCredentials = true
Vue.prototype.$http = axios
Vue.prototype.$baseUrl = "http://localhost:1234"
// Vue.prototype.$baseUrl = ""
Vue.prototype.$sha512 = SHA512

const store = new Vuex.Store({
  state: {
    token: VueCookies.get('Auth-token')
    // token: ''
  },
  mutations: {
    logout (state) {
      state.token = ''
    },
    setToken (state, token) {
      VueCookies.set('Auth-token', token)
      state.token = token
      Vue.prototype.$http.defaults.headers.common['Auth-Token'] = store.state.token
    }
  }
})

Vue.prototype.$http.defaults.headers.common['Auth-Token'] = store.state.token
Vue.prototype.$store = store

const routes = [
  {
    name: 'main', path: '/', component: Main
  },
  {
    name: 'login', path: '/login', component: Login
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path !== '/login') {
    if (store.state.token) {
      next()
    } else {
      router.push({
        path: 'login'
      })
    }
  } else {
    next()
  }
})

axios.interceptors.response.use(
  response => response,
  (error) => {
    if (error.response != null && error.response.status === 401) {
      router.replace({
        name: 'login'
      })
    }
  }
)

new Vue({
  el: '#app',
  router,
  template: '<App></App>',
  components: {
    App
  }
})
