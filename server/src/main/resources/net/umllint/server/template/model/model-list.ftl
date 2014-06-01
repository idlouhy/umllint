<#import "../common/page.ftl" as layout>
<@layout.page title>



<table id="model-list">
    <tr>
        <th>name</th>
        <th>title</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    </tr>
    <#list models as model>
        <tr>
            <td>${model.name}</td>
            <td>${model.title}</td>
            <td><a href="/app/models/view/${model.id}">view</a></td>
            <td><a href="/app/models/edit/${model.id}">edit</a></td>
            <td><a href="/app/results">execute</a></td>
        </tr>
    </#list>
</table>

</@layout.page>
