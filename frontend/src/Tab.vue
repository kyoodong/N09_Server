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
        <b-tr v-for="(data, index) in urlList">
          <b-td>{{ data.name }}</b-td>
          <b-td>{{ data.url }}</b-td>
          <b-td>
            <b-button @click="delete(index)" variant="danger">삭제</b-button>
            <b-button @click="modify(index)">수정</b-button>
          </b-td>
        </b-tr>
      </b-tbody>
    </b-table-simple>
  </b-tab>
</template>

<script>
export default {
  name: 'tab',
  props: ['title', 'urlList'],
  data () {
    return {
    }
  },
  methods: {
    delete(index) {

    },
    modify(index) {

    }
  }
}
</script>

<style>
</style>
