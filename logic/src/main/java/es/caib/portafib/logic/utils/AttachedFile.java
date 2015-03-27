package es.caib.portafib.logic.utils;

import java.io.File;

/**
 * 
 * @author anadal
 *
 */
public class AttachedFile {

  private String name;

  private File content;

  /**
   * 
   */
  public AttachedFile() {
    super();
  }

  /**
   * @param name
   * @param content
   */
  public AttachedFile(String name, File content) {
    super();
    this.name = name;
    this.content = content;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public File getContent() {
    return content;
  }

  public void setContent(File content) {
    this.content = content;
  }

}
