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
  </div>
</template>

<script>
export default {
  name: 'manageUserTable',
  watch: {
    '$route'(to, from) {
      this.loadUserList();
    }
  },
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
      this.$http.get(`${this.$baseUrl}/users/services/${this.$route.params.serviceId}`)
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
