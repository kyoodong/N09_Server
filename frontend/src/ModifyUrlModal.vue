<template>
  <b-modal id="modify-url-modal" title="URL 수정" @ok="submit">
    <div>
      <b-input-group size="sm" prepend="이름">
        <b-form-input v-model="modifyingUrlData.name"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="URL" class="mt-3">
        <b-form-input v-model="modifyingUrlData.url"></b-form-input>
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
        serviceId: 0
      }
    }
  },
  methods: {
    submit() {
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
