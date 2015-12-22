package org.fundaciobit.plugins.signatureweb.miniappletinserversia;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class Borrar extends Thread {

  
  final Semaphore semafor;
  
  final int pos;
  /**
   * 
   */
  public Borrar(Semaphore semafor, int pos) {
    super();
    this.semafor = semafor;
    this.pos = pos;
  }

 
  @Override
  public void run() {
    
    System.out.println(" Entrat a POS[" + pos + "] (Esperant ...)");
    
    try {
      if (!semafor.tryAcquire(20, TimeUnit.SECONDS)) {
        System.out.println(" Superat temps màxim per POS[" + pos + "]");
      } else {
        System.out.println(" Processada tasca per [" + pos + "]");
      }
    } catch (InterruptedException e) {
      System.out.println("Tasca [" + pos + "] ha sigut cancelada");
    }
    
  }
  
  
  public static void main(String[] args) {
    
    try {
      Semaphore s = new Semaphore(0);
      
      for (int i = 0; i < 10; i++) {
        Borrar b = new Borrar(s, i);
        b.start();
      }
      
      for (int i = 0; i < 10; i++) {
        System.out.println(" Allibera un thread pitjant return ...");
       
          System.in.read();
       
        System.in.read(new byte[System.in.available()]);
        s.release();
      }
      
      System.out.println(" FINAL ");
    
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  
  
  
  
  
}
