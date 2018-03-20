$(function () {
	var cookie = localStorage.cook;

	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectCourse?cookie="+cookie+"&context=",
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
                    		var n=i+1;
                    		var content = "<tr><td><input type='checkbox'></td><td><a class='couid_td'>"+val.scourseid+"</a></td><td>"+val.scoursename+"</td><td class='am-hide-sm-only'>"+val.scollegename +"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.ihours+"</td><td class='am-hide-sm-only'>"+val.icredit+"</td><td>"+val.inowscournumber+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o'></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 删除</button></div></div></td></tr>";
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
	
        $(".delete_butt").click(function(){
 			var couid = $(this).parents("tr").find(".couid_td").text();
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/DeleteCourse?cookie="+cookie+"&couid="+couid,
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
        	var couid = $(this).parents("tr").find(".couid_td").text();
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectCourse?cookie="+cookie+"&context="+couid,
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
						$.each(data.info, function(i,val){   
                    		localStorage.scoursename = val.scoursename;
							localStorage.scourseid  = val.scourseid ;
							localStorage.smajorname  = val.smajorname ;
							localStorage.scollegename  = val.scollegename ;
							localStorage.icredit = val.icredit;
							localStorage.iscournumber = val.iscournumber;
							localStorage.ihours = val.ihours;
							localStorage.inowscournumber = val.inowscournumber;
                    	
						  });
						document.course_manage_form.action="admin_course_view.html";
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
        	var couid = $(this).parents("tr").find(".couid_td").text();
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectCourse?cookie="+cookie+"&context="+couid,
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
						$.each(data.info, function(i,val){   
                    		localStorage.scoursename = val.scoursename;
							localStorage.scourseid  = val.scourseid ;
							localStorage.smajorname  = val.smajorname ;
							localStorage.scollegename  = val.scollegename ;
							localStorage.icredit = val.icredit;
							localStorage.iscournumber = val.iscournumber;
							localStorage.ihours = val.ihours;
							localStorage.inowscournumber = val.inowscournumber;
                    	
						  });
                    	document.course_manage_form.action="admin_course_change.html";
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
                url:"http://192.168.1.187:8080/studentgrade/SelectCourse?cookie="+cookie+"&context="+keyword,
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
                    		
                    		var content = "<tr><td><input type='checkbox'></td><td><a class='couid_td'>"+val.scourseid+"</a></td><td>"+val.scoursename+"</td><td class='am-hide-sm-only'>"+val.scollegename +"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.ihours+"</td><td class='am-hide-sm-only'>"+val.icredit+"</td><td>"+val.inowscournumber+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o'></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 删除</button></div></div></td></tr>";
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