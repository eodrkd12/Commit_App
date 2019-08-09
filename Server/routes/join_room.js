var express=require('express');
var router=express.Router();

var db_join_room=require('../public/SQL/join_room_sql')();

router.get('/date',function(req,res,next){
    db_join_room.get_join_room_date(function(err,result){
        if(err) console.log(err);
        else {
            console.log("데이팅 조회 성공");
            res.send(result);
        }
    })
})