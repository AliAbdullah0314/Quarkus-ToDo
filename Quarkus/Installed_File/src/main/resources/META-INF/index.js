import axios from "/node_modules/axios/lib"; //"/node_modules/axios"




const app = createApp({
    data() {
      return {
        items: [],
      };
    },
    mounted() {
      this.fetchData();
    },
    methods: {
      fetchData() {
        axios.get('http://localhost:8080/to-do-list/persons')
          .then(response => {
            this.items = response.data;
            console.log(response.data);
          })
          .catch(error => {
            console.error(error);
          });
      },
    },
  });
  
  app.mount('#app');
  


  