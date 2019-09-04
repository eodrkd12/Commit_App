var express = require('express');
var router = express.Router();

var db_user = require('../public/SQL/user_sql')();

//==========인승==========
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
//==========인승==========

//==========세현==========
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
  db_user.join(req.body.id, req.body.pw, req.body.name, req.body.birthday, req.body.gender
    , req.body.nickname, req.body.web_mail, req.body.university_name, req.body.enter_year, req.body.department_name
    , null//null 자리에는 프로필 사진
    , function(err,result){
      if(err){
        console.log(err)
        var send=new Object
        send.result="fail"
        res.send(send)
      }
      else{
        console.log(result)
        var send=new Object
        send.result="success"
        res.send(send)
      }
    })
})

//==========세현==========

//==========상원==========
router.put('/',function(req,res,next){ // 상원 회원정보수정 (최신화)
  db_user.update_user(req.body.pw,req.body.nickname)
  res.send('success');
})

router.delete('/',function(req,res,next){ // 상원 회원정보 삭제 (최신화)
  db_user.delete_user(req.body.id)
  res.send('success');
})
//==========상원==========

module.exports = router;
