var first;
var second;
var msg;
var sum;
function init()
{
	first = Math.floor(Math.random() * 9) + 1;
	second = Math.floor(Math.random() * 9) + 1;
	msg = 'Value of '+first+' + '+second+' =?';
	sum = first + second;

	document.getElementById('captcha1').placeholder=msg;

}
