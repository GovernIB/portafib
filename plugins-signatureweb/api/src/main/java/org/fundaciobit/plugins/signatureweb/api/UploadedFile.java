package org.fundaciobit.plugins.signatureweb.api;

/**
 * 
 * @author anadal
 *
 */
public class UploadedFile {

  /**
   * Return the name of the parameter in the multipart form.
   * 
   * @return the name of the parameter (never <code>null</code> or empty)
   */
  String formName;

  /**
   * Return the original filename in the client's filesystem.
   * <p>
   * This may contain path information depending on the browser used, but it
   * typically will not with any other than Opera.
   * 
   * @return the original filename, or the empty String if no file has been
   *         chosen in the multipart form, or <code>null</code> if not defined
   *         or not available
   */
  String originalFilename;

  /**
   * Return the content type of the file.
   * 
   * @return the content type, or <code>null</code> if not defined (or no file
   *         has been chosen in the multipart form)
   */
  String contentType;

  /**
   * Return the size of the file in bytes.
   * 
   * @return the size of the file, or 0 if empty
   */
  long size;

  /**
   * Return the contents of the file as an array of bytes.
   * 
   * @return the contents of the file as bytes, or an empty byte array if empty
   * @throws IOException
   *           in case of access errors (if the temporary store fails)
   */
  byte[] bytes;

  /**
     * 
     */
  public UploadedFile() {

  }

  /**
   * @param name
   * @param originalFilename
   * @param contentType
   * @param size
   * @param bytes
   */
  public UploadedFile(String formName, String originalFilename, String contentType, long size,
      byte[] bytes) {
    super();
    this.formName = formName;
    this.originalFilename = originalFilename;
    this.contentType = contentType;
    this.size = size;
    this.bytes = bytes;
  }

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getOriginalFilename() {
    return originalFilename;
  }

  public void setOriginalFilename(String originalFilename) {
    this.originalFilename = originalFilename;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public byte[] getBytes() {
    return bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

}
