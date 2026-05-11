# The Pupil's Trial: Maps — The Word Frequency Analyzer

*"Build a text analysis engine that reveals the hidden patterns within words."*

---

## **Your Mission: Build a Text Analysis Engine**

### **Requirements**

#### **TextAnalyzer Class**
Using Maps as the core data structure, implement:

1. `Map<String, Integer> getWordFrequency(String text)` — count occurrences of each word (case-insensitive, strip punctuation)
2. `Map<Character, Integer> getCharFrequency(String text)` — count each character (ignore spaces)
3. `Map<Integer, List<String>> groupByWordLength(String text)` — group words by their length
4. `Map<String, List<Integer>> getWordPositions(String text)` — track all positions where each word appears
5. `List<Map.Entry<String, Integer>> getTopNWords(String text, int n)` — return the N most frequent words
6. `Map<String, String> buildIndex(String text)` — map each unique word to the sentence it first appears in
7. `double calculateSimilarity(String text1, String text2)` — Jaccard similarity: size of word intersection / size of word union

### **Demo Scenario**
1. Analyze a multi-sentence paragraph
2. Show top 10 most frequent words
3. Show word length distribution
4. Compare similarity between two texts
5. Build a word index

### **Sample Input**
Use a paragraph about programming (50+ words) for the demo.

---

*"A Map transforms chaos into knowledge. Words become counts, characters become frequencies, and raw text becomes insight."*
