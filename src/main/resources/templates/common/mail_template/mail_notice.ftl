<div class="emailpaged" style="background: #fff;">
    <div class="emailcontent" style="width:100%;max-width:720px;text-align: left;margin: 0 auto;padding-top: 20px;padding-bottom: 80px">
        <div class="emailtitle" style="border-radius: 5px;border:1px solid #eee;overflow: hidden;">
            <h1 style="color:#fff;background: #3798e8;line-height:70px;font-size:24px;font-weight:normal;padding-left:40px;margin:0">
                New comments on your blog！
            </h1>
            <div class="emailtext" style="background:#fff;padding:20px 32px 40px;">
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">${user.nickname!}, Hello!</p>
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">There are visitors《${pageTitle!}》leave a message:</p>
                    <br />
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;padding:10px 20px;background:#f8f8f8;margin:0">
                ${author!}：${content!}
                </p>
                <br />
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">You can click<a href="${pageFullPath!}">View full content</a></p>
            </div>
        </div>
    </div>
</div>