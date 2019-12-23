<template>
  <div>
    <h3>회원리스트</h3>
    <b-form-input v-model="searchKeyword"></b-form-input>
    <b-table
      id="my-table"
      :items="filteredUserList"
      :fields="fields"
      small
    >
      <template v-slot:cell(index)="data">
        {{ data.index + 1 }}
      </template>
      <template v-slot:cell(etc)="data">
        <b-button variant="danger" @click="dropUser(data)">삭제</b-button>
      </template>
    </b-table>
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
    },
    dropUser(data) {
      let userId = data.item.id

      this.$http.delete(`${this.$baseUrl}/users?userId=${userId}`)
        .then(result => {
          if (result.status === 200) {
            this.userList = this.userList.filter(el => {
              return el.id != userId
            })
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
        key: 'index',
        label: '번호'
      },{
        key: 'name',
        label: '이름'
      }, {
        key: 'id',
        label: '번호'
      },{
        key: 'createdAt',
        label: '가입일자',
        formatter: value => {
          let date = new Date(value);
          return date.getUTCFullYear() + "." + (1 + date.getUTCMonth()) + "." + date.getDate()
        }
      }, {
        key: 'etc',
        label: '비고',
        colType: 'button'
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
