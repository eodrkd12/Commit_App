var express = require('express');
var router = express.Router();

var db_user = require('../public/SQL/user_sql')();

router.get('/', function(req, res, next) {
  db_user.get_user(function(err,result){
    if(err) console.log(err);
    else res.send(result);
  })
});

router.post('/', function(req,res,next){
  db_user.join(req.body.id,req.body.pw,req.body.name,req.body.birthday,req.body.gender,req.body.nickname,req.body.email,req.body.verified,req.body.university,req.body.grade,req.body.department,req.body.profile_image)
  res.send("회원가입 요청 전송")
})

module.exports = router;