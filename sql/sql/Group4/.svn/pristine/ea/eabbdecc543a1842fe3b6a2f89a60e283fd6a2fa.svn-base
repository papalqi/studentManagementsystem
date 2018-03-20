$(function () {
 	var cookie = localStorage.cook;
 	var admin = localStorage.account;

 	$("#change_password_btn").click(function(){

 		var oldpassword = $("#old_password").val();
 		var newpassword = $("#new_password").val();
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/ChangeTeacherPassword?cookie="+cookie+"&username="+admin+"&oldpassword="+oldpassword+"&newpassword="+newpassword,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	alert("修改成功");
//                  	console.log("http://192.168.1.187:8080/studentgrade/InsertStudent?cookie="+cookie+"&stuid="+stuid+"&stuname="+stuname+"&classid="+classid);
                    }else if(data.status == 400){
                    	alert("错误");
                    }else if(data.status == 408){
                    	console.log("超时");
                    }
                },
                error: function (err) {
                    console.log("情况异常!");
                }
           });
 	});
           
   });