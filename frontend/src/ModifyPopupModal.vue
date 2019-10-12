<template>
  <b-modal id="modify-popup-modal" title="페이지 광고 생성" @ok="submit">
    <div>
      <b-input-group size="sm" prepend="이름">
        <b-form-input v-model="popupData.name"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="링크" class="mt-3">
        <b-form-input v-model="popupData.url"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="이미지" class="mt-3">
        <b-form-file accept="image/*" v-model="popupData.image"
          :placeholder="popupData.imageUrl"></b-form-file>
      </b-input-group>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: 'modify-popup',
  props: ['popup'],
  watch: {
    popup() {
      this.popupData = JSON.parse(JSON.stringify(this.popup))
    }
  },
  data() {
    return {
      popupData: {}
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

      var popupData = this.popupData
      if (this.popupData.image != null) {
        let formData = new FormData()
        formData.append('fileName', this.popupData.imageUrl)
        formData.append('imageFile', this.popupData.image)

        this.$http.post(`${this.$baseUrl}/images`, formData)
          .then(result => {
            if (result.status === 200) {
              this.$emit('on-modify-popup-image', popupData.id)
            }
          })
      }

      this.$http.put(`${this.$baseUrl}/popups/${this.popupData.id}`, this.popupData)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-modify-popup', result.data)
            this.$bvModal.hide('modify-popup-modal')
          }
        })
    }
  }
}
</script>
