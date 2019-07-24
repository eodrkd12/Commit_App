var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Title' });
});

router.get('/test',function(req,res,next){
  res.send('hi')
})

module.exports = router;
