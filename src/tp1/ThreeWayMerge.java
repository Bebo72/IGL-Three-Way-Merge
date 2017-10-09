/**
 * 
 */
package tp1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Bebo
 *
 */
public class ThreeWayMerge
{
    /**
     * Constructeur par défaut
     */
    public ThreeWayMerge()
    {
        try
        {
            lectureLigne();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Methodes

    public void lectureLigne()
    {
        BufferedReader readerComparaisonA, readerComparaisonB, readerComparaisonOriginal;
        BufferedWriter writer = null;
        FileWriter fileWriter = null;
        String currentLineA, currentLineB, currentLineOriginal;

        try
        {
            // Initialisation des variables
            readerComparaisonA = new BufferedReader(new FileReader("CompareA"));
            readerComparaisonB = new BufferedReader(new FileReader("CompareB"));
            readerComparaisonOriginal = new BufferedReader(new FileReader("CompareOriginal"));
            fileWriter = new FileWriter("CompareSortie");
            writer = new BufferedWriter(fileWriter);

            // Boucle qui parcourt le fichier original tant qu'il y a de ligne
            while ((currentLineOriginal = readerComparaisonOriginal.readLine()) != null)
            {
                currentLineA = readerComparaisonA.readLine();
                currentLineB = readerComparaisonB.readLine();

                // Si fin fichier A et B
                if(currentLineA == null && currentLineB == null) {
                    writer.write(currentLineOriginal + "\n");
                    continue;
                }
                // Si fin fichier A mais pas B
                else if(currentLineA == null && currentLineB != null) {
                    writer.write(currentLineB + "\n");
                    continue;
                }
                // Si fin fichier B mais pas A
                else if(currentLineA == null && currentLineB != null) {
                    writer.write(currentLineA + "\n");
                    continue;
                }
                
                try
                {
                    // A = B
                    if (currentLineA.equals(currentLineB)) {
                        writer.write(currentLineA + "\n");
                        continue;
                    }
                }
                catch (NullPointerException e)
                {
                }
                try
                {
                    // A != B mais A non nul
                    if(!currentLineA.equals("") && currentLineB.equals("")) {
                        writer.write(currentLineA + "\n");
                        continue;
                    }
                }
                catch (NullPointerException e)
                {
                }
                try
                {
                    // A != B mais B non nul
                    if(currentLineA.equals("") && !currentLineB.equals("")) {
                        writer.write(currentLineB + "\n");
                        continue;
                    }
                }
                catch (NullPointerException e)
                {
                }
                try
                {
                    // A != B Conflit Merge manuel nécessaire
                    if(!currentLineA.equals(currentLineB))
                        throw new Exception("Merge manuel requis entre A et B");
                }
                catch (NullPointerException e)
                {
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                
                try
                {
                    if (currentLineA.equals("") && currentLineB.equals("") && !currentLineOriginal.equals(""))
                        writer.write(currentLineOriginal + "\n");
                }
                catch (NullPointerException e)
                {
                    if(currentLineA == null && currentLineB == null)
                    writer.write(currentLineOriginal + "\n");
                }
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (writer != null)
                    writer.close();
                if (fileWriter != null)
                    fileWriter.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Methode principale
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        ThreeWayMerge test = new ThreeWayMerge();
    }

}
