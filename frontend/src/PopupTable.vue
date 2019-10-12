<template>
  <div>
    주의: 가장 최신에 등록된 팝업페이지만 앱에 노출됩니다!
    <b-table-simple hover small>
      <b-thead>
        <b-tr>
          <b-th>이름</b-th>
          <b-th>url</b-th>
          <b-th>이미지</b-th>
          <b-th>등록일자</b-th>
          <b-th>옵션</b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <b-tr v-for="(popup, index) in sortedPopupDataList" v-bind:key="popup.id">
          <b-td>{{ popup.name }}</b-td>
          <b-td>{{ popup.url }}</b-td>
          <b-td><img class="popup-image" :src="$baseUrl + '/images?fileName=' + popup.imageUrl"></b-td>
          <b-td>{{ timeToString(popup.createdAt) }}</b-td>
          <b-td>
            <b-button @click="deletePopup(index)" variant="danger">삭제</b-button>
            <b-button @click="$emit('on-show-modify-url', service.urlDataList[index])">수정</b-button>
          </b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
    <b-button variant="primary" @click="$bvModal.show('create-popup-modal')">팝업 추가</b-button>
    <create-popup-modal :service="service"
      @on-create-popup="onCreatePopup"/>
  </div>
</template>

<script>
import CreatePopupModal from './CreatePopupModal'
import ModifyPopupModal from './CreatePopupModal'
export default {
  name: 'popupTable',
  components: {
    CreatePopupModal: CreatePopupModal
  },
  props: ['service'],
  computed: {
    sortedPopupDataList() {
      if (this.service.popupList == null)
        return []

      return this.service.popupList.sort((left, right) => {
        return left.createdAt - right.createdAt
      })
    }
  },
  methods: {
    onCreatePopup(popup) {
      this.service.popupList.push(popup)
    },
    timeToString(time) {
      let date = new Date(time)
      return `${date.getFullYear()}년 ${date.getMonth() + 1}월 ${date.getUTCDate()}일 ${date.getUTCHours()}시 ${date.getUTCMinutes()}분`
    },
    deletePopup(index) {
      this.$http.delete(`${this.$baseUrl}/popups/${this.sortedPopupDataList[index].id}`)
        .then(result => {
          if (result.status === 200) {
            for (var i = 0; i < this.service.popupList.length; i++) {
              if (this.service.popupList[i].id === this.sortedPopupDataList[index].id) {
                this.service.popupList.splice(i, 1)
              }
            }
          }
        })
    }
  },
  data() {
    return {
    }
  }
}
</script>

<style>
  .popup-image {
    max-width: 500px;
    max-height: 300px;
  }
</style>
