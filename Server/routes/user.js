var express = require('express');
var router = express.Router();

var db_user = require('../public/SQL/user_sql')();

router.get('/', function(req, res, next) {
  db_user.get_user(function(err,result){
    if(err) console.log(err);
    else res.send(result);
  })
});

router.post('/login', function(req,res,next){
  db_user.login(req.body.id,function(err,result){
    if(err) console.log(err);
    else {
      var data=result[0]
      res.send(data);
    }
  })
})

router.post('/check', function(req,res,next){
  db_user.check(req.body.id,function(err,result){
    if(err) console.log(err)
    else 
      res.send(result[0])
  })
})
router.post('/check/nickname', function(req,res,next){
  db_user.check_nickname(req.body.nickname,function(err,result){
    if(err) console.log(err)
    else 
      res.send(result[0])
  })
})

router.post('/', function(req,res,next){
  db_user.join(req.body.id,req.body.pw,req.body.name,req.body.birthday,req.body.gender,req.body.nickname,req.body.email,req.body.verified,req.body.university,req.body.grade,req.body.department,req.body.profile_image)
  res.send("success")
})
//상원

router.post('/find_pw',function(req,res,next){
  db_user.find_pw(req.body.id,function(err,result){
    if(err) console.log(err)
    else{
      res.send(result[0])
    }
  })
})

router.post('/find_id',function(req,res,next){ // 이메일로 id찾기
  db_user.find_id(req.body.email,function(err,result){
    if(err) console.log(err)
    else{
      res.send(result[0])
    }
  })
})

router.put('/update_user',function(req,res,next){ // 회원정보수정 (최신화)
  db_user.update_user(req.body.pw,function(err,result){
    if(err) console.log(err)
    else res.send(result[0])
  })
})

router.delete('/',function(req,res,next){ //회원정보 삭제 (최신화)
  db_user.delete_user(req.body.id)
  res.send('success');
})

//상원
module.exports = router;
