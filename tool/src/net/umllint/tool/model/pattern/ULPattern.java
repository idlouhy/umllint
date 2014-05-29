package net.umllint.tool.model.pattern;


public class ULPattern {

    private String id;
    private String name;
    private String title;
    private String code;
    private String description;
    private ULPatternCategory category;
    private ULPatternSeverity severity;
    private ULPatternReference reference;

    public ULPattern() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ULPatternCategory getCategory() {
        return category;
    }
    public void setCategory(ULPatternCategory category) {
        this.category = category;
    }
    public ULPatternSeverity getSeverity() {
        return severity;
    }
    public void setSeverity(ULPatternSeverity severity) {
        this.severity = severity;
    }
    public ULPatternReference getReference() {
        return reference;
    }
    public void setReference(ULPatternReference reference) {
        this.reference = reference;
    }
}