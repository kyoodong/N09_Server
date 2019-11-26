<template>
  <b-tab :title="service.name" active>
    <b-table-simple hover small>
      <b-thead>
        <b-tr>
          <b-th>이름</b-th>
          <b-th>url</b-th>
          <b-th>우선순위</b-th>
          <b-th>옵션</b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <b-tr v-for="(data, index) in sortedUrlDataList" v-bind:key="data.id">
          <b-td>{{ data.name }}</b-td>
          <b-td>{{ data.url }}</b-td>
          <b-td>{{ data.priority }}</b-td>
          <b-td>
            <b-button @click="deleteUrl(index)" variant="danger">삭제</b-button>
            <b-button @click="$emit('on-show-modify-url', service.urlDataList[index])">수정</b-button>
          </b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
    <b-button @click="$bvModal.show('create-url-modal')">URL 생성</b-button>
    <b-button @click="$bvModal.show('push-modal')">푸시 전송</b-button>
    <b-button @click="$bvModal.show('dev-push-modal')">푸시(개발용) 전송</b-button>

    <hr/>

    <manage-user-table :service="service"/>

    <hr/>

    <popup-table :service="service"></popup-table>

    <b-button variant="danger" @click="deleteService">서비스 삭제</b-button>
    <b-button variant="primary" @click="$emit('on-modify-service', service)">서비스 수정</b-button>
    <push-modal
      :service="service"
      @on-push-sent="onPushSent"></push-modal>

    <dev-push-modal
      :service="service"
      @on-dev-push-sent="onPushSent"></dev-push-modal>
  </b-tab>
</template>

<script>
import PushModal from './PushModal'
import DevPushModal from './DevPushModal'
import PopupTable from './PopupTable'
import ManageUserTable from './ManageUserTable'

export default {
  name: 'tab',
  components: {
    PushModal: PushModal,
    DevPushModal: DevPushModal,
    PopupTable: PopupTable,
    ManageUserTable: ManageUserTable
  },
  props: ['service'],
  computed: {
    sortedUrlDataList() {
      if (this.service.urlDataList == null)
        return []

      return this.service.urlDataList.sort((left, right) => {
        return left.priority - right.priority
      })
    }
  },
  methods: {
    deleteUrl(index) {
      this.$http.delete(`${this.$baseUrl}/urls/${this.sortedUrlDataList[index].id}`)
        .then(result => {
          if (result.status === 200) {
            const id = this.sortedUrlDataList[index].id
            this.service.urlDataList = this.service.urlDataList.filter(
              urlData => urlData.id !== id
            )
          }
        })
    },
    deleteService() {
      this.$http.delete(`${this.$baseUrl}/services/${this.service.id}`)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-delete-service', this.service.id)
          }
        })
    },
    onPushSent(result) {
      console.log(result)
    }
  },
  data() {
    return {
    }
  }
}
</script>

<style>
</style>
