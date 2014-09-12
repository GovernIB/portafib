package es.caib.portafib.hibernate;

import org.fundaciobit.genapp.common.crypt.FileIDEncrypter;

public class HibernateFileUtil {

    private static FileIDEncrypter encrypter = null;

    static {
      try {
        encrypter = new FileIDEncrypter("keyValuekeyValue".getBytes(),
            FileIDEncrypter.ALGORITHM_AES);
      } catch (Exception e) {
        System.err.println("Error instanciant File Encrypter: " + e.getMessage());
        e.printStackTrace(System.err);
      }
    }

    public static FileIDEncrypter getEncrypter() {
      return encrypter;
    }

    public static void setEncrypter(FileIDEncrypter encrypter) {
      HibernateFileUtil.encrypter = encrypter;
    }

    public static String encryptFileID(long fileID) {
      try {
        return encrypter.encrypt(String.valueOf(fileID));
      } catch(Exception e) {
        System.err.print("Error executant File Encrypter: " + e.getMessage());
        e.printStackTrace(System.err);
        return String.valueOf(fileID);
      }
    }

    public static long decryptFileID(String encryptedData)  {
      try {
        return Long.parseLong(encrypter.decrypt(encryptedData));
      } catch(Exception e) {
        System.err.print("Error executant File Decrypter: " + e.getMessage());
        e.printStackTrace(System.err);
        return 0;
      }
    }

}
