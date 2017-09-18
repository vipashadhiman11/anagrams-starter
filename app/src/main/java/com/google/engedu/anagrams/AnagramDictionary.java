/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    HashSet<String> wordSet = new HashSet<>();
    HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();
    public AnagramDictionary(Reader reader) throws IOException {

        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {

            String word = line.trim();
            wordSet.add(word);

            if (lettersToWord.containsKey(sortWords(word))) {
                ArrayList<String> temp = lettersToWord.get(sortWords(word));
                temp.add(word);
                lettersToWord.put(sortWords(word), temp);
            } else {
                ArrayList<String> newWord = new ArrayList<>();
                newWord.add(word);
                lettersToWord.put(sortWords(word), newWord);
            }
        }
        Log.e("HashMap structure",lettersToWord.toString());
    }

    public boolean isGoodWord(String word, String base) {
       return !word.contains(base)&& wordSet.contains(word);
    }

    public List<String> getAnagrams(String word) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> IntermediateResult = new ArrayList<String>();
        for(char i='a';i<='z';i++){
            String anagram= i+word;
            String sortedAnagram=sortWords(anagram);
            if(lettersToWord.containsKey(sortedAnagram)) {
                IntermediateResult = lettersToWord.get(sortedAnagram);
                for(String s: IntermediateResult) {
                    if (isGoodWord(s, word)) {
                        result.add(s);
                    }
                }
            }
        }

        return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        return result;
    }

    public String pickGoodStarterWord() {

    /*    ArrayList<String> arrayList= new ArrayList<>();
        ArrayList<String> lengthTheStarter= new ArrayList<>();
        String str="";
        lengthTheStarter = sizeToWords.get(wordLength);
        str=lengthTheStarter.get(random.nextInt(lengthTheStarter.size()));
        arrayList=getAnagramsWithOneMoreLetter(str);
        while (arrayList.size()<=MIN_NUM_ANAGRAMS){
            str=lengthTheStarter.get(random.nextInt(lengthTheStarter.size()));
            arrayList=getAnagramsWithOneMoreLetter(str);
        }
        if(wordLength< MAX_WORD_LENGTH){
            wordLength=wordLength+1;
        }
        else {
            wordLength= DEFAULT_WORD_LENGTH;
        }
      */
    int size= wordSet.size();
        int item= random.nextInt(size);
        int i=0;
        for(Object object: wordSet){
            if(i==item)
                return object.toString();
                //Log.e("in pick good starter",""+object.toString());
            i++;
        }
    }
    public String sortWords(String source){
        char[] sourceWords = source.toCharArray();
        Arrays.sort(sourceWords);
        return new String(sourceWords);
    }
}
