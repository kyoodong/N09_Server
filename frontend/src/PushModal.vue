<template>
  <b-modal id="push-modal" title="푸시 보내기" @ok="submit">
    <div>
      <b-input-group size="sm" prepend="타이틀">
        <b-form-input v-model="pushData.title"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="내용" class="mt-3">
        <b-form-input v-model="pushData.content"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="링크" class="mt-3">
        <b-form-input v-model="pushData.url"></b-form-input>
      </b-input-group>
      <b-input-group size="sm" prepend="이미지 URL(링크)" class="mt-3">
        <b-form-input v-model="pushData.imageUrl"></b-form-input>
      </b-input-group>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: 'push-modal',
  props: ['service'],
  watch: {
    service() {
      this.urlData.serviceId = this.service.id
    }
  },
  data() {
    return {
      pushData: {
        title: '',
        content: '',
        url: '',
        imageUrl: '',
        serviceId: this.service.id
      }
    }
  },
  methods: {
    submit() {
      if (this.pushData.title === '') {
        alert('타이틀을 입력하세요')
        return
      }

      if (this.pushData.content === '') {
        alert('내용을 입력하세요')
        return
      }

      if (this.pushData.url === '') {
        alert('링크를 입력하세요')
        return
      }

      this.$http.post(`${this.$baseUrl}/pushes`, this.pushData)
        .then(result => {
          if (result.status === 200) {
            this.$emit('on-push-sent', result.data)
            // this.$bvModal.hide('push-modal')
            // this.pushData.title = ''
            // this.pushData.content = ''
            // this.pushData.url = 0
          }
        })
    }
  }
}
</script>
