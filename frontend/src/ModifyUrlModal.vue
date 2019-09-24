<template>
  <b-modal id="modify-url-modal" title="URL 수정" @ok="submit">
    <div>
      <b-input-group size="sm" prepend="이름">
        <b-form-input v-model="modifyingUrlData.name"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="URL" class="mt-3">
        <b-form-input v-model="modifyingUrlData.url"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="우선순위" class="mt-3">
        <b-form-input v-model="modifyingUrlData.priority" type="number"></b-form-input>
      </b-input-group>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: 'modify-url',
  props: ['urlData'],
  watch: {
    urlData() {
      this.modifyingUrlData = JSON.parse(JSON.stringify(this.urlData))
    }
  },
  data() {
    return {
      modifyingUrlData: {
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
      if (this.modifyingUrlData.name === '') {
        alert('이름을 입력하세요')
        return
      }

      if (this.modifyingUrlData.url === '') {
        alert('url을 입력하세요')
        return
      }

      if (this.modifyingUrlData.priority === '') {
        alert('우선순위를 입력하세요')
        return
      }

      this.$http.put(`${this.$baseUrl}/urls/${this.urlData.id}`, this.modifyingUrlData)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-modify-url', result.data)
            this.$bvModal.hide('modify-url-modal')
          }
        })
    }
  }
}
</script>
