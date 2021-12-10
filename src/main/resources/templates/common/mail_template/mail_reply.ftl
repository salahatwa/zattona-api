<div class="emailpaged" style="background: #fff;">
    <div class="emailcontent" style="width:100%;max-width:720px;text-align: left;margin: 0 auto;padding-top: 20px;padding-bottom: 80px">
        <div class="emailtitle" style="border-radius: 5px;border:1px solid #eee;overflow: hidden;">
            <h1 style="color:#fff;background: #3798e8;line-height:70px;font-size:24px;font-weight:normal;padding-left:40px;margin:0">
                You are ${blog_title!} There is a reply to the message on！
            </h1>
            <div class="emailtext" style="background:#fff;padding:20px 32px 40px;">

                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">${baseAuthor!}, Hello!</p>
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">You are《${pageTitle!}》Message:
                    <br />
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;padding:10px 20px;background:#f8f8f8;margin:0">${baseContent!}</p>
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">${replyAuthor!} Reply to you:
                    <br />
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;padding:10px 20px;background:#f8f8f8;margin:0">${replyContent!}</p>
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">You can click
                    <a href="${pageFullPath!}">View full content</a>
                </p>
                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">Welcome again
                    <a href="${blog_url!}">${blog_title!}</a>
                </p>

                <p style="color: #6e6e6e;font-size:13px;line-height:24px;">(This email is sent automatically by the system, please do not reply. If you interrupt, please forgive me.)</p>
            </div>
            <p style="color: #6e6e6e;font-size:13px;line-height:24px;text-align:right;padding:0 32px">Mail from：
                <a href="${blog_url!}" style="color:#51a0e3;text-decoration:none">${blog_title!}</a>
            </p>
        </div>
    </div>
</div>