package com.company;

import java.util.Scanner;

public class Main {

    private static int levenshteinDistance(CharSequence firstWord, CharSequence secondWord){

        if(secondWord.length() < firstWord.length()){
            CharSequence temp = firstWord;
            firstWord = secondWord;
            secondWord = temp;
        }

        int len1 = firstWord.length() + 1;
        int len2 = secondWord.length() + 1;

        int[] cost =  new int[len1];
        int[] newCost = new int[len2];

        for (int i = 0; i < len1; i++){
            cost[i] = i;
        }

        for (int j = 1; j < len2; j++){

            newCost[0] = j;

            for (int k = 1; k < len1; k++) {
                int match = (firstWord.charAt(k - 1) == secondWord.charAt(j-1)) ? 0 : 1;

                int cost_replace = cost[k-1] + match;
                int cost_insert = cost[k] + 1;
                int cost_delete = newCost[k-1] + 1;

                newCost[k] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
            }

            int[] swap = cost;
            cost = newCost;
            newCost = swap;
        }

        return cost[len1-1];
    }

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        System.out.print("\nEnter first word: \n");
        CharSequence firstInput = sc.nextLine();
        System.out.print("Enter second word: \n");
        CharSequence secondInput = sc.nextLine();


        int dist = levenshteinDistance(firstInput, secondInput);
        System.out.println("\nLevenshtein Distance = " + dist);

    }
}
