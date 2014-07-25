/*
 * @function:将字符串str去掉首尾的空格
 * @param str:要去掉空格的字符串
 */
function trim(str){
	str = str.replace(/^\s+|\s+$/g, "");
	return str;
}

/*
 * @param str:要验证的邮箱
 * @return :返回true代表该邮箱格式正确 返回false代表邮箱格式不正确 
 * @function:验证邮箱格式是否正确
 */
function isEmail(str){
	var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	return pattern.test(str);
}