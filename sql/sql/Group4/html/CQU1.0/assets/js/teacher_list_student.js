$(function () {
var cookie = localStorage.cook;

	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectTeacher?cookie="+cookie+"&context=",
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
                    	console.log("http://192.168.1.187:8080/studentgrade/SelectTeacher?cookie="+cookie+"&context=");
                    	$.each(data.info, function(i,val){   
                    		var n=i+1;
                    		var content = "<tr><td><input type='checkbox'></td><td><a class='teaid_td'>"+val.iteacherid+"</a></td><td>"+val.sname+"</td><td class='am-hide-sm-only'>"+val.scollegename+"</td><td class='am-hide-sm-only'>"+val.stitle+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button></div></div></td></tr>";

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
            
           
        
        $(".view_butt").click(function(){
        	var teaid_string = $(this).parents("tr").find(".teaid_td").text();
 			var teaid = Number(teaid_string);
            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectOneTeacher?cookie="+cookie+"&teaid="+teaid,
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
						localStorage.teaname = data.teaname;
						localStorage.teaid = data.teaid;
						localStorage.teacollege = data.teacollege;
						localStorage.teatitle = data.teatitle;
						localStorage.teaphoto = data.teaphoto;
						localStorage.teaphoneno = data.teaphoneno;
						localStorage.teamail = data.teamail;
                    	document.teacher_manange_form.action="student_view_teacher.html";
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
                url:"http://192.168.1.187:8080/studentgrade/SelectTeacher?cookie="+cookie+"&context="+keyword,
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
                    		var n=i+1;
                    		var content = "<tr><td><input type='checkbox'></td><td class='am-hide-sm-only'>"+n+"</td><td><a class='teaid_td'>"+val.iteacherid+"</a></td><td>"+val.sname+"</td><td class='am-hide-sm-only'>"+val.scollegename+"</td><td class='am-hide-sm-only'>"+val.stitle+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o '></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 删除</button></div></div></td></tr>";
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
            