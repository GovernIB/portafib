package org.fundaciobit.plugins.signature.api;

/**
 * 
 * @author anadal(u80067)
 *
 */
public class PropertyInfo {

  protected String key;

  protected String description;

  protected boolean optional;

  /**
   * Per comentaris usar #
   */
  protected String[] listOfAvailableValues;

  /**
   * Per comentaris usar #
   */
  protected String[] examples;

  public PropertyInfo() {
    super();
  }

  public PropertyInfo(String key, String description, boolean optional,
      String[] listOfAvailableValues, String[] examples) {
    super();
    this.key = key;
    this.description = description;
    this.optional = optional;
    this.listOfAvailableValues = listOfAvailableValues;
    this.examples = examples;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isOptional() {
    return optional;
  }

  public void setOptional(boolean optional) {
    this.optional = optional;
  }

  public String[] getListOfAvailableValues() {
    return listOfAvailableValues;
  }

  public void setListOfAvailableValues(String[] listOfAvailableValues) {
    this.listOfAvailableValues = listOfAvailableValues;
  }

  public String[] getExamples() {
    return examples;
  }

  public void setExamples(String[] examples) {
    this.examples = examples;
  }

}
