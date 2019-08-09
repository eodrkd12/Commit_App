var pool=require('../../config/db_config');

module.exports=function(){
    return {
        get_join_room_date: function(callback){
            pool.getConnection(function(err,con){
                var sql=`select * from join_room where category='date'`;
                con.query(sql,function(err,result,field){
                    con.release();
                    if(err)return;
                    callback(null,result);
                })
            })
        },
        
        pool:pool
    }
}