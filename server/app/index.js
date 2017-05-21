const keys = require('./config/keys.js');
const express = require('express');
const Clarifai = require('clarifai');
const multer = require('multer');
const FoodModel = require('./config/model/model.js');
const mongoose = require('mongoose');
const NutritionixClient = require('nutritionix');
const async = require('async');
const ClarifaiApp = new Clarifai.App(keys.clarifai_client_id, keys.clarifai_secret_id);
const nutritionix = new NutritionixClient({
  appId: '2c690ed5',
  appKey: '62bafd47f29b358da3534702923625c4'
  // debug: true, // defaults to false
});
const server = express();
mongoose.connect('mongodb://localhost/test');
const upload = multer({ storage: multer.memoryStorage() });
const db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', () => {
  server.post('/api/image', upload.single('food'), (req, res) => {
    ClarifaiApp.models.predict('health', {base64: req.file.buffer.toString('base64')}).then(

      (response) => {
        let condition = response.outputs[0].data.concepts[0].name;
        ClarifaiApp.models.predict(Clarifai.FOOD_MODEL, {base64: req.file.buffer.toString('base64')}).then(

          (response) => {
            let food = response.outputs[0].data.concepts[0].name;
            nutritionix.search({
              q: food,
              limit: 3,
              offset: 0,
              search_nutrient: 'calories'
            }).then((data) => {
                let b = true;
                if (condition === 'hypoglecemia') {
                   b = false;
                }
                const Food = new FoodModel(
                  {
                    time: new Date(),
                    condition: b,
                    food: food,
                  });
                Food.save((err) => {
                  if (err) console.log(err);
                  else {
                    console.log('Data saved!');
                    res.send({
                      condition: condition,
                      item: food,
                      calories: data.results[0].nutrient_value,

                    });
                  }
                });

              });

            }, (err) => {
              res.send("error");
            }).catch((err) => console.log(err));



          }

        );
      });








        //
        // async.map(response.outputs[0].data.concepts[0], nutritionixAsync, (error, data) => {
        //   if (error) res.send('Error');
        //   else {
        //     res.send(data);
        //
        //   }
        //
        //
        // });



  server.get('/api/image/getall', (req, res) => {
    FoodModel.find({}, function (err, user) {
      if (err) {
        res.send(err);
      }
      else {
        res.send(user);
      }
    } );
  });
  server.get('/api/info/:id', (req, res) => {
    FoodModel.findById(req.params.id, function (err, user) {
      if (err) {
        res.send(err);
      }
      else {
        res.send({
          food: user.food,
          date: user.time,
        });
      }
    } );
  });
});
server.listen(8080);

console.log('Listening on 8080');

