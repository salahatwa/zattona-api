<#include "../macro.ftl">
<#include "kitboxmenu.ftl"/>
<@layout title="Toolkit free maker | ${options.blog_title!} " keywords="${meta_keywords!}" description="${meta_description!}">
<div class="container" style="padding-top: 100px;">
<div class="row">
<div class="col-sm-3 col-md-3">
  <@KitBoxMenu KitBoxMenus '/kitbox/tools/freemarkerTest'></@KitBoxMenu>
</div>
<div class="col-sm-9 col-md-9">
  <div class="card">
    <div class="card-body">
      <h5 class="card-title text-muted">FreeMarker(FTL) Online Test Tools</h5>
      <div class="row">
        <div class="col-md-6">
          <form>
            <div class="form-group">
              <label>FreeMarker (ftl) Code</label>
              <textarea
                id="ftl"
                name="ftl"
                class="form-control"
                rows="8"
              ></textarea>
            </div>
          </form>
        </div>
        <div class="col-md-6">
          <form>
            <div class="form-group">
              <label>JavaBean (JSON) Code</label>
              <textarea
                id="json"
                name="json"
                class="form-control"
                rows="8"
              ></textarea>
            </div>
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <button
            id="freeMarkerTstBtn"
            type="button"
            class="btn btn-primary btn-block"
            onclick="test()"
            style="width: 100%; margin: 10px 0"
            data-loading-text="<i class='fa fa-circle-o-notch fa-spin'></i> Loading..."
          >
          <span id="loaderId" ></span>
            Test
          </button>
        </div>
      </div>
      <div class="row">
        <div class="col-12">
          <form>
            <div class="form-group">
              <label>Result</label>
              <textarea
                id="data"
                name="data"
                class="form-control"
                rows="8"
              ></textarea>
            </div>
          </form>
        </div>
      </div>
      <div>
        <blockquote style="font-size: 14px">
          <p>
            Fill in the FreeMarker code in "FreeMarker (ftl) Code"; in "JavaBean
            (JSON) "Code" fill in the JSON string of the parameter object;
            "Result" will return the running result of the FreeMarker engine。
          </p>
          <footer>
            Only supports the built-in function method, does not support the
            custom function method
          </footer>
        </blockquote>
      </div>
    </div>
  </div>
</div>
</div>
</div>
<script type="text/javascript">
  $(function () {
    $("#ftl").val(
      "<html>\n\t<body>\n\t\t ${'$'}{demo.text}\n\t</body>\n</html>"
    );
    $("#json").val('{\n\t"demo":{\n\t\t"text":"Hello World"\n\t}\n}');
  });

 

 function test() {
  
    enableLoading("freeMarkerTstBtn");
    
    let ftl = $("#ftl").val();
    let json = $("#json").val();
    if (ftl === "") {
      $("#data").val("Error: FreeMarker Code is empty！");
      msg("Error: FreeMarker Code is empty！","error");
       disableLoading("freeMarkerTstBtn");
      return;
    }
    if (json === "") {
      $("#data").val("Error: JavaBean Code is empty！");
      msg("Error: JavaBean Code is empty！","error");
       disableLoading("freeMarkerTstBtn");
      return;
    }
    $.ajax({
      url: "/api/freemarker/test",
      type: "POST",
      async: true,
      data: {
        ftl: ftl,
        beanJson: json,
      },
      timeout: 60000,
      dataType: "json",
      success: function (data, textStatus, jqXHR) {
        if (data.data.code === 200) {
          $("#data").val(data.data.data);
           msg("Generated successfully", "success");
        } else {
          $("#data").val("Error.\n" + data.data.message);
          msg(data.data.message, "error");
        }
      },
      error: function (xhr, textStatus) {
        $("#data").val("Error.\n" + xhr.responseText);
        msg(xhr.responseText, "error");
      },
      complete: function(data) {
           disableLoading("freeMarkerTstBtn");
        }
    });
  }
</script>
</@layout>
