$(function(){

	function typeName(){
		var tn = [];
		$.ajax({
			url:"number",
			type:"get",
			async:false,
			dataType:"json",
			success:function(result){
				if(result.status==200){
					for(var i =0;i<result.data.length;i++){
						tn[i]=result.data[i].type_name;
					}
				}
			},
			error:function(){
				alert("请求失败!");
			}
		});
		return tn;
	}

	function data(){
		var dt =[];
		$.ajax({
			url:"number",
			type:"get",
			async:false,
			dataType:"json",
			success:function(result){
				if(result.status==200){
					for(var i=0;i<result.data.length;i++){
						dt[i] = result.data[i].num;
					}
				}
			},
			error:function(){
				alert("请求失败!");
			}
		});
		return dt;
	}

	var typeName = typeName();
	var data = data();

//	function series(){
//		var se = [];
//		for(var i =0;i<typeName.length;i++){
//			var obj={
//					name:typeName[i],
//					value:data[i]
//			}
//			se.push(obj);
//		}
//		alert(se);
//		return se;
//	}
//	var series = series();





	var chart = Highcharts.chart('container',{
		chart: {
			type: 'column'
		},
		title: {
			text: 'IT各岗位市场需求人数'
		},
		subtitle: {
			text: '数据来自网络'
		},
		xAxis: {
			categories: ['北京','上海','广州','深圳','杭州','成都','重庆','武汉','苏州','西安','天津','南京','郑州','长沙','沈阳'],
			crosshair: true
		},
		yAxis: {
			min: 0,
			title: {
				text: '需求量 (个)'
			}
		},
		tooltip: {
			// head + 每个 point + footer 拼接成完整的 table
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			'<td style="padding:0"><b>{point.y:.1f} 个</b></td></tr>',
			footerFormat: '</table>',
			shared: true,
			useHTML: true
		},
		plotOptions: {
			column: {
				borderWidth: 0
			}
		},
		series:
			[{
				name:typeName[0],
				data:data[0]
			},
			{
				name:typeName[1],
				data:data[1]
			},
			{
				name:typeName[2],
				data:data[2]
			},
			{
				name:typeName[3],
				data:data[3]
			},
			{
				name:typeName[4],
				data:data[4]
			},
			{
				name:typeName[5],
				data:data[5]
			},
			{
				name:typeName[6],
				data:data[6]
			},
			{
				name:typeName[7],
				data:data[7]
			},
			{
				name:typeName[8],
				data:data[8]
			},
			{
				name:typeName[9],
				data:data[9]
			},
			{
				name:typeName[10],
				data:data[10]
			},
			{
				name:typeName[11],
				data:data[11]
			},
			{
				name:typeName[12],
				data:data[12]
			},
			{
				name:typeName[13],
				data:data[13]
			},
			{
				name:typeName[14],
				data:data[14]
			},
			{
				name:typeName[15],
				data:data[15]
			},
			{
				name:typeName[16],
				data:data[16]
			},
			{
				name:typeName[17],
				data:data[17]
			},
			{
				name:typeName[18],
				data:data[18]
			},
			{
				name:typeName[19],
				data:data[19]
			},
			{
				name:typeName[21],
				data:data[21]
			},
			{
				name:typeName[22],
				data:data[22]
			},
			{
				name:typeName[23],
				data:data[23]
			},
			{
				name:typeName[24],
				data:data[24]
			},
			{
				name:typeName[25],
				data:data[25]
			}]

	});
});