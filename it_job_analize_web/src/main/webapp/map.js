var mapChart = echarts.init(document.getElementById('map-wrap'));
//mapChart的配置（引入地图）
var option = {
		geo: {
			map: 'china'
		}
};
var geoCoordMap = {
		"北京": [116.41,39.91],
		"上海": [121.48,31.42],
		"广州": [113.23,23.16],
		"深圳": [114.06,22.61],
		"杭州": [120.20,30.26],
		"成都": [104.04,30.40],
		"重庆": [106.45,29.56],
		"武汉": [114.31,30.51],
		"苏州": [120.37,31.19],
		"西安": [108.57,34.17],
		"天津": [117.12,39.02],
		"南京": [118.46,32.03],
		"郑州": [113.65,34.76],
		"长沙": [113.00,28.21],
		"沈阳": [123.25,41.48],
}
;
var max = 170000,
min = 200; // todo 
var maxSize4Pin = 200,
minSize4Pin = 20;

function city(){
	var ci = [];
	$.ajax({
		url:"host/index",
		type:"get",
		async:false,
		dataType:"json",
		success:function(result){
			if(result.status==200){
				for(var i=0;i<result.data.length;i++){
					var obj={
							name:result.data[i].city,
							value:result.data[i].num
					}
					ci.push(obj);
				}
			}
		},
		error:function(){
			alert("请求失败!");
		}
	});
	return ci;
}

var data = city();

var convertData = function(data) {
	var res = [];
	for (var i = 0; i < data.length; i++) {
		var geoCoord = geoCoordMap[data[i].name];
		if (geoCoord) {
			res.push({
				name: data[i].name,
				value: geoCoord.concat(data[i].value)
			});
		}
	}
	return res;
};
var option = {
//		backgroundColor:'#FFFAFA',
		//地图修改
		visualMap: {
			show: false,
			min: 10,
			max: 500,
			left: 'left',
			top: 'bottom',
			text: ['高', '低'], // 文本，默认为数值文本
			calculable: true,
			seriesIndex: [1],
			inRange: {
				color: ['#3B5077', '#031525'] // 蓝黑
//color: ['#ffc0cb', '#800080'] // 红紫
//color: ['#3C3B3F', '#605C3C'] // 黑绿
//color: ['#0f0c29', '#302b63', '#24243e'] // 黑紫黑
//color: ['#23074d', '#cc5333'] // 紫红
//color: ['#00467F', '#A5CC82'] // 蓝绿
//color: ['#1488CC', '#2B32B2'] // 浅蓝
//color: ['#00467F', '#A5CC82'] // 蓝绿
//color: ['#00467F', '#A5CC82'] // 蓝绿
//color: ['#00467F', '#A5CC82'] // 蓝绿
//color: ['#00467F', '#A5CC82'] // 蓝绿

			}
		},
		legend: {
			orient: 'vertical',
			y: 'bottom',
			x: 'right',
			data: ['sell_area'],
			textStyle: {
				color: '#fff'
			}
		},
		tooltip: {
			trigger: 'item',
			formatter: function(params) {
				if (typeof(params.value)[2] == "undefined") {
					return params.name;
				} else {
					return params.name + ' : ' + params.value[2];
				}
			}
		},
		title: {
			text: '全国IT工作地点分布',
			subtext: '数据来自网络',
			x: 'center',
			textStyle: {
				color: '#000000'
			}
		},
		geo: {
			map: 'china',
			label: {
				normal: {
					show: false
				},
				emphasis: {
					show: false,
				}
			},
			roam: true,
			itemStyle: {
				normal: {
					areaColor: '#031525',
					borderColor: '#3B5077',
				},
				emphasis: {
					areaColor: '#2B91B7',
				}
			}
		},
		series: [{
			name: '工作人数',
			type: 'scatter',
			coordinateSystem: 'geo',
			data: convertData(data),
			symbolSize: function(val) {
				return val[2] / 5000;
			},
			label: {
				normal: {
					formatter: '{b}',
					position: 'right',
					show: true
				},
				emphasis: {
					show: true
				}
			},
			itemStyle: {
				normal: {
					color: '#05C3F9'
				}
			}
		},
		{
			type: 'map',
			map: 'zhongguo',
			geoIndex: 0,
			aspectScale: 0.75, //长宽比
			showLegendSymbol: false, // 存在legend时显示
			label: {
				normal: {
					show: false
				},
				emphasis: {
					show: false,
					textStyle: {
						color: '#fff'
					}
				}
			},
			roam: true,
			itemStyle: {
				normal: {
					areaColor: '#031525',
					borderColor: '#3B5077',
				},
				emphasis: {
					areaColor: '#2B91B7'
				}
			},
			animation: false,
			data: data
		},
		{
			name: '点',
			type: 'scatter',
			coordinateSystem: 'geo',
			symbol: 'pin',
			symbolSize: function(val) {
				var a = (maxSize4Pin - minSize4Pin) / (max - min);
				var b = minSize4Pin - a * min;
				b = maxSize4Pin - a * max;
				return a * val[2] + b;
			},
			label: {
				normal: {
					show: true,
					textStyle: {
						color: '#fff',
						fontSize: 9,
					}
				}
			},
			itemStyle: {
				normal: {
					color: '#F62157', //标志颜色
				}
			},
			zlevel: 6,
			data: convertData(data),
		},
		{
			name: 'Top 5',
			type: 'effectScatter',
			coordinateSystem: 'geo',
			data: convertData(data.sort(function(a, b) {
				return b.value - a.value;
			}).slice(0, 5)),
			symbolSize: function(val) {
				return val[2] / 5000;
			},
			showEffectOn: 'render',
			rippleEffect: {
				brushType: 'stroke'
			},
			hoverAnimation: true,
			label: {
				normal: {
					formatter: '{b}',
					position: 'right',
					show: false
				}
			},
			itemStyle: {
				normal: {
					color: '#05C3F9',
					shadowBlur: 10,
					shadowColor: '#05C3F9'
				}
			},
			zlevel: 1
		},

		]
};
mapChart.setOption(option);
window.onresize = mapChart.resize;