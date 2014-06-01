<#import "../common/page.ftl" as layout>
<@layout.page title>

<p>
<form action="/app/pattern/create" method="post">

    <p>name: <input name="pattern-name" type="text"/></p>

    <p>title: <input name="pattern-title" type="text"/></p>

    <p>enabled: <input name="pattern-enabled" type="text"/></p>

    <p>category:

        <select name="pattern-category">
          <#list categories as category>
              <option value="${category.id}">${category.title}</option>
          </#list>
        </select>
    </p>
    <p>severity:

        <select name="pattern-severity">
          <#list severities as severity>
              <option value="${severity.id}">${severity.title}</option>
          </#list>
        </select>
    </p>
    <p>reference:

        <select name="pattern-reference">
          <#list references as reference>
              <option value="${reference.id}">${reference.title}</option>
          </#list>
        </select>
    </p>

    <p>description: <textarea name="pattern-description"></textarea></p>

    <p>code: <textarea name="pattern-code"></textarea></p>

    <input name="submit" type="submit"/>
</form>
</p>

</@layout.page>