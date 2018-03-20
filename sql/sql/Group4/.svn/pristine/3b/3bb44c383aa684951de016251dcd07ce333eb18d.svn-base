$(function () {
	var cookie = localStorage.cook;
	
	
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/GetYHFK?cookie="+cookie,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("显示成功！");
                    	
                    	$.each(data.info, function(i,val){   
                    		n=n+1;
                    		if(n<9){
                    		var content = "<tr><td><a class='yid_td'>"+val.yid+"</a></td><td>"+val.sname+"</td><td class='am-hide-sm-only'>"+val.message+"</td><td class='am-hide-sm-only'>"+val.times+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button></div></div></td></tr>";
						    $(".am-table tbody").append(content);
						    }else{
						    	return false;
						    }
						  }); 
	                }else if(data.status == 400){
	                	console.log("显示失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
            
            $(".view_butt").click(function(){
            	document.user_back_form.action="admin_user_back_view.html";
                $(".am-form").submit();
            });
    });