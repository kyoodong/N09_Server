<template>
  <b-modal id="create-service-modal" title="서비스 생성" @ok="submit">
    <div>
      <b-input-group size="sm" prepend="이름">
        <b-form-input v-model="name"></b-form-input>
      </b-input-group>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: 'create-service',
  data() {
    return {
      name: ''
    }
  },
  methods: {
    submit() {
      const data = {
        id: 0,
        name: this.name
      }
      this.$http.post(`${this.$baseUrl}/services`, data)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-create-service', result.data)
            this.$bvModal.hide('create-service-modal')
            this.name = ''
          }
        })
    }
  }
}
</script>
