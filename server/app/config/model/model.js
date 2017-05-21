const mongoose = require('mongoose');

const FoodSchema = mongoose.Schema({
  time: Date,
  condition: Boolean,
  food: String,

});
module.exports =  mongoose.model('Food', FoodSchema);
