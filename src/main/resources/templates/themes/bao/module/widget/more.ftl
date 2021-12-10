<div class="pagination-list flex flex-row " id="pagination">
    <#if pagination.hasNext>
        <div class="sbtn diag-btn more-btn see-more" role="button" path="${pagination.nextPageFullPath!}" onClick="getMore(this)">
            see more
        </div>
    <#else>
        <p style="color: #cccccc">~You're done~</p>
    </#if>
</div>