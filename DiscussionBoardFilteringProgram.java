/*
Mohamed Mahmoud
Discussion Board Filtering Program
 */

/*
Start of Program
 */

/*
All Java Util Libraries
 */

import java.util.*;

public class Discussion_Board {

    /*
     Testing ~ Different Discussion Boards
     Simulating the program
     */

    public static void main(String[] args) {
        Discussion_Board(input1);
        Discussion_Board(input2);

    }

    /*
     Test 1
     */

    static String[] input1 = {"David no no no no nobody never",
            "Jennifer why ever not",
            "Parham no not never nobody",
            "Shishir no never know nobody",
            "Alvin why no nobody",
            "Alvin nobody never know why nobody",
            "David never no nobody",
            "Jennifer never never nobody no"};

    /*
     Test 2
     */

    static String[] input2 = {"user1 doubledutch double double dutch",
            "user2 dutch doubledutch doubledutch double",
            "user3 not double dutch doubledutch"

    };

    /**
     * @pre : list of posts on Discussion Board
     * @post : Array list of the most used words on the Discussion Board in increasing order
     */

    public static ArrayList<String> Discussion_Board(String[] input) {

        /*
        [ User : [Word : Frequency] ]
        Tree Map used to represent the frequencies of the different words in the Discussion Board
        Temporary user used as a dummy to keep track of repeated words
         */

        HashMap<String,HashMap<String,Integer>> outer =new HashMap<>();
        TreeMap<String,Integer> frequency =new TreeMap<>();
        String TemporaryUser = " ";

        /*
        Initializing the inner hash map used to keep track of the word and its frequency
         */

        /*
        Iterate over all the elements in the input String Array
        Hash Map for Word and its Frequency
        Temporary string used to be able to access words of each post
        User is always the first word of a string
         */

        for (int i = 0; i < input.length; i++) {

            HashMap<String,Integer> inner =new HashMap<>();
            String [] temporary = input[i].split(" ");
            TemporaryUser = temporary[0];

            /*
             Inner hashmap ~ Individual posts
             Iterate over all the elements in every post
             Conditional to check if the inner hash map contains the current word
             If it does contain it, then increment its frequency value by 1
             Case in which the conditional fails
             If it doesn't contain it, then create a new <key,value> pair with the word and a starting value of 1
             */

            for(int j=1;j<temporary.length;j++ ) {
                    if (inner.containsKey(temporary[j])) {
                        inner.put(temporary[j], inner.get(temporary[j]) + 1);

                    } else {
                        inner.put(temporary[j], 1);
                    }
                }

            /*
            Outer hashmap ~ Discussion Board
            Outer conditional to check if the Person has made a post previously
            Iterating over the words that are stored in the key values of the inner hashmap
            Inner conditional to check if the Person who made the post used that word in his post
            Add the value of the current frequency of the word to the new value
            Case in which the inner conditional fails
            If the Person who made the post doesn't have the word in his post, add it to his list of words
            Case in which the outer conditional fails
            If the person hasn't made a post previously, then add the words and their frequencies to the outer hash map
             */

            if(outer.containsKey(temporary[0])){
                for(String bug : inner.keySet() ) {
                    if(outer.get(temporary[0]).containsKey(bug)){
                        outer.get(temporary[0]).put(bug,inner.get(bug)+outer.get(temporary[0]).get(bug));
                    }
                    else{
                        outer.get(temporary[0]).put(bug, inner.get(bug));
                    }
                }
            }else{
                outer.put(temporary[0], inner);
            }
        }

        /*
        @pre : Finalized hash map containing the [ Person : [Word : Frequency] ]
        @post : Finalized frequency hash map that contains each word repeated in all posts and its frequency
         */

        /*
        Arraylist of boolean variables to check if the word in question was repeated in all posts
        Arraylist of Strings that are not repeated in all posts
         */

        ArrayList<Boolean> chekingValidity = new ArrayList<Boolean>();
        ArrayList<String> doesntContain = new ArrayList<String>();

        /*
        Iterating over all the keys in the outer hash map
        Conditional to check if the person in question is the temporary user defined above
        Iterating over all the words the person in question used in his post
        Conditional to check if the Person in question hasn't used the word in his post
        If the word wasn't used in his post, add it to the Arraylist of words that are not repeated in all posts
         */

        for(String key : outer.keySet()){
            if(key!=TemporaryUser){
                for(String innerWords : outer.get(TemporaryUser).keySet()){
                    if(!outer.get(key).containsKey(innerWords)){
                        doesntContain.add(innerWords);
                    }
                }
            }
        }

        /*
        Iterating over all the People that have posted in the discussion board
        Outer conditional to check if the person in question is not the temporary user defined above
        Iterating over all the words the person in question used in his post
        Middle conditional to check if the word in question is not part of the Arraylist of words not used in all posts
        Inner conditional to check if the frequency contains the word in question
        If it does contain it, then add it to the frequency of the current word
        Case in which the inner conditional fails
        If it doesn't contain it, then add the word
         */

        for(String key : outer.keySet()) {
            if (key != TemporaryUser) {
                for (String innerWords : outer.get(TemporaryUser).keySet()) {
                    if (!doesntContain.contains(innerWords)) {
                        if (frequency.containsKey(innerWords)) {
                            frequency.put(innerWords, outer.get(key).get(innerWords) + frequency.get(innerWords));
                        }else{
                            frequency.put(innerWords, outer.get(key).get(innerWords));
                        }
                    }

                }
            }

            /*
            Case in which the middle conditional fails
            Iterate over all the words in the words used by a person
            Conditional to check if the word is not a part of the words that are not used in all posts
            Conditional to check if frequency has the word in question
            If it does have the word, then add the frequency to the frequency of the word in question
            Case when the conditional fails
            If the frequency doesn't have the word in question, then add it to the frequencies
             */

            else{
                for (String innerWords : outer.get(TemporaryUser).keySet()) {
                    if (!doesntContain.contains(innerWords)) {
                        if (frequency.containsKey(innerWords)) {
                            frequency.put(innerWords, outer.get(TemporaryUser).get(innerWords) + frequency.get(innerWords));
                        }else{
                            frequency.put(innerWords, outer.get(TemporaryUser).get(innerWords));
                        }
                    }

                }
            }
        }

        /*
        Sorting Algorithm to return the Arraylist of frequencies in order
        Arraylist used to represent final result
        Sorting
        Return the final result
        */

        ArrayList<String> result = new ArrayList<>(frequency.keySet());
        result.sort((a, b) -> frequency.get(b) - frequency.get(a));
        return result;
    }
}


