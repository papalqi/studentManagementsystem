$(function () {
		var cookie = localStorage.cook;
        $("#drop_outt").click(function(){  
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/Logout?cookie="+cookie,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("成功!")
                    	$(location).attr('href', 'login.html');
                    }else if(data.status == 200){
                    	alert("超时！")
          				$(location).attr('href', 'login.html');
                    }else{
                    	console.log("错误！")
                    }
                },
                error: function (err) {
                    console.log("异常");
                }
            });
        });
   });
