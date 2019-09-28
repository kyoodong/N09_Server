import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import VueRouter from 'vue-router'
import Vuex from 'vuex'
import VueCookies from 'vue-cookies'
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

VueCookies.config('7d')

Vue.prototype.$http = axios
Vue.prototype.$baseUrl = "http://localhost:1234"

const store = new Vuex.Store({
  state: {
    token: VueCookies.get('Auth-token')
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
    console.log(store.state.token)
    if (store.state.token) {
      console.log('next')
      next()
    } else {
      console.log('login')
      router.push({
        path: 'login'
      })
    }
  } else {
    next()
  }
})

new Vue({
  el: '#app',
  router,
  template: '<App></App>',
  components: {
    App
  }
})
