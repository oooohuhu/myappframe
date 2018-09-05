//判断字段非空
function checkNotNull(str) {
    //str = trim(JSON.stringify(str)); // 转成字符串
    if (str == null || str == 'null' || str == '' || str == ' ' || str.length <= 0 || str == '""' || str == '"null"') {
        return false;
    } else {
        return true;
    }
}