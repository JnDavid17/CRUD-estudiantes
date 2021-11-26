
public class testeo {

   public static void main(String args[]) {
      RunnableDemo R1 = new RunnableDemo( "Primero");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Segundo");
      R2.start();
   }   
}