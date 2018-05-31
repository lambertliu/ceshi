function welfare(){
	var da=[];
	$.ajax({
		url:"dbwelfare",
		type:"get",
		async:false,
		dataType:"json",
		success:function(result){
			if(result.status==200){
				for(var i=0;i<result.data.length;i++){
					if(result.data[i].welfare==='不加班'){
						result.data[i].count/3;
					}
					var obj={
							name:result.data[i].welfare,
							weight:result.data[i].count
					}
					da.push(obj);
				}
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});
	return da;
}
var data = welfare();

Highcharts.chart('container', {
	series: [{
		type: 'wordcloud',
		data: data
	}],
	title: {
		text: '大数据职位福利待遇'
	}
});