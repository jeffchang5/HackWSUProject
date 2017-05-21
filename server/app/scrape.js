/**
 * Created by jeffreychang on 5/20/17.
 */
const keys = require('./config/keys.js');
const Clarifai = require('clarifai');
const ClarifaiApp = new Clarifai.App(keys.clarifai_client_id, keys.clarifai_secret_id);


ClarifaiApp.models.initModel('health').then(function(model) {
  updateModel,
      function(err) {
        console.log('df')
    }
});

function updateModel(model) {
  model.mergeConcepts({"id": "hypoglecemia"}).then(
    function(response) {
      console.log('df')
      // do something with response
    },
    function(err) {
      console.log('df')
      // there was an error
    }
  );
}









// ClarifaiApp.models.create(
//   "health",
//   [
//     { "id": "diabetes" }
//   ]
// ).then(
//   function(response) {
//     console.log(response);
//     // do something with response
//   },
//   function(err) {
//     console.log(err);
//     // there was an error
//   }
// );

// let Scraper = require ('images-scraper')
//   , bing = new Scraper.Bing();

//   bing.list({
//     keyword: 'nuts',
//     num: 200,
//     detail: true
//   })
//   .then(function (res) {
//     res.forEach((x) => {
//       console.log(x.url);
//       ClarifaiApp.inputs.create({
//         url: x.url,
//         concepts: [
//           {
//             id: "hypoglecemia",
//             value: true
//           }
//         ]
//       })
//         .then(
//           function(response) {
//             console.log("good");
//           },
//           function(err) {
//             console.log('f');
//           }
//         );
//     })
//   }).catch(function(err) {
//       console.log('err bing');
// });

