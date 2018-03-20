$(function () {
	var cookie = localStorage.cook;

	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectNoPClass?cookie="+cookie+"&context=",
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

                    	$.each(data.info, function(i,val){   
                    		n=n+1;
                    		if(n<9){
                    		var content = "<tr><td><a class='iclassid_td'>"+val.iclassid+"</a></td><td>"+val.sclassname+"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.scollegename+"</td><td class='am-hide-sm-only'>未排名</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o'></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 删除</button></div></div></td></tr>";
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
	
//      $(".delete_butt").click(function(){
// 			var stuid_string = $(this).parents("tr").find(".stuid_td").text();
// 			var stuid = Number(stuid_string);
//          $.ajax({
//              url:"http://192.168.1.187:8080/studentgrade/DeleteStudent?cookie="+cookie+"&stuid="+stuid,
//              type:"get",
//              xhrFields: {  
//                      withCredentials: true  
//                  }, 
//              crossDomain: true,
//              async:false,
//              dataType:"json",
//              success:function(data){
//                  if(data.status == 200){
//                  	alert("删除成功！");
//                  	
//	                }else if(data.status == 400){
//	                	console.log("删除失败");
//	                }
//              },
//              error: function (err) {
//                  console.log("超时!");
//              }
//          });
//      });
//      
//      
//      
        $(".rank_butt").click(function(){
        	var classid_string = $(this).parents("tr").find(".iclassid_td").text();
   			var classid = Number(classid_string);
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/RankClassGrade?cookie="+cookie+"&classid="+classid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("排名成功！");
//                  	console.log(data.stuname);
						
                    	
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
                url:"http://192.168.1.187:8080/studentgrade/SelectNoPClass?cookie="+cookie+"&context="+keyword,
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
                    		var content = "<tr><td><a class='iclassid_td'>"+val.iclassid+"</a></td><td>"+val.sclassname+"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.scollegename+"</td><td class='am-hide-sm-only'>未排名</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary rank_butt'><span class='am-icon-pencil-square-o'></span> 排名</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button></div></div></td></tr>";
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
        
        $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectYPClass?cookie="+cookie+"&context=",
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

                    	$.each(data.info, function(i,val){   
                    		n=n+1;
                    		if(n<9){
                    		var content = "<tr><td><a class='iclassid_td'>"+val.iclassid+"</a></td><td>"+val.sclassname+"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.scollegename+"</td><td class='am-hide-sm-only'>已排名</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button></div></div></td></tr>";
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
        	var classid_string = $(this).parents("tr").find(".iclassid_td").text();
   			var classid = Number(classid_string);
   			localStorage.classid = classid;
   			document.grade_rank_form.action="admin_grade_rank_state.html";
            $(".am-form").submit();
        });
   });