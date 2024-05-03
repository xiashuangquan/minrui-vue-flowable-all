<template>
  <i-frame
    :src="url"
    style="position: absolute; top: -40px; left: 0px; width: 100%; height: 100%"
  />
</template>
<script>
import iFrame from "@/components/iFrame/index";
export default {
  name: "design",
  components: { iFrame },
  data() {
    return {
      url: this.jimureport + "/jmreport/list",
    };
  },
  created() {},
};
</script>
