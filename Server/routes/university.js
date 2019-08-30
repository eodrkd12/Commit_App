var express=require('express');
var router=express.Router();

var db_university=require('../public/SQL/university_sql')()

router.post('/',function(req,res,next){
    var name=req.body[0].name
    db_university.search_university(name,function(err,result){
        if(err){
            console.log(err)
        }
        else{
            res.send(result)
        }
    })
})

module.exports=router;