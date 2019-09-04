var express=require('express');
var router=express.Router();

var db_department=require('../public/SQL/department_sql')()

router.post('/',function(req,res,next){

    var universityName=req.body[0].university_name
    var departmentName=req.body[0].department_name
    
    db_department.search_department(universityName,departmentName,function(err,result){
        if(err){
            console.log(err)
        }
        else{
            res.send(result)
        }
    })
})

module.exports=router;