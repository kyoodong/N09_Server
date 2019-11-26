<template>
  <div>
    <h3>회원리스트</h3>
    <b-form-input v-model="searchKeyword"></b-form-input>
    <b-table
      id="my-table"
      :items="filteredUserList"
      :per-page="perPage"
      :current-page="currentPage"
      :fields="fields"
      small
    ></b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
    ></b-pagination>
  </div>
</template>

<script>
export default {
  name: 'manageUserTable',
  props: ['service'],
  computed: {
    filteredUserList() {
      if (this.userList == null)
        return [];

      if (this.searchKeyword === '') {
        return this.userList;
      }

      return this.userList.filter((el) => {
        return el.id.includes(this.searchKeyword);
      })
    },
    rows() {
      return this.filteredUserList.length;
    }
  },
  created() {
    this.loadUserList();
  },
  methods: {
    loadUserList() {
      this.$http.get(`${this.$baseUrl}/users/services/${this.service.id}`)
        .then(result => {
          if (result.status === 200) {
            this.userList = result.data;
          }
        })
    }
  },
  data() {
    return {
      userList: [],
      searchKeyword: '',
      currentPage: 1,
      perPage: 20,
      fields: [{
        key: 'name',
        label: '이름'
      }, {
        key: 'id',
        label: '번호'
      }]
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
