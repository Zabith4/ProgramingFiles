/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package card;

/**
 *
 * @author nicholascicchetti
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

public class Deck
{

    private ArrayList<Card> theCards;
    private int nextCardIndex;

    public Deck()
    {
        theCards = new ArrayList<Card>(81);
        nextCardIndex = 0;
        for (int newCardQuantity = 1; newCardQuantity < 4; newCardQuantity++)
        {
            for (int newCardColor = 1; newCardColor < 4; newCardColor++)
            {
                for (int newCardShading = 1; newCardShading < 4; newCardShading++)
                {
                    for (int newCardShape = 1; newCardShape < 4; newCardShape++)
                    {
                        Card setCards = new Card(newCardQuantity, newCardColor, newCardShading, newCardShape);
                        theCards.add(setCards);
                    }
                }
            }
        }
        nextCardIndex = 0;
        Collections.shuffle(theCards);
    }

    public Deck(String filename)
    {
        // Create the arraylist so we have a place to put the cards
        theCards = new ArrayList<Card>(81);

        try
        {
            String line;
            BufferedReader infile = new BufferedReader(new FileReader(filename));
            int position = 0;

            while ((line = infile.readLine()) != null)
            {
                // Blank lines might contain white space, so trim it off
                line = line.trim();

        // ignore blank lines
                // continue will cause the program to stop executing this iteration
                // of the body and re-evaluate the condition.
                if (line.length() == 0)
                {
                    continue;
                }

                // ignore comments
                if (line.startsWith("#"))
                {
                    continue;
                }

        // a valid line contains 4 integers
                // The StringTokenizer auto-magically breaks a string
                // into parts
                StringTokenizer tokenizer = new StringTokenizer(line);

        // The tokenizer gives us strings, that have to be converted,
                // or parsed, into integers.  If the string isn't an integer,
                // it will through an exception
                int quantity = Integer.parseInt(tokenizer.nextToken());
                int color = Integer.parseInt(tokenizer.nextToken());
                int shading = Integer.parseInt(tokenizer.nextToken());
                int shape = Integer.parseInt(tokenizer.nextToken());

        // If there was not an exception in the parse, then we
                // can create the card and add it to the deck.
                theCards.add(new Card(quantity, color, shading, shape));
            }
        }
    // We catch exceptions here and through a different kind of exception.
        // This code will make sense by the end of the semester.  I promise!
        catch (Exception e)
        {
            throw new RuntimeException("Error while reading file: " + e.toString());
        }

    // The first card we take is in index 0 (no matter how many cards
        // were in the file).
        nextCardIndex = 0;

    }

    public boolean hasNext()
    {
        return nextCardIndex < theCards.size();
    }

    public Card getNext()
    {

        if (hasNext())
        {
            return theCards.get(nextCardIndex++);
        }
        else
        {
            return null;
        }
    }
}
