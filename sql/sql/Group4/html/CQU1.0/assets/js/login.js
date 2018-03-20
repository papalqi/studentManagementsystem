 $(function () {
        $("#btn").click(function(){
        	var account_string = 0;
        	account_string = $("input[type='tel']").val();
		   	var account = Number(account_string);
		   	var pw_string = 0;
		   	pw_string = $("input[type='password']").val();
		   	var pw = Number(pw_string);
		   	var identity = 0;
   			var identity_string = $("#identity_select input[type='radio']:checked").val();
   			identity = Number(identity_string);
 			var cook = 0;
 			
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/Login?username="+account+"&password="+pw+"&identity="+identity,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	alert("登录成功！");
                    	cook = data.cookie;
                    	localStorage.cook = cook;
                    	localStorage.account = account;
                    	localStorage.identity =  identity;
                    	if(identity == 1){
                    		 document.form1.action="admin_index.html?cookie=data.cookie";
                    		$(".am-form").submit();
                    	}else if(identity == 2){
	                    	document.form1.action="teacher_index.html?cookie=data.cookie";
	                    	$(".am-form").submit();
	                    }else if(identity == 3){
	                    	document.form1.action="student_index.html?cookie=data.cookie";
	                    		$(".am-form").submit();
	                    }else{
	                    	alert("帐号密码错误！");
	                    	return false;
	                    }
	                }else if(data.status == 400){
	                	alert("帐号密码错误");
	                }
                },
                error: function (err) {
//                  alert("帐号密码错误!");
                }
            });
        });
   });
