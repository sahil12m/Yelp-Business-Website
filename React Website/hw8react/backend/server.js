const axios = require('axios');
const express = require('express');
const cors = require('cors');
const path = require('path');

const apiKey =
  "b23jX2PQrdHtl6296Yg9O2ydKn5AUNqkEo1SNAZ86jlRzSa_KqtYzf_lfxjT5aE4PgfyK28a24tUqe1_5kbkS0Spaor-VVjl3gcc0zV_RJ-RAW-Z1mR9NNbpjCw1Y3Yx";

const app = express();
app.use(cors());
app.use(express.json());

app.use(express.static(path.resolve(__dirname,'build')));

app.get('/suggestion', (req, res) =>{
    const autocomplete = async () => {
        try{
          const keyword = req.query.keyword;
          await axios.get(`https://api.yelp.com/v3/autocomplete?text=${keyword}`,{
            headers: {
              Authorization: `Bearer ${apiKey}`
            }
          })
           .then((resp) => {
            console.log(resp.data)
            res.json(resp.data)
          })
          .catch((err) => {
            console.log (`${err}`)
          })
        }
        catch(error){
          console.log(error);
        }
    };
    autocomplete();
});

app.get('/search', (req, res) =>{
  const search = async () => {
      try{
        const keyword = req.query.keyword;
        const latitude = req.query.latitude;
        const longitude = req.query.longitude;
        const category = req.query.category;
        const radius = parseInt( req.query.radius) * 1609;
        await axios.get(`https://api.yelp.com/v3/businesses/search?term=${keyword}&latitude=${latitude}&longitude=${longitude}&categories=${category}&radius=${radius}&limit=10`,{
          headers: {
            Authorization: `Bearer ${apiKey}`
          }
        })
         .then((resp) => {
          res.json(resp.data)
        })
        .catch((err) => {
          console.log (`${err}`)
        })
      }
      catch(error){
        console.log(error);
      }
  };
  search();
});



app.get('/card', (req, res) =>{
  const card = async () => {
      try{
        const id = req.query.id;
        await axios.get(`https://api.yelp.com/v3/businesses/${id}`,{
          headers: {
            Authorization: `Bearer ${apiKey}`
          }
        })
         .then((resp) => {
          res.json(resp.data)
        })
        .catch((err) => {
          console.log (`${err}`)
        })
      }
      catch(error){
        console.log(error);
      }
  };
  card();
});

app.get('/review', (req, res) =>{
  const review = async () => {
      try{
        const id1 = req.query.id;
        await axios.get(`https://api.yelp.com/v3/businesses/${id1}/reviews`,{
          headers: {
            Authorization: `Bearer ${apiKey}`
          }
        })
         .then((resp) => {
          res.json(resp.data)
        })
        .catch((err) => {
          console.log (`${err}`)
        })
      }
      catch(error){
        console.log(error);
      }
  };
  review();
});

app.get('*', (req,res) => {
  res.sendFile(path.resolve(__dirname,'build','index.html'))
});

app.listen(4001);



