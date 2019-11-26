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
            <b-button @click="modifyingPopupIndex = index, $bvModal.show('modify-popup-modal')">수정</b-button>
          </b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
    <b-button variant="primary" @click="$bvModal.show('create-popup-modal')">팝업 추가</b-button>
    <create-popup-modal :service="service"
      @on-create-popup="onCreatePopup"/>

    <modify-popup-modal :popup="sortedPopupDataList[modifyingPopupIndex]"
      @on-modify-popup="onModifyPopup"
      @on-modify-popup-image="onModifyPopupImage"/>
  </div>
</template>

<script>
import CreatePopupModal from './CreatePopupModal'
import ModifyPopupModal from './ModifyPopupModal'
export default {
  name: 'popupTable',
  components: {
    CreatePopupModal: CreatePopupModal,
    ModifyPopupModal: ModifyPopupModal
  },
  props: ['service'],
  computed: {
    sortedPopupDataList() {
      if (this.service.popupList == null)
        return []

      return this.service.popupList.sort((left, right) => {
        return right.createdAt - left.createdAt
      })
    }
  },
  methods: {
    onCreatePopup(popup) {
      this.service.popupList.push(popup)
    },
    onModifyPopup(popup) {
      this.$set(this.service.popupList, this.modifyingPopupIndex, popup)
    },
    onModifyPopupImage(id) {
      for (var i = 0; i < this.service.popupList.length; i++) {
        if (this.service.popupList[i].id === id) {
          // this.$set(this.service.popupList, i, this.service.popupList[i])
          let imageUrl = this.service.popupList[i].imageUrl
          this.service.popupList[i].imageUrl = ''
          this.$nextTick(() => {
            this.service.popupList[i].imageUrl = imageUrl
          })
          break
        }
      }
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
      modifyingPopupIndex: -1
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
