var express = require('express');
var router = express.Router();

var db_user = require('../public/SQL/user_sql')();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('유저123');
});

router.get('/name',function(req,res,next){
  db_user.select(function(err,result){
    if(err) console.log(err);
    else res.send(result);
  })
})

module.exports = router;
