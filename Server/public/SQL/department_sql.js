var pool=require('../../config/db_config');

module.exports=function(){
    return {
        search_department: function(universityName,departmentName,callback){
            pool.getConnection(function(err,con){
                var sql=`select * from department_list where university_name='${universityName}' AND department_name like '%${departmentName}%'`
                con.query(sql,function(err,result){
                    con.release();
                    if(err) callback(err);
                    else callback(null,result);
                })
            })
        },
        pool:pool
    }
}