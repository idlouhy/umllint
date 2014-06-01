<#import "../common/page.ftl" as layout>
<@layout.page title>

<p>
<form action="/app/pattern/edit/${pattern.id}" method="post">

  <p>id: ${pattern.id} <input name="pattern-id" readonly="true" type="hidden" value="${pattern.id}"/></p>

  <p>name: <input name="pattern-name" type="text" value="${pattern.name}"/></p>

  <p>title: <input name="pattern-title" type="text" value="${pattern.title}"/></p>

  <p>enabled: <input name="pattern-enabled" type="text" value="${pattern.enabled}"/></p>

  <p>category:

    <select name="pattern-category">
      <#list categories as category>
        <#if pattern.category.id==category.id>
          <option value="${category.id}" selected="true">${category.title}</option>
        <#else>
          <option value="${category.id}">${category.title}</option>
        </#if>
      </#list>
    </select>
  </p>
  <p>severity:

    <select name="pattern-severity">
      <#list severities as severity>
        <#if pattern.severity.id==severity.id>
          <option value="${severity.id}" selected="true">${severity.title}</option>
        <#else>
          <option value="${severity.id}">${severity.title}</option>
        </#if>
      </#list>
    </select>
  </p>
  <p>reference:

    <select name="pattern-reference">
      <#list references as reference>
        <#if pattern.reference.id==reference.id>
          <option value="${reference.id}" selected="true">${reference.title}</option>
        <#else>
          <option value="${reference.id}">${reference.title}</option>
        </#if>
      </#list>
    </select>
  </p>

  <p>description: <textarea name="pattern-description">${pattern.description}</textarea></p>

  <p>code: <textarea name="pattern-code">${pattern.code}</textarea></p>

  <input name="submit" type="submit"/>
</form>
</p>

</@layout.page>