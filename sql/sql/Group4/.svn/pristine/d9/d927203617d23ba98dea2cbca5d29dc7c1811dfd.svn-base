$(function () {
	var cookie = localStorage.cook;
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectPassGrade?cookie="+cookie+"&context=",
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
                    		var content = "<tr><td><input type='checkbox'></td><td><a class='stuid_td'>"+val.scourseid+"</a></td><td>"+val.scoursename+"</td><td>"+val.scollegename+"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.sname+"</td><td class='am-hide-sm-only teacouid'>"+val.iteachclassid+"</td><td>已审核</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button></div></div></td></tr>";
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
            
            $(".am-icon-search").click(function(){
        	var keyword = $("#fuzzy_search").val();  
			$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectPassGrade?cookie="+cookie+"&context="+keyword,
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
                    		var content = "<tr><td><input type='checkbox'></td><td><a class='stuid_td'>"+val.scourseid+"</a></td><td>"+val.scoursename+"</td><td>"+val.scollegename+"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.sname+"</td><td class='am-hide-sm-only teacouid'>"+val.iteachclassid+"</td><td>已审核</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button></div></div></td></tr>";
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