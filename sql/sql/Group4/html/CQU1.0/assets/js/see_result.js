$(function () {
	var cookie = localStorage.cook;
	vae teaid= account;
	week = 1;

	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectTeacherTableCourse?cookie="+cookie+"&teaid="+teaid,
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
                    		var content = "<tr><td><input type='checkbox'></td><td class='am-hide-sm-only'>"+n+"</td><td><a class='couid_td'>"+val.scourseid+"</a></td><td>"+val.scoursename+"</td><td class='am-hide-sm-only'>"+val.icredit+"</td><td class='am-hide-sm-only'>"+val.scollegename +"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o'></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 删除</button></div></div></td></tr>";
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