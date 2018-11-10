/**
 * 请求菜单接口
 * @param callback
 */
function getPerm(callback) {
    $.ajax({
        url: 'http://localhost:8080/sys/permission/permlist',
        type: 'GET',
        async: true,
        data: {},
        success: function (result) {
            if (result.code == 200) {

                PermData = result.data;
                callback(PermData);
            }
        },
        error: function () {
            console.log("error");
        }
    })
}

/**
 * 无限树形菜单
 */
getPerm(function (list) {

    //转成树形json
    function getTree(list) {
        var map = {},
            node, roots = [],
            i;
        for (i = 0; i < list.length; i++) {
            if(list[i].ismenu == 1) {
                map[list[i].id] = i;
                list[i].children = [];
            }

        }
        for (i = 0; i < list.length; i++) {
            node = list[i];
            if(node.ismenu == 1) {
                if (node.pid !== 0) {
                    list[map[node.pid]].children.push(node);
                } else {
                    roots.push(node);
                }
            }


        }
        //返回结果
        return roots;
    }
    var tree = getTree(list);

    console.log(getTree(list));

    var selectbox = document.getElementById("selectbox");

    var j = "├";

    function SelectTree(d) {
        var htmls = '<select>';
        htmls += "<option value='0'>≡ 作为一级栏目 ≡</option>";
        for (var i = 0; i < d.length; i++) {
            htmls += "<option value='" + d[i].id + "'>" + j + d[i].name + "</option>";
            if (d[i].children.length) {
                j += "├";
                htmls += SelectTree2(d[i].children);
                j = j.slice(0, j.length - 1);
            }
            htmls += '</option>';
        }
        htmls += '</select>';
        return htmls;
    }

    function SelectTree2(d) {
        var htmls = "";
        for (var i = 0; i < d.length; i++) {
            htmls += "<option value='" + d[i].id + "'>" + j + d[i].name + "</option>";
            htmls += '</option>';
            if (d[i].children.length) {
                j += "├";
                htmls += SelectTree2(d[i].children);
                j = j.slice(0, j.length - 1);
            }
        }
        return htmls;
    }

    selectbox.innerHTML = SelectTree(tree);
});

/**
 * 请求新增资源
 */
function savePerm() {
    var form = $("#permForm").serialize();
    console.log(form);

    $.ajax({
        url: 'add',
        type: 'POST',
        cache: false,
        data: form,
        contentType: "application/x-www-form-urlencoded;charset=UTF-8",
        dataType: "json",
        success: function (result) {
            if (result.code == 200) {
                layer.msg('新增成功！');
                console.log(result);
                $('#permModal').modal('hide');
            }
        },
        error: function () {
            console.log("error");
        }
    })
}