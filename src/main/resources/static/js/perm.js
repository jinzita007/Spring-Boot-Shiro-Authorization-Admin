/**
 * 请求菜单接口
 * @param callback
 */
    function getPerm() {
        $.ajax({
            url: 'http://localhost:8080/sys/permission/permlist',
            type: 'GET',
            data: {},
            success: function (result) {
                if (result.code == 200) {
                    list = result.data;
                    /**
                     * 无限树形菜单
                     */
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


                }
            },
            error: function () {
                console.log("error");
            }
        })
    }

/**
 * 请求新增资源
 */
function savePerm(){
    var data = {
        pid: $("#permForm #selectbox").val(),
        name: $("#permForm input[name='name']").val(),
        url: $("#permForm input[name='url']").val(),
        code: $("#permForm input[name='code']").val(),
        ismenu: $("#permForm input[name='ismenu']").val(),
        status: $("#permForm input[name='status']").val(),

    }
    $.ajax({
        type: "POST",
        url:"add",
        data:data,
        success: function(data) {
            if(data.code == 201){
                console.log(data);
                layer.msg(data.msg);
                $('#table').bootstrapTable("refresh");
                $('#permModal').modal('hide');
            }
        }
    });
    console.log(data);
}
