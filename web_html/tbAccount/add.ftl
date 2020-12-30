<#assign path = request.contextPath/>
<!DOCTYPE html>
<html class="x-admin-sm">
  
  <head>
    <meta charset="UTF-8">
    <title>商户管理平台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="stylesheet" href="${path}/assets/css/font.css">
    <link rel="stylesheet" href="${path}/assets/css/xadmin.css">
    <script type="text/javascript" src="${path}/assets/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${path}/assets/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="layui-fluid">
        <div class="layui-row">
            <#--<input id="menus" value="${menus}"/>menus-->
            <form action="" id="form_data" method="post" class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label for="loginId" class="layui-form-label">
                        <span class="x-red">*</span>登入账号
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="loginId" name="loginId" required="" lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="loginPass" class="layui-form-label">
                        <span class="x-red">*</span>密码
                    </label>
                    <div class="layui-input-inline">
                        <input type="password" id="loginPass" name="loginPass" required="" lay-verify="required" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <button class="layui-btn" lay-submit="" id="add" lay-filter="add">增加</button>
              </div>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        layui.use(['tree', 'util'], function(){
            var form = layui.form;
            form.on('submit(add)', function(data){
                $.ajax({
                    url : '${path}${Session["sys:tbAccount:add"]}',
                    type : 'POST',
                    dataType : 'json',
                    data : $('#form_data').serialize(),
                    async : false,
                    success : function (res) {
                        if(res.state){
                            layer.alert("添加成功",{icon:6},function () {
                                // 获得frame索引
                                var index = parent.layer.getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                                //刷新父窗口
                                xadmin.father_reload();
                            })
                        }else {
                            layer.alert(res.msg);
                        }
                    },error : function (e) {
                        layer.alert(e)
                    }
                });
                return false;
            });
        });

    </script>
  </body>

</html>