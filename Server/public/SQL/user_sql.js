var pool = require('../../config/db_config');

module.exports = function () {
    return {
        get_user: function (callback) {
            pool.getConnection(function (err, con) {
                var sql=`select * from user`;
                con.query(sql,function(err,result,fields){
                    con.release();
                    if(err) return;
                    else callback(null,result);
                })
            });
        },
        join: function(id,pw,name,birthday,gender,nickname,email,verified,university,grade,department,profile_image){
            pool.getConnection(function(err,con){
                var sql=`insert into user values('${id}','${pw}','${name}',${birthday},'${gender}','${nickname}','${email}','${verified}','${university}',${grade},'${department}',${profile_image})`
                con.query(sql, function(err,result,fields){
                    con.release();
                    if(err) console.log(err);
                    else console.log("회원가입 완료");
                })
            })
        }
        ,
        login: function(id,callback){
            pool.getConnection(function(err,con){
                var sql=`select * from user where ID='${id}'`
                con.query(sql, function(err,result,fields){
                    con.release();
                    console.log(result);
                    if(err) console.log(err);
                    else callback(null,result);
                })
            })
        }
        ,
        pool: pool
    }
};