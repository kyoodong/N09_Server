<template>
  <b-tab :title="service.name" active>
    <b-button @click="$bvModal.show('create-url-modal')">URL 생성</b-button>
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
    <b-button variant="danger" @click="deleteService">서비스 삭제</b-button>
    <b-button variant="primary" @click="$emit('on-modify-service', service)">서비스 수정</b-button>
  </b-tab>
</template>

<script>
export default {
  name: 'tab',
  props: ['service'],
  computed: {
    sortedUrlDataList() {
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
