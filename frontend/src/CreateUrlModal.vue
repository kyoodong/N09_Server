<template>
  <b-modal id="create-url-modal" title="URL 생성" @ok="submit">
    <div>
      <b-input-group size="sm" prepend="이름">
        <b-form-input v-model="urlData.name"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="URL" class="mt-3">
        <b-form-input v-model="urlData.url"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="우선순위" class="mt-3">
        <b-form-input v-model="urlData.priority" type="number"></b-form-input>
      </b-input-group>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: 'create-url',
  props: ['service'],
  watch: {
    service() {
      this.urlData.serviceId = this.service.id
    }
  },
  data() {
    return {
      urlData: {
        id: 0,
        name: '',
        url: '',
        serviceId: 0,
        priority: 0
      }
    }
  },
  methods: {
    submit() {
      if (this.urlData.name === '') {
        alert('이름을 입력하세요')
        return
      }

      if (this.urlData.url === '') {
        alert('url을 입력하세요')
        return
      }

      if (this.urlData.priority === '') {
        alert('우선순위를 입력하세요')
        return
      }

      this.$http.post(`${this.$baseUrl}/urls`, this.urlData)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-create-url', result.data)
            this.$bvModal.hide('create-url-modal')
            this.urlData.name = ''
            this.urlData.url = ''
            this.urlData.priority = 0
          }
        })
    }
  }
}
</script>
