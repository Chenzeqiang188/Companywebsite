function Clock() {
	var date = new Date();
	var top7 = document.getElementById("top7").value;
	var top8 = document.getElementById("top8").value;
	var top9 = document.getElementById("top9").value;
	var top10 = document.getElementById("top10").value;
	var top11 = document.getElementById("top11").value;
	var top12 = document.getElementById("top12").value;
	var top13 = document.getElementById("top13").value;
	var top14 = document.getElementById("top14").value;
	var top15 = document.getElementById("top15").value;
	var top16 = document.getElementById("top16").value;
	var top17 = document.getElementById("top17").value;
	this.year = date.getFullYear();
	this.month = date.getMonth() + 1;
	this.date = date.getDate();
	this.day = new Array(top17, top11, top12, top13, top14, top15, top16)[date.getDay()];
	this.hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
	this.minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
	this.second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
	this.toString = function() {
		return top7 + this.year + top8 + this.month + top9 + this.date + top10 +" "+ this.hour + ":" + this.minute + ":" + this.second + " " + this.day; 
	};
	
	this.toSimpleDate = function() {
		return this.year + "-" + this.month + "-" + this.date;
	};
	
	this.toDetailDate = function() {
		return this.year + "-" + this.month + "-" + this.date + " " + this.hour + ":" + this.minute + ":" + this.second;
	};
	
	this.display = function(ele) {
		var clock = new Clock();
		ele.innerHTML = clock.toString();
		window.setTimeout(function() {clock.display(ele);}, 1000);
	};
}