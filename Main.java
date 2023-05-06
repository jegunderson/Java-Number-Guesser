import java.util.*; // use hashmap
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class

class Main {
    public static void main(String [] args) {

        Dictionary dictionary  = new Dictionary();

        int maxNumber = getMax();
        // Get random number from 0-designated max
        double randomDouble = Math.random() * (maxNumber + 1);
        int randomNum = (int)randomDouble;
        int userGuess = getGuess();
        dictionary.addGuess(userGuess);
        // System.out.print(randomNum);

        // Keep prompting until they guess the number
        while (userGuess != randomNum)
        {
            while (userGuess < randomNum)
            {
                System.out.print("Too low, guess again: ");
                userGuess = getGuess();
                dictionary.addGuess(userGuess);
                // dictionary.updateNumber();
                // System.out.print(dictionary.myDict);
            }
            while (userGuess > randomNum)
            {
                System.out.print("Too high, guess again: ");
                userGuess = getGuess();
                dictionary.addGuess(userGuess);
                // dictionary.updateNumber();
                // System.out.print(dictionary.myDict);
            }
        }

        System.out.println("You guessed it!");
        // Put final total of guesses in dictionary
        dictionary.updateNumber();
        dictionary.createFile();
        dictionary.writeToFile(dictionary.myDict);
    }


    public static int getMax() {
        
        // Need scanner to recieve input from user
        Scanner myObj = new Scanner(System.in);

        System.out.print("What should the maximum number be? ");

        String maxNumber = myObj.nextLine();

        // Convert userinput string to int
        int maxNum=Integer.parseInt(maxNumber); 

        System.out.println("You chose " + maxNumber);

        return maxNum;
    }

    public static int getGuess(){
        Scanner myObj = new Scanner(System.in);

        System.out.print("What is your guess? ");
        String userGuessString = myObj.nextLine();

        int userGuess = Integer.parseInt(userGuessString);

        return userGuess;
    }
}

// GET maxNum
// randomNum <- Math.random() * (maxNum + 1)
// GET useGuess
// IF userGuess != randomNum
//     IF userGuess < randomNum
//         println("Too low, guess again: ")

class Dictionary 
{
    HashMap myDict = new HashMap();
    // array to hold all guesses
    List<Integer> guesses = new ArrayList<Integer>();
    int numberOfGuesses = guesses.size();

    public Dictionary() {

        //  numberGuesses.add(0,guesses.size());
        // Fill dictionary
       myDict.put("guesses", guesses);
       myDict.put("number", numberOfGuesses);

    }
/// add guess to list of guesses
    public void addGuess(int guess)
    {
        guesses.add(guess);
    }

    public void updateNumber(){

        numberOfGuesses = guesses.size();
        myDict.put("number", numberOfGuesses);

    }

    public void createFile() {
        try {
          File dictFile = new File("guesses.txt");
          // If file created, tell user
          if (dictFile.createNewFile()) {
            System.out.println("File created: " + dictFile.getName());
          } else {
            // If it already exists tell user
            System.out.println("File already exists.");
          }
          // Show error if one occurs
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
      }

    public void writeToFile(HashMap myDict)
    {
            try {
              FileWriter myWriter = new FileWriter("guesses.txt");
              myWriter.write(myDict.toString());
              myWriter.close();
              System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
    }
    
}