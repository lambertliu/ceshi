var pageSize = 30;
$(function(){
	//点击分业组件的按钮进行翻页的方法
	$("#start").click(function(){
		page(1,pageSize);
	})
});

function page(currentPage,pageSize){
	alert("后台代码没有啊");
	
	//获取数据
	var position = $("#position").val();
	var city = $("#select option:selected").val();
	if(jobname==null || jobname==""){
		//服务不接受null和"";
		jobname="undefined";
	}

	$.ajax({
		url:"http://localhost:8089/select",
		type:"get",
		data:{"page":currentPage,"pos":position,"city":city,"rows":pageSize},
		dataType:"json",
		success:function(result){
			if(result.status==200){
				//闹到服务端返回的json数据
				var page = result.data;
				var data = page.data;

				//清空表格现有数据
				$(".bordered tbody").html("");

				//清空分页条
				$(".button gray").html("");

				//查到的数据给表格添加进去
				$(jobname).each(function(n,jName){
					var tr ='<tr>'+
					'<td>'+(n+1)+'</td>'+
					'<td><a id href="'+jName.url+'">'+
					jName.positionName+
					'</a></td>'+
					'<td>'+jName.city+'</td>'+
					'<td>'+jName.salary+'</td>'+
					'<td>'+jName.company+'</td>'+
					'<td>'+jName.date+'</td>'+
					'</tr>';
					$(".bordered tbody").append(tr);
				});
				
				//构建分业组件
				if(page.totalPage>1){
					//处理前一页
					var perviousLink='<button type="button" class="button black rarrow">'+
					'<a href="javascript:page('+page.previousPage+')">上一页</a>'+
					'</button>';
					
				}
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});
}