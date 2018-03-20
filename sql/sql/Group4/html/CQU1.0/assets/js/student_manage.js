$(function () {
	var cookie = localStorage.cook;
	
	
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectStudent?cookie="+cookie+"&context=",
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	var n = 0;
                    	console.log("显示成功！");
                    	
    					localStorage.setItem("userInfo",JSON.stringify(data.info));
    					
                    	 
                    	$.each(data.info, function(i,val){   
                    		n=n+1;
                    		if(n<9){
                    		var content = "<tr><td><input type='checkbox'></td><td><a class='stuid_td'>"+val.stuid+"</a></td><td>"+val.stuname+"</td><td>"+val.stucollege+"</td><td class='am-hide-sm-only'>"+val.stumajor+"</td><td class='am-hide-sm-only'>"+val.stuclass+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o'></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 删除</button></div></div></td></tr>";
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
	
        $(".delete_butt").click(function(){
 			var stuid_string = $(this).parents("tr").find(".stuid_td").text();
 			var stuid = Number(stuid_string);
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/DeleteStudent?cookie="+cookie+"&stuid="+stuid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	alert("删除成功！");
                    	
	                }else if(data.status == 400){
	                	console.log("删除失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
        });
        
        $(".view_butt").click(function(){
        	var stuid_string = $(this).parents("tr").find(".stuid_td").text();
 			var stuid = Number(stuid_string);
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectOneStudent?cookie="+cookie+"&stuid="+stuid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("成功！");
//                  	console.log(data.stuname);
						localStorage.stuname = data.stuname;
						localStorage.stuid = data.stuid;
						localStorage.stucollege = data.stucollege;
						localStorage.stumajor = data.stumajor;
						localStorage.stuclass = data.stuclass;
                    	document.student_manange_form.action="admin_student_view.html";
                    	$(".am-form").submit();
                    	
	                }else if(data.status == 400){
	                	console.log("失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
        });
        
        $(".change_butt").click(function(){
        	var stuid_string = $(this).parents("tr").find(".stuid_td").text();
 			var stuid = Number(stuid_string);
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectOneStudent?cookie="+cookie+"&stuid="+stuid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("成功！");
//                  	console.log(data.stuname);
						localStorage.stuname = data.stuname;
						localStorage.stuid = data.stuid;
						localStorage.stucollege = data.stucollege;
						localStorage.stumajor = data.stumajor;
						localStorage.stuclass = data.stuclass;
                    	document.student_manange_form.action="admin_student_change.html";
                    	$(".am-form").submit();
                    	
	                }else if(data.status == 400){
	                	console.log("失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
        });
        
        
        $(".am-icon-search").click(function(){
        	var keyword = $("#fuzzy_search").val();  
			$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectStudent?cookie="+cookie+"&context="+keyword,
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
                    	$(".am-table tbody").empty();
                    	alert("搜索完成！");
                    	$.each(data.info, function(i,val){   
	
                    		var content = "<tr><td><input type='checkbox'></td><td><a class='stuid_td'>"+val.stuid+"</a></td><td>"+val.stuname+"</td><td>"+val.stucollege+"</td><td class='am-hide-sm-only'>"+val.stumajor+"</td><td class='am-hide-sm-only'>"+val.stuclass+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o'></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 删除</button></div></div></td></tr>";
						    $(".am-table tbody").append(content);
						  }); 
						  
	                }else if(data.status == 400){
	                	console.log("显示失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
        });
   });