<template>
  <b-tabs content-class="mt-3">
    <tab :title="'N09'"></tab>
  </b-tabs>
</template>

<script>
  import Tab from './Tab'
export default {
  name: 'main-view',
  components: {
    tab: Tab
  },
  data () {
    return {
    }
  }
}
</script>

<style>
</style>
