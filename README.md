
@{
    ViewBag.Title = "工厂新增、编辑";
    Layout = "~/Views/Shared/_Layout.cshtml";
}
<body>
    <div class="mini-toolbar">
        <form method="post" id="Form" class="important">
            <div class="mini-toolbar">
                <input id="FactoryId" name="FactoryId" value="0" class="mini-hidden" />
                <table id="edit">
                    <tr>
                        <th>名称：</th>
                        <td>
                            <input name="Name" id="Name" class="mini-textbox textboxWidth" required="true" vtype="maxLength:100" />
                            <span class="require">*</span>
                        </td>
                        <th>简称：</th>
                        <td>
                            <input name="Sname" id="Sname" class="mini-textbox textboxWidth" required="true" vtype="maxLength:100" />
                            <span class="require">*</span>
                        </td>
                    </tr>
                    <tr>                       
                        <th>标准编码：</th>
                        <td>
                            <input name="StdCode" id="StdCode" class="mini-textbox textboxWidth" required="true" vtype="maxLength:100" />
                            <span class="require">*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>是否启用：</th>
                        <td>
                            <input name="InUse" id="InUse" class="mini-checkbox checkboxWidth" truevalue="1" falsevalue="0" value="1" />
                        </td>
                        <th>排序：</th>
                        <td>
                            <input name="SortNum" class="mini-spinner textboxWidth" value="0" minvalue="0" maxvalue="200000" required="true" />
                            <span class="require">*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>描述：</th>
                        <td colspan="3">
                            <input name="Des" class="mini-textbox desTextboxWidth" vtype="maxLength:1000" />
                        </td>
                    </tr>
                </table>
                <div class="bottomButtonContainer">
                    <a class="mini-button bottomButtonSpan" id="btnSave" iconcls="icon-save" onclick="Save()">保存</a>
                    <a class="mini-button bottomButtonSpan" iconcls="icon-cancel" onclick="CloseWindow(false)">关闭</a>
                </div>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
    mini.parse();
    var form = new mini.Form("Form");
    var factoryId, pageMode, url;
   //判断下拉列表是否为空
    function onComboValidation(e) {
        var items = this.findItems(e.value);
        if (!items || items.length == 0) {
            e.isValid = false;
            e.errorText = "不能为空";
        }
    }

    //编辑加载数据
    function SetData(data) {
        pageMode = data.PageMode;//1新增 2编辑
        factoryId = data.FactoryID;
        if (pageMode == PageModelEnum.Edit) {
            $.ajax({
                url: '@Url.Action("QuerySingleFactory")?now=' + Math.random(),
                data: { "factoryId": factoryId },
                cache: false,
                success: function (text) {
                    var codeText = mini.decode(text);
                    form.setData(codeText);
                    form.setChanged(false);
                    url = '@Url.Action("UpdateFactory")?now=' + Math.random();
                }
            });
        } else {
            mini.get("InUse").disable();
            url = '@Url.Action("AddFactory")?now=' + Math.random();
        }
    }

    //保存按钮操作
    function Save() {
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        var data = form.getData();
        var json = mini.encode(data);
        mini.get('btnSave').setEnabled(false);
        $.ajax({
            url: url,
            type: "post",
            data: { data: json },
            cache: false,
            success: function (text) {
                mini.get('btnSave').setEnabled(true);
                alert(text.Message);
                CloseWindow(true);
            },
            error: function (text) {
                mini.get('btnSave').setEnabled(true);
                var textReult = mini.decode(text.responseText);
                alert(textReult.Message);
            }
        });
    }
</script>




@{
    ViewBag.Title = "工厂维护";
    Layout = "~/Views/Shared/_Layout.cshtml";
}

<body>
    <!--页头 及查询条件-->
    <div>
        <div class="mini-toolbar mes-toolbar">
            <span class="title-icon"></span>
            <label class="titleSpan">工厂维护(PCITC_EP V1.0_20170527)</label>
            <div class="topButtonContainer">
                <span class="mes-buttons">
                    <a class="mini-button buttonSpan" id="btnAdd" iconcls="icon-add" onclick="add()">新增</a>
                    <a class="mini-button buttonSpan" id="btnAllDel" iconcls="icon-no" onclick="delAll()">删除</a>
                </span>
            </div>
        </div>
    </div>
    <div id="SearchForm">
        <div class="mini-toolbar">
            <span class="spancontainer">
                名称：
                <input class="mini-textbox textboxWidth" id="SearchFactory" vtype="maxLength:100" />
            </span>
            <span class="spancontainer">
                标准编码：
                <input class="mini-textbox textboxWidth" id="SearchStdCode" vtype="maxLength:100" />
            </span>
            <span class="spancontainer">
                是否启用：
                <input id="SearchInUse" class="mini-combobox middleComboboxWidth" valuefield="Key" textfield="Value" value="1" required="true" onblur="checkComboboxblur('SearchInUse')" allowinput="true" valuefromselect="true" />
            </span>
            <span class="buttonSpancontainer"><a class="mini-button searchButtonSpan" iconcls="icon-search" id="btnQuery" onclick="search(0)">查询</a></span>
        </div>
    </div>
    <!--表格Grid-->
    <div class="mini-fit">
        <div id="FactoryGrid" class="mini-datagrid" onbeforeload="clearSelect" onloaderror="fail(e)" style="width: 100%; height: 100%" url="@Url.Action("QueryFactoryData")" idField="FactoryId" multiselect="true" showpager="false" pagesize="20" sortMode="client" >
            <div property="columns">
                <div type="checkcolumn"></div>
                <div name="action" headeralign="center" renderer="onActionRenderer" width="70" align="center">操作</div>
                <div field="Name" datatype="string" sorttype="chinese" headeralign="center" width="130" align="left" allowsort="true" autoescape="true">名称</div>
                <div field="Sname" datatype="string" sorttype="chinese" headeralign="center" width="130" align="left" allowsort="true" autoescape="true">简称</div>
                <div field="StdCode" datatype="string" sorttype="chinese" headeralign="center" width="130" align="left" allowsort="true" autoescape="true">标准编码 </div>
                <div field="InUseShow" datatype="string" sorttype="chinese" headeralign="center" width="70" align="center" allowsort="true" autoescape="true">是否启用</div>
                <div field="CrtDate" datatype="date" headeralign="center" dateformat="yyyy-MM-dd HH:mm:ss" width="130" align="center" allowsort="true" sorttype="date">创建时间</div>
                <div field="CrtUserName" datatype="string" sorttype="chinese" headeralign="center" width="70" align="left" allowsort="true" autoescape="true">创建人</div>
                <div field="MntDate" datatype="date" headeralign="center" dateformat="yyyy-MM-dd HH:mm:ss" width="130" align="center" allowsort="true" sorttype="date">维护时间</div>
                <div field="MntUserName" datatype="string" sorttype="chinese" headeralign="center" width="70" align="left" allowsort="true" autoescape="true">维护人</div>
                <div field="SortNum" datatype="int" headeralign="center" width="50" align="right" allowsort="true" sorttype="float">排序 </div>
                <div field="Des" datatype="string" sorttype="chinese" headeralign="center" width="180" align="left" allowsort="true" autoescape="true">描述 </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
    mini.parse();
    var grid = mini.get("FactoryGrid");
    var form = new mini.Form("SearchForm");
    $(function () {
        //获取是否启用
        GetCombobox('SearchInUse', '@Url.Action("QueryInUse")', true);
        mini.get("SearchInUse").setValue(1);
    });

    //添加配置行按钮操作
    function onActionRenderer(e) {
        var record = e.record;
        var factoryId = record.FactoryId;
        var operationString = ' <a class="Edit_Button" href="javascript:edit(\'' + factoryId + '\')">编辑</a>';
        operationString += ' <a class="Reply_Button" href="javascript:delSingle(\'' + factoryId + '\')">删除</a>';
        return operationString;
    }

    //搜索
    function search(index) {
        form.validate();
        if (form.isValid() == false) {
            return;
        }
        var inUse = mini.get("SearchInUse").getValue();//是否启用
        var name = mini.get("SearchFactory").getValue();//工厂名称
        var stdcode = mini.get("SearchStdCode").getValue();//标准编码
        grid.load({  "name": trim(name), "inUse": inUse, "stdcode": stdcode, pageIndex: index });
        grid.setShowPager(true);
    }

    //工厂维护新增
    function add() {
        var pageMode = PageModelEnum.NewAdd;
        var title = "工厂新增";
        detail(title, "", pageMode);
    }

    //工厂维护编辑
    function edit(factoryId) {
        var pageMode = PageModelEnum.Edit;
        var title = "工厂编辑";
        detail(title, factoryId, pageMode);
    }

    //工厂维护新增或者编辑详细页面
    function detail(title, fid, pageMode) {
        mini.open({
            url: '@Url.Action("FactoryAddOrEdit")?now=' + Math.random(),
            title: title, width: 620, height: 220,
            allowResize: false,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = { "PageMode": pageMode, "FactoryID": fid };
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (isRefresh) {
                if (isRefresh == true) {
                    if (pageMode == PageModelEnum.NewAdd) search(0);
                    if (pageMode == PageModelEnum.Edit) search(grid.pageIndex);
                }
            }
        });
    }

    //工厂维护（单条删除）
    function delSingle(factoryId) {
        if (confirm('确定删除吗？')) {
            var ids = [];
            ids.push(factoryId);
            $.ajax({
                url: '@Url.Action("DeleteFactory")?now=' + Math.random(),
                data: { data: mini.encode(ids) },
                type: "post",
                success: function (text) {
                    alert(text.Message);
                    search(0);
                },
                error: function (text) {
                    var textResult = mini.decode(text.responseText);
                    alert(textResult.Message);
                    grid.reload();
                    return;
                }
            });
        }
    }

    //工厂维护（批量删除）
    function delAll() {
        var rows = grid.getSelecteds();
        if (rows.length > 0) {
            if (confirm('确定删除吗？')) {
                var ids = [];
                for (var i = 0, n = rows.length; i < n; i++) {
                    var r = rows[i];
                    ids.push(r.FactoryId);
                }
                mini.get('btnAllDel').setEnabled(false);
                $.ajax({
                    url: '@Url.Action("DeleteFactory")?now=' + Math.random,
                    data: { data: mini.encode(ids) },
                    type: "post",
                    success: function (text) {
                        alert(text.Message);
                        mini.get('btnAllDel').setEnabled(true);
                        search(0);
                    },
                    error: function (text) {
                        mini.get('btnAllDel').setEnabled(true);
                        var textReult = mini.decode(text.responseText);
                        alert(textReult.Message);
                        grid.reload();
                        return;
                    }
                });
            }
        }
        else {
            alert("请选择要删除的数据！");
        }
    }
</script>


