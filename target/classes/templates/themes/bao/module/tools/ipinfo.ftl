<#include "../macro.ftl">
<#include "kitboxmenu.ftl"/>
<@layout title="Toolkit ip query Tool | ${options.blog_title!} " keywords="${meta_keywords!}" description="${meta_description!}">
    <div class="container" style="padding-top: 100px;">
        <div class="row">
            <div class="col-sm-3 col-md-3">
               <@KitBoxMenu KitBoxMenus '/kitbox/tools/ip'></@KitBoxMenu>
            </div>
            <div class="col-sm-9 col-md-9">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">IP Address information query tool</h5>
                        <h6 class="card-subtitle mb-2 text-muted">
                            IP address information query tool, open service interface to realize IP information query, query the geographic location of IP address</h6>
                        <div style="margin: auto;max-width: 600px;">
                            <form>
                                <label class="sr-only" for="iptext">IP</label>
                                <div class="input-group mb-2 mr-sm-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">IP:</div>
                                    </div>
                                    <input type="text" class="form-control" value="${myip!''}"
                                           id="iptext" placeholder="IP">
                                    <div class="input-group-prepend">
                                        <div class="btn btn-primary" id="ipQueryBtn" onclick="query()"><span id="loaderId" ></span>Query</div>
                                    </div>
                                </div>
                            </form>
                            <form>
                                <div class="form-group row">
                                    <label for="countryShort"
                                           class="col-sm-2 col-form-label col-form-label-sm">country code</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="countryShort"
                                               value="${ipdto.countryShort!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="countryLong"
                                           class="col-sm-2 col-form-label col-form-label-sm">country</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="countryLong"
                                               value="${ipdto.countryLong!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="region" class="col-sm-2 col-form-label col-form-label-sm">province</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="region"
                                               value="${ipdto.region!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="city" class="col-sm-2 col-form-label col-form-label-sm">city</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="city"
                                               value="${ipdto.city!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="isp" class="col-sm-2 col-form-label col-form-label-sm">ISP</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="isp"
                                               value="${ipdto.isp!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="zipcode" class="col-sm-2 col-form-label col-form-label-sm">Postcode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="zipcode"
                                               value="${ipdto.zipcode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="latitude" class="col-sm-2 col-form-label col-form-label-sm">latitude</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="latitude"
                                               value="${ipdto.latitude!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="longitude" class="col-sm-2 col-form-label col-form-label-sm">longitude</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="longitude"
                                               value="${ipdto.longitude!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="timezone" class="col-sm-2 col-form-label col-form-label-sm">Time zone</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="timezone"
                                               value="${ipdto.timezone!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="domain" class="col-sm-2 col-form-label col-form-label-sm">domain</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="domain"
                                               value="${ipdto.domain!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="iddcode"
                                           class="col-sm-2 col-form-label col-form-label-sm">iddcode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="iddcode"
                                               value="${ipdto.iddcode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="areacode"
                                           class="col-sm-2 col-form-label col-form-label-sm">areacode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="areacode"
                                               value="${ipdto.areacode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="weatherstationcode" class="col-sm-2 col-form-label col-form-label-sm">weatherstationcode</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="weatherstationcode"
                                               value="${ipdto.weatherstationcode!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="weatherstationname" class="col-sm-2 col-form-label col-form-label-sm">weatherstationname</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="weatherstationname"
                                               value="${ipdto.weatherstationname!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mcc" class="col-sm-2 col-form-label col-form-label-sm">mcc</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="mcc"
                                               value="${ipdto.mcc!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mnc" class="col-sm-2 col-form-label col-form-label-sm">mnc</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="mnc"
                                               value="${ipdto.mnc!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="mobilebrand" class="col-sm-2 col-form-label col-form-label-sm">mobilebrand</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm"
                                               id="mobilebrand"
                                               value="${ipdto.mobilebrand!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="elevation"
                                           class="col-sm-2 col-form-label col-form-label-sm">elevation</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="elevation"
                                               value="${ipdto.elevation!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="usagetype"
                                           class="col-sm-2 col-form-label col-form-label-sm">usagetype</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="usagetype"
                                               value="${ipdto.usagetype!'...'}">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="status" class="col-sm-2 col-form-label col-form-label-sm">status</label>
                                    <div class="col-sm-10">
                                        <input type="text" readonly class="form-control form-control-sm" id="status"
                                               value="${ipdto.status!'...'}">
                                    </div>
                                </div>
                            </form>
                            <div>
                                <blockquote style="font-size: 14px;">
                                    <p>IP query interface service has been opened. For more open interface services,please contact with us。
                                    </p>
                                    <footer>{ip} - Change to the IP address that needs to be queried, use GET request</footer>
                                </blockquote>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<script type="text/javascript">
            function query() {
             enableLoading("ipQueryBtn");
                var ip = $("#iptext").val();
                if (ip === '') {
                    msg("Please fill in the IP address to be queried", "error");
                    disableLoading("ipQueryBtn");
                    return;
                }
                $.ajax({
                    url: '/api/ip/' + ip,
                    type: 'GET',
                    async: true,
                    timeout: 10000,
                    dataType: 'json',
                    success: function (data, textStatus, jqXHR) {
                        if (data.data.code === 200) {
                            $("#countryShort").val(data.data.data.countryShort);
                            $("#countryLong").val(data.data.data.countryLong);
                            $("#region").val(data.data.data.region);
                            $("#city").val(data.data.data.city);
                            $("#isp").val(data.data.data.isp);
                            $("#latitude").val(data.data.data.latitude);
                            $("#longitude").val(data.data.data.longitude);
                            $("#timezone").val(data.data.data.timezone);
                            $("#domain").val(data.data.data.domain);
                            $("#zipcode").val(data.data.data.zipcode);
                            $("#netspeed").val(data.data.data.netspeed);
                            $("#iddcode").val(data.data.data.iddcode);
                            $("#areacode").val(data.data.data.areacode);
                            $("#weatherstationcode").val(data.data.data.weatherstationcode);
                            $("#weatherstationname").val(data.data.data.weatherstationname);
                            $("#mcc").val(data.data.data.mcc);
                            $("#mnc").val(data.data.data.mnc);
                            $("#mobilebrand").val(data.data.data.mobilebrand);
                            $("#elevation").val(data.data.data.elevation);
                            $("#usagetype").val(data.data.data.usagetype);
                        } else {
                            msg(data.data.message, "error");
                        }
                    },
                    error: function (xhr, textStatus) {
                        msg(xhr.responseText, "error");
                    },
                     complete: function(data) {
                        disableLoading("ipQueryBtn");
                    }
                })
            }
</script>
</@layout>