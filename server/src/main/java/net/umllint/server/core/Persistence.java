package net.umllint.server.core;

import net.umllint.server.model.*;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Persistence {

  private static Persistence instance = null;

  private Connection connection = null;
  private Statement statement = null;
  private ResultSet resultSet = null;

  private Log log = Log.getInstance();

  private String url;
  private String user;
  private String password;

  protected Persistence() {

  }

  public static Persistence getInstance() throws ClassNotFoundException {

    if (instance == null) {
      instance = new Persistence();
      instance.init();
    }

    return instance;
  }


  public Connection getConnection() {
    return connection;
  }

  public String getUrl() {
    return url;
  }

  public String getUser() {
    return user;
  }

  public String getPassword() {
    return password;
  }

  private void init() throws ClassNotFoundException {

    Properties properties = new Properties();

    try {

      String os = System.getProperty("os.name");

      if (os.startsWith("Windows")) {
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("local.properties"));
      }
      else {
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("production.properties"));
      }

      url = properties.getProperty("umllint.db.url");
      user = properties.getProperty("umllint.db.user");
      password = properties.getProperty("umllint.db.password");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private void connect() throws SQLException, ClassNotFoundException {

    if (connection != null) {
      connection.close();
    }

    Class.forName("org.postgresql.Driver");
    connection = DriverManager.getConnection(url, user, password);
  }


  private ULPattern getPattern(ResultSet resultSet) throws SQLException, ClassNotFoundException {


    ULPattern pattern = new ULPattern();
    pattern.setId(resultSet.getString(1));
    pattern.setName(resultSet.getString(5));
    pattern.setTitle(resultSet.getString(6));
    pattern.setCode(resultSet.getString(7));
    pattern.setDescription(resultSet.getString(8));
    pattern.setEnabled(resultSet.getString(9));

    ULPatternCategory category = new ULPatternCategory();
    category.setId(resultSet.getInt(10));
    category.setName(resultSet.getString(11));
    category.setTitle(resultSet.getString(12));
    pattern.setCategory(category);

    ULPatternSeverity severity = new ULPatternSeverity();
    severity.setId(resultSet.getInt(13));
    severity.setName(resultSet.getString(14));
    severity.setTitle(resultSet.getString(15));
    pattern.setSeverity(severity);

    ULPatternReference reference = new ULPatternReference();
    reference.setId(resultSet.getInt(16));
    reference.setName(resultSet.getString(17));
    reference.setTitle(resultSet.getString(18));
    reference.setDescription(resultSet.getString(19));
    reference.setCitation(resultSet.getString(21));
    pattern.setReference(reference);

    return pattern;
  }

  public List<ULPattern> getAllPatterns() throws SQLException, ClassNotFoundException {

    List<ULPattern> patterns = new LinkedList<ULPattern>();

    connect();

    try {
      String sql = "SELECT * FROM pattern\n" +
          "  LEFT JOIN category ON pattern.category_id=category.category_id\n" +
          "  LEFT JOIN severity ON pattern.severity_id=severity.severity_id\n" +
          "  LEFT JOIN reference ON pattern.reference_id=reference.reference_id" +
          "  ORDER BY pattern_id";
      log.addMessage(sql);


      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        patterns.add(getPattern(resultSet));
      }

    } catch (SQLException e) {
      log.addMessage(e.getMessage());
    }

    return patterns;
  }

  public ULPattern getPattern(Integer id) throws SQLException, ClassNotFoundException {

    ULPattern pattern = null;

    connect();

    try {
      String sql = "SELECT * FROM pattern\n" +
          "  LEFT JOIN category ON pattern.category_id=category.category_id\n" +
          "  LEFT JOIN severity ON pattern.severity_id=severity.severity_id\n" +
          "  LEFT JOIN reference ON pattern.reference_id=reference.reference_id\n" +
          " WHERE pattern.pattern_id=" + id.toString();


      log.addMessage(sql);

      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);

      if (resultSet.next()) {
        pattern = getPattern(resultSet);
      }

    } catch (SQLException e) {
      log.addMessage(e.getMessage());
    }

    return pattern;
  }


  public ULPattern updatePattern(ULPattern pattern) throws SQLException, ClassNotFoundException {

    connect();

    try {

      String sql = "UPDATE pattern SET name=?, title=?, enabled=?, category_id=?, severity_id=?, reference_id=?, code=?, description=? WHERE pattern_id=?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, pattern.getName());
      preparedStatement.setString(2, pattern.getTitle());
      Boolean enabled = pattern.getEnabled().equals("t")? true : false;
      preparedStatement.setBoolean(3, enabled);
      preparedStatement.setInt(4, pattern.getCategory().getId());
      preparedStatement.setInt(5, pattern.getSeverity().getId());
      preparedStatement.setInt(6, pattern.getReference().getId());
      preparedStatement.setString(7, pattern.getCode());
      preparedStatement.setString(8, pattern.getDescription());
      preparedStatement.setInt(9, Integer.parseInt(pattern.getId()));
      preparedStatement.executeUpdate();
      log.addMessage(sql);

    } catch (SQLException e) {
      log.addMessage(e.getMessage());
    }

    return pattern;
  }


  public ULPattern insertPattern(ULPattern pattern) throws SQLException, ClassNotFoundException {

    connect();

    try {

      String sql = "INSERT INTO pattern (name, title, enabled, category_id, severity_id, reference_id, code, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, pattern.getName());
      preparedStatement.setString(2, pattern.getTitle());
      Boolean enabled = pattern.getEnabled().equals("t")? true : false;
      preparedStatement.setBoolean(3, enabled);
      preparedStatement.setInt(4, pattern.getCategory().getId());
      preparedStatement.setInt(5, pattern.getSeverity().getId());
      preparedStatement.setInt(6, pattern.getReference().getId());
      preparedStatement.setString(7, pattern.getCode());
      preparedStatement.setString(8, pattern.getDescription());
      ResultSet resultSet = preparedStatement.executeQuery();
      log.addMessage(sql);

    } catch (SQLException e) {
      log.addMessage(e.getMessage());
    }

    return pattern;
  }

  public ULPattern deletePattern(ULPattern pattern) throws SQLException, ClassNotFoundException {

    connect();

    try {

      String sql = "DELETE FROM pattern WHERE pattern_id=?";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, Integer.parseInt(pattern.getId()));
      preparedStatement.executeQuery();
      log.addMessage(sql);

    } catch (SQLException e) {
      log.addMessage(e.getMessage());
    }

    return pattern;
  }

  public List<ULPatternSeverity> getAllSeverity() throws SQLException, ClassNotFoundException {

    List<ULPatternSeverity> severities = new LinkedList<ULPatternSeverity>();

    connect();

    try {
      String sql = "SELECT * FROM severity";
      log.addMessage(sql);

      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        ULPatternSeverity severity = new ULPatternSeverity();
        severity.setId(resultSet.getInt(1));
        severity.setName(resultSet.getString(2));
        severity.setTitle(resultSet.getString(3));
        severities.add(severity);
      }

    } catch (SQLException e) {
      log.addMessage(e.getMessage());
    }

    return severities;
  }

  public List<ULPatternCategory> getAllCategory() throws SQLException, ClassNotFoundException {

    List<ULPatternCategory> categories = new LinkedList<ULPatternCategory>();

    connect();

    try {
      String sql = "SELECT * FROM category";

      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        ULPatternCategory category = new ULPatternCategory();
        category.setId(resultSet.getInt(1));
        category.setName(resultSet.getString(2));
        category.setTitle(resultSet.getString(3));
        categories.add(category);
      }

    } catch (SQLException e) {
      log.addMessage(e.getMessage());
    }

    return categories;
  }


  public List<ULPatternReference> getAllReference() throws SQLException, ClassNotFoundException {

    List<ULPatternReference> references = new LinkedList<ULPatternReference>();

    connect();

    try {
      String sql = "SELECT * FROM reference";

      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        ULPatternReference reference = new ULPatternReference();
        reference.setId(resultSet.getInt(1));
        reference.setName(resultSet.getString(2));
        reference.setTitle(resultSet.getString(3));
        reference.setDescription(resultSet.getString(4));
        reference.setCitation(resultSet.getString(6));
        references.add(reference);
      }

    } catch (SQLException e) {
      log.addMessage(e.getMessage());
    }

    return references;
  }
}
