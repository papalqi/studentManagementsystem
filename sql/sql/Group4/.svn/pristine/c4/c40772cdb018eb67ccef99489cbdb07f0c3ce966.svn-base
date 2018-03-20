 $(function () {
 	var cookie = localStorage.cook;
 	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/OnlineNo?cookie="+cookie,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	$("#online_student").html(data.info.studentonlineno);
                    	$("#online_teacher").html(data.info.teacheronlineno);
                    	console.log("成功");
                    }else if(data.status == 400){
                    	console.log("错误");
                    }else if(data.status == 408){
                    	console.log("超时");
                    }
                },
                error: function (err) {
                    console.log("情况异常!");
                }
           });
           
   });
