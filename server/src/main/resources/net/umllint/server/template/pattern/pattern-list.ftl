<#import "../common/page.ftl" as layout>
<@layout.page title>

<p>
  <a href="/app/pattern/create">Create</a> a new pattern.
<p>


</p>
<table id="pattern-list">
  <tr>
    <th>id</th>
    <th>enabled</th>
    <th>title</th>
    <th>category</th>
    <th>severity</th>
    <th>reference</th>
    <th class="list-cell-actions"></th>

  </tr>
  </tr>
  <#list patterns as pattern>
    <tr>
      <td>${pattern.id}</td>

      <#if pattern.enabled=="t">
        <td><input type="checkbox" checked="checked"disabled="disabled"/></td>
      <#else>
        <td><input type="checkbox" disabled="disabled"/></td>
      </#if>

      <td><a href="/app/pattern/view/${pattern.id}">${pattern.title}</a></td>
      <td>${pattern.category.title}</td>
      <td>${pattern.severity.title}</td>
      <td>${pattern.reference.title}</td>
      <td class="list-cell-actions">
        <a href="/app/pattern/edit/${pattern.id}">edit</a>
        <a href="/app/pattern/delete/${pattern.id}">delete</a>
      </td>

    </tr>
  </#list>
</table>
</p>

</@layout.page>

