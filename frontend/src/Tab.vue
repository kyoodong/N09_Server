<template>
  <b-tab :title="title" active>
    <b-table-simple hover small>
      <b-thead>
        <b-tr>
          <b-th>이름</b-th>
          <b-th>url</b-th>
          <b-th>옵션</b-th>
        </b-tr>
      </b-thead>
      <b-tbody>
        <b-tr v-for="(data, index) in urlList" v-bind:key="data.id">
          <b-td>{{ data.name }}</b-td>
          <b-td>{{ data.url }}</b-td>
          <b-td>
            <b-button @click="deleteUrl(index)" variant="danger">삭제</b-button>
            <b-button @click="modifyUrl(index)">수정</b-button>
          </b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
    <b-button variant="danger" @click="deleteService">서비스 삭제</b-button>
  </b-tab>
</template>

<script>
export default {
  name: 'tab',
  props: ['title', 'urlList', 'id'],
  data () {
    return {
    }
  },
  methods: {
    deleteUrl(index) {

    },
    modifyUrl(index) {

    },
    deleteService() {
      this.$http.delete(`${this.$baseUrl}services/${this.id}`)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-delete-service', this.id)
          }
        })
    }
  }
}
</script>

<style>
</style>
