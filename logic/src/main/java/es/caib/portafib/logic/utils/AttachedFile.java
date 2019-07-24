package es.caib.portafib.logic.utils;

import java.io.File;

/**
 * 
 * @author anadal
 *
 */
public class AttachedFile {

  private String name;

  private String description;

  private File content;

  /**
   * @param name
   * @param content
   */
  public AttachedFile(String name,File content) {
    this.name = name;
    this.content = content;
    this.description = name.substring(0, name.indexOf('.'));
  }

  public AttachedFile(String name, String description, File content) {
    this.name = name;
    this.description = description;
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public File getContent() {
    return content;
  }

  public void setContent(File content) {
    this.content = content;
  }

}
