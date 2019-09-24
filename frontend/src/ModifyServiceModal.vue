<template>
  <b-modal id="modify-service-modal" title="서비스 생성" @ok="submit">
    <div>
      <b-input-group size="sm" prepend="이름">
        <b-form-input v-model="modifyingService.name"></b-form-input>
      </b-input-group>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: 'modify-service',
  props: ['service'],
  watch: {
    service() {
      this.modifyingService = JSON.parse(JSON.stringify(this.service))
      console.log(this.modifyingService)
    }
  },
  data() {
    return {
      modifyingService: {}
    }
  },
  methods: {
    submit() {
      this.$http.put(`${this.$baseUrl}services/${this.service.id}`, this.modifyingService)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-modify-service', result.data)
            this.$bvModal.hide('modify-service-modal')
          }
        })
    }
  }
}
</script>
