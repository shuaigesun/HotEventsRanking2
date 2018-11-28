function first(page){
    $("[name=pageNo]").val(page);
    $("form").submit();
}

function last(page){
    $("[name=pageNo]").val(page);
    $("form").submit();
}

function prev(page){
    if(page<=1){
        alert("当前页已是首页");
        return false;
    }else{
        page--;
    }
    $("[name=pageNo]").val(page);
    $("form").submit();
}

function next(page){
    var pageTotal = $("[name=pageTotal]").val();
    if(page>=pageTotal){
        alert("当前页已是末页");
        return false;
    }else{
        page++;
    }
    $("[name=pageNo]").val(page);
    $("form").submit();
}
