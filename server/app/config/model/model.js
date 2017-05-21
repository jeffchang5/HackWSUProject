const mongoose = require('mongoose');

const FoodSchema = mongoose.Schema({
  time: Date,
  condition: Boolean,
  food: String,
  calories: Number,

});
module.exports =  mongoose.model('Food', FoodSchema);
