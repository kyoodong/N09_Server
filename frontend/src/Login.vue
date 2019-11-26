<template>
  <div>
    <h1>로그인</h1>
    <b-input-group size="sm" prepend="패스워드">
      <b-form-input type="password" v-model="password"></b-form-input>
    </b-input-group>
    <b-button @click="login">로그인</b-button>
  </div>
</template>

<script>
export default {
  name: 'login-view',
  data() {
    return {
      password: ''
    }
  },
  methods: {
    login() {
      let binary = '';
      let bytes = new Uint8Array(this.$sha512.digest(this.password));
      let len = bytes.byteLength;
      for (let i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i]);
      }
      let encryptedPassword = encodeURIComponent(btoa(binary))
      console.log(encryptedPassword)

      this.$http.get(`${this.$baseUrl}/admins/login?password=${encryptedPassword}`)
        .then(result => {
          if (result.status === 200) {
            if (result.data.length > 0) {
              this.$store.commit('setToken', result.data)
              this.$router.replace({
                name: 'main'
              }).catch(error => {
                console.log(error)
              })
            } else {
              alert('비밀번호가 틀렸습니다.')
            }
          } else {
            alert('비밀번호가 틀렸습니다.')
          }
        })
    }
  }
}
</script>

<style>
</style>
