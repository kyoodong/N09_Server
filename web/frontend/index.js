import Main from './vue/main.vue'

const routes = [
    { path: '/', component: Main }
];

const router = new VueRouter({
    routes
});

const vm = new Vue({
    router
}).$mount('#app');
