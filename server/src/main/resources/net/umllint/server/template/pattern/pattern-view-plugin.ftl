<#import "../common/page-plugin.ftl" as layout>
<@layout.page>

<h2>${pattern.title}</h2>
<p>
<table class="list-item-view">
    <tr>
        <td>
        <span>
          <b>name</b>: ${pattern.id}
        </span>
        </td>
    </tr>
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
          <b>name</b>: ${pattern.title}
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
        <b>reference</b>: ${pattern.reference.name}
      </span>
        </td>
    </tr>
    <tr>
        <td>
      <span>
        <b>reference</b>:<br />
        <div class="umllint-pattern-description">
          ${pattern.description}
        </div>
      </span>
        </td>
    </tr>
    <tr>
        <td>
      <span>
        <b>code</b>:<div class="code-qvt"> ${pattern.code}</div>
      </span>
        </td>
    </tr>
</table>
</p>

</@layout.page>

