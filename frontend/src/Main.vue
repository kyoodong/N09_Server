<template>
  <div>
    <h1>관리자 페이지</h1>
    <b-button @click="$bvModal.show('create-service-modal')">서비스 추가</b-button>
    <b-tabs content-class="mt-3">
      <tab
        :title="service.name"
        :urlList="service.urlList"
        :id="service.id"
        v-for="service in serviceList"
        v-bind:key="service.id"
        @on-delete-service="onDeleteService"
        ></tab>
    </b-tabs>

    <create-service-modal @on-create-service="onCreateService"></create-service-modal>
  </div>
</template>

<script>
import Tab from './Tab'
import CreateServiceModal from './CreateServiceModal'

export default {
  name: 'main-view',
  components: {
    tab: Tab,
    createServiceModal: CreateServiceModal
  },
  created() {
    this.fetchAllServices()
  },
  methods: {
    fetchAllServices() {
      this.$http.get(this.$baseUrl + 'services')
        .then(res => {
          if (res.status === 200) {
            this.serviceList = res.data
          }
        })
    },
    onCreateService(data) {
      this.serviceList.push(data)
    },
    onDeleteService(id) {
      this.serviceList = this.serviceList.filter(service => service.id !== id)
    }
  },
  data() {
    return {
      serviceList: [],
      service: {}
    }
  }
}
</script>

<style>
</style>
