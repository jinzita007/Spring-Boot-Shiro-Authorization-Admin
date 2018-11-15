function getMenu() {
    $.ajax({
        url: "http://localhost:8080/menu",
        type: "get",
        async: false,
        dataType: "json",
        success: function (data) {
            var menu = null;
            var html = null;
            var chidLen = null;
            var child = null;
            for (var i = 0; i < data.menuList.length; i++) {
                menu = data.menuList[i];
                chidLen = menu.children.length;

                if (chidLen == 0) {
                    if(menu.ismenu == 1) {
                        if (i == 0) {
                            html = $('<li menu-id="' + i + '" class="active"></li>');
                        } else {
                            html = $('<li menu-id="' + i + '"></li>');
                        }

                        $(".sidebar .sidebar-menu").append(html);
                        html = $('<a href="' + menu.url + '" target="mainFrame"> <i class="fa fa-dashboard"></i><span>' + menu.name + '</span></a>');
                        $('[menu-id="' + i + '"]').append(html);
                    }

                }



                if (chidLen > 0) {
                    if(menu.ismenu == 1) {
                        if (i == 0) {
                            html = $(' <li menu-id="' + i + '" class="active treeview "></li>');
                        } else {
                            html = $(' <li menu-id="' + i + '" class="treeview "></li>');
                        }

                        $(".sidebar .sidebar-menu").append(html);

                        html = $('<a href="' + menu.url + '" target="mainFrame"> <i class="fa fa-dashboard"></i> <span>' + menu.name + '</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span> </a> <ul menuUl-id="' + i + '" class="treeview-menu"> </ul>');
                        $('[menu-id="' + i + '"]').append(html);
                    }



                }

                for (var j=0;j<chidLen;j++){
                    child = menu.children[j];
                    if(child.ismenu == 1) {
                        if (i == 0 && j == 0) {
                            html = $('<li class="active"><a href="' + child.url + '" menu-controller="' + child.url + '" target="mainFrame"><i class="fa fa-circle-o"></i> ' + child.name + '</a></li>');
                        } else {
                            html = $('<li class=""><a href="' + child.url + '" menu-controller="' + child.url + '" target="mainFrame"><i class="fa fa-circle-o"></i> ' + child.name + '</a></li>');
                        }

                        $('[menuUl-id="' + i + '"]').append(html);
                    }

                }

            }
            // $(Selector.data).each(function () {
            //     Plugin.call($(this))
            // })
            // $(".sidebar-menu li:first ul li:first a").click();

        },

        error: function () {
            console.log("异常！")
        }
    })
}