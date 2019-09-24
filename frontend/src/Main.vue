<template>
  <div>
    <h1>관리자 페이지</h1>
    <b-button @click="$bvModal.show('create-service-modal')">서비스 추가</b-button>
    <b-tabs content-class="mt-3">
      <tab
        :service="service"
        :urlList="service.urlList"
        v-for="service in serviceList"
        v-bind:key="service.id"
        @click="onClickTab(service)"
        @on-delete-service="onDeleteService"
        @on-modify-service="onShowModifyService"
        @on-show-modify-url="onShowModifyUrlData"
        ></tab>
    </b-tabs>

    <create-service-modal @on-create-service="onCreateService"/>
    <modify-service-modal
      :service="service"
      @on-modify-service="onModifyService"/>
    <create-url-modal
      :service="service"
      @on-create-url="onCreateUrl"/>
    <modify-url-modal
      :urlData="urlData"
      @on-modify-url="onModifyUrl"></modify-url-modal>
  </div>
</template>

<script>
import Tab from './Tab'
import CreateServiceModal from './CreateServiceModal'
import ModifyServiceModal from './ModifyServiceModal'
import CreateUrlModal from './CreateUrlModal'
import ModifyUrlModal from './ModifyUrlModal'

export default {
  name: 'main-view',
  components: {
    tab: Tab,
    createServiceModal: CreateServiceModal,
    modifyServiceModal: ModifyServiceModal,
    createUrlModal: CreateUrlModal,
    modifyUrlModal: ModifyUrlModal
  },
  created() {
    this.fetchAllServices()
  },
  methods: {
    fetchAllServices() {
      this.$http.get(`${this.$baseUrl}/services`)
        .then(res => {
          if (res.status === 200) {
            this.serviceList = res.data
            if (this.serviceList != null && this.serviceList.length > 0) {
              this.service = JSON.parse(JSON.stringify(this.serviceList[0]))
            }
          }
        })
    },
    onCreateService(data) {
      this.serviceList.push(data)
    },
    onModifyService(data) {
      for (var i = 0; i < this.serviceList.length; i++) {
        if (this.serviceList[i].id === data.id) {
          this.$set(this.serviceList, i, data)
          break
        }
      }
    },
    onShowModifyUrlData(urlData) {
      this.urlData = urlData
      this.$bvModal.show('modify-url-modal')
    },
    onDeleteService(id) {
      this.serviceList = this.serviceList.filter(service => service.id !== id)
    },
    onShowModifyService(service) {
      this.$bvModal.show('modify-service-modal')
    },
    onClickTab(service) {
      this.service = JSON.parse(JSON.stringify(service))
    },
    onCreateUrl(urlData) {
      for (var i = 0; i < this.serviceList.length; i++) {
        if (this.serviceList[i].id === urlData.serviceId) {
          if (this.serviceList[i].serviceList == null)
            this.serviceList[i].serviceList = []

          this.serviceList[i].urlDataList.push(urlData)
          break
        }
      }
    },
    onModifyUrl(urlData) {
      for (var i = 0; i < this.serviceList.length; i++) {
        if (this.serviceList[i].id === urlData.serviceId) {
          for (var j = 0; j < this.serviceList[i].urlDataList.length; j++) {
            if (this.serviceList[i].urlDataList[j].id === urlData.id) {
              this.$set(this.serviceList[i].urlDataList, j, urlData)
            }
          }
          break
        }
      }
    }
  },
  data() {
    return {
      serviceList: [],
      service: {},
      urlData: {}
    }
  }
}
</script>

<style>
</style>
