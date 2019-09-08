var pool=require('../../config/db_config');

module.exports=function(){
    return {
        search_university: function(name,callback){
            pool.getConnection(function(err,con){
                var sql=`select * from university_list where university_name like '%${name}%'`
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