import keys from 'config/keys.js';
import express from 'express';
import Clarifai from 'clarifai';
import multer from 'multer';

const ClarifaiApp = new Clarifai.App(
  keys.clarifai_client_id,
  keys.clarifai_secret_id,
);

const server = express();
const upload = multer({ dest: 'uploads/' });

server.post('/api/image', upload.single('food'), (req, res) => {

  console.log(req.file);
  // req.pipe(fs.createWriteStream(__dirname + req.url));

  res.end('OK!');
  // ClarifaiApp.models.predict(Clarifai.FOOD_MODEL, { base64: buffer }).then(
  //   (response) => {
  //     es.setHeader("content-type", "some/type");
  //   },
  //   (error) => {
  //     res.end(error);
  //   },
  // );
});
server.listen(8080);

console.log('Listening on 8080');
