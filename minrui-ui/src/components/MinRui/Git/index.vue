<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
export default {
  name: "MinRuiGit",
  data() {
    return {
      url: "https://gitee.com/xiashuangquan/MinRui-Vue",
    };
  },
  methods: {
    goto() {
      window.open(this.url);
    },
  },
};
</script>
