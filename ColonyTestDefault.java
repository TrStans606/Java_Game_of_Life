import java.io.FileNotFoundException;


public class ColonyTestDefault {
    public static void main(String[] args) throws FileNotFoundException {
      Colony colony = new Colony("colonydata1.txt");
      colony.display();
      System.out.println(colony.numOfLiving());

      for (int i = 0; i < 10; i++) {
         System.out.println("--------------------------");

         colony.next();
         colony.display();
         System.out.println(colony.numOfLiving());
      }
   }

}