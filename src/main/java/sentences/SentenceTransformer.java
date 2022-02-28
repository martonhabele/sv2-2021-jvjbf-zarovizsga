package sentences;

public class SentenceTransformer {
    public String shortenSentence(String sentence) {
        String shortenedSentence = sentence;
        if (isValidSentence(sentence)) {
            String[] words = sentence.split(" ");
            if (words.length >= 5) {
                shortenedSentence = words[0] + " ... " + words[words.length - 1];
            }
        }
        return shortenedSentence;
    }

    private boolean isValidSentence (String sentence) {
        char firstLetter = sentence.charAt(0);
        char lastLetter = sentence.charAt(sentence.length() - 1);

        if (!Character.isUpperCase(firstLetter)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
        if (lastLetter != '.' && lastLetter != '!' && lastLetter != '?') {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
        return true;
    }
}