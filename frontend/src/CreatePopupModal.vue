<template>
  <b-modal id="create-popup-modal" title="페이지 광고 생성" @ok="submit">
    <div>
      <b-input-group size="sm" prepend="이름">
        <b-form-input v-model="popupData.name"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="링크" class="mt-3">
        <b-form-input v-model="popupData.url"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="이미지" class="mt-3">
        <b-form-file accept="image/*" v-model="popupData.image"></b-form-file>
      </b-input-group>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: 'create-popup',
  props: ['service'],
  watch: {
    service() {
      this.popupData.serviceId = this.service.id
    }
  },
  data() {
    return {
      popupData: {
        id: 0,
        name: '',
        url: '',
        image: undefined,
        serviceId: 0,
        createdAt: new Date()
      }
    }
  },
  methods: {
    submit() {
      if (this.popupData.name === '') {
        alert('이름을 입력하세요')
        return
      }

      if (this.popupData.url === '') {
        alert('링크를 입력하세요')
        return
      }

      if (this.popupData.image === undefined) {
        alert('이미지를 첨부하세요')
        return
      }
      let formData = new FormData()
      formData.append('id', this.popupData.id)
      formData.append('name', this.popupData.name)
      formData.append('url', this.popupData.url)
      formData.append('image', this.popupData.image)
      formData.append('serviceId', this.popupData.serviceId)

      this.$http.post(`${this.$baseUrl}/popups/services/${this.service.id}`, formData)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-create-popup', result.data)
            this.$bvModal.hide('create-popup-modal')
            this.popupData.name = ''
            this.popupData.url = ''
            this.popupData.image = null
          }
        })
    }
  }
}
</script>
