<template>
  <i-frame
    :src="url"
    style="position: absolute; top: 5px; left: 5px; width: 99%; height: 90%"
  />
</template>
<script>
import iFrame from "@/components/iFrame/index";
export default {
  name: "StudentReport",
  components: { iFrame },
  data() {
    return {
      url: this.jimureport + "/jmreport/view/825531250547613696", //9dbadaee8720767efe3164a7d018c870
      queryParams: {},
    };
  },
  created() {
    let name = "";
    let sex = "";
    let status = "";
    let str = "";
    this.queryParams = this.$route.query;
    // alert(JSON.stringify(this.queryParams))
    // if(this.queryParams.studentName!='' && this.queryParams.studentName!=null){
    //   name='name='+this.queryParams.studentName;
    // }

    // if(this.queryParams.studentSex!='' && this.queryParams.studentSex!=null){
    //   sex='sex='+this.queryParams.studentSex;
    // }

    // if(this.queryParams.studentStatus!='' && this.queryParams.studentStatus!=null){
    //   status='status='+this.queryParams.studentStatus;
    // }

    // if(name!='' || sex!='' || status!=''){
    //   if(name!=''){
    //     if(str!='') str+='&';
    //     str+=name
    //   }
    //   if(sex!=''){
    //     if(str!='') str+='&';
    //     str+=sex
    //   }
    //   if(status!=''){
    //     if(str!='') str+='&';
    //     str+=status
    //   }
    //   str = '?'+str;
    //   this.url+=str;
    // }
  },
};
</script>
