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
          <b-td>{{ popup.createdAt }}</b-td>
          <b-td>
            <b-button @click="deleteUrl(index)" variant="danger">삭제</b-button>
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
