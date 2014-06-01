<#import "../common/page.ftl" as layout>
<@layout.page title>

<table class="list-item-view">
  <tr>
    <td>
        <span>
          <b>name</b>: ${pattern.name}
        </span>
    </td>
  </tr>
  <tr>
    <td>
        <span>
          <b>title</b>: ${pattern.title}
        </span>
    </td>
  </tr>
  <tr>
    <td>
        <span>
          <b>enabled</b>: ${pattern.enabled}
        </span>
    </td>
  </tr>
  <tr>
    <td>
      <span>
        <b>category</b>: ${pattern.category.title}
      </span>
    </td>
  </tr>
  <tr>
    <td>
      <span>
        <b>severity</b>: ${pattern.severity.title}
      </span>
    </td>
  </tr>

  <tr>
    <td>
      <span>
        <b>description</b>:<br/>
        <div class="umllint-pattern-description">
        ${pattern.description}
        </div>
      </span>
    </td>
  </tr>

  <tr>
    <td>
      <span>
        <b>reference</b>: ${pattern.reference.citation}
      </span>
    </td>
  </tr>


  <tr>
    <td>
      <span>
        <b>code</b>:<div class="code-qvt"> ${pattern.code}
      </span>
    </td>
  </tr>
</table>
</p>

<p>
  <a href="/app/pattern/edit/${pattern.id}">edit</a>
  <a href="/app/pattern/delete/${pattern.id}">delete</a>
</p>

</@layout.page>
