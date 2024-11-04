

var chartDataStr = decodeHtml(chartData);
var chartJsonArray = JSON.parse(chartDataStr);

var arrayLength = chartJsonArray.length;

var numericData = [];
var labelData = [];

for(var i=0; i < arrayLength; i++){
	numericData[i] = chartJsonArray[i].val;
	labelData[i] = chartJsonArray[i].label;
}

//labelData[0] = 'fff'
//labelData[1] = 'ggg'
//numericData[0] = '10'
//numericData[1] = '20'
new Chart(document.getElementById("myPieChart"), {
	type: 'pie',
	data: {
	      labels: labelData,
	      datasets: [{
	        label: '# of Votes',
	        data: numericData,
	        borderWidth: 1
	      }]
	    },
	    options: {
	      scales: {
	        y: {
	          beginAtZero: true
	        }
	      }
	    }
});



// "[{"val": 1, "label": "COMPLETED"},{"val": 2, "label": "INPROGRESS"},{"val": 1, "label": "NOTSTARTED"}]"
function decodeHtml(html){
	var txt = document.createElement("textarea");
	txt.innerHTML = html;
	return txt.value;
}