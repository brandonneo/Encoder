package Encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordsEncoding {

    public static class Words {

        private final String plainText;
        private final List<String> refTable;

        public Words(String refTable, String plainText) {
            this.plainText = plainText;
            this.refTable = Arrays.asList(refTable.split(""));
        }

        public String getPlainText() {
            return plainText;
        }

        public List<String> getRefTable() {
            return refTable;
        }

        // Convert the string char letter to index value
        public String encode() {
            String plainTextNoSpace = plainText.replace(" ", "");
            StringBuilder strNum = new StringBuilder();
            for (char c : plainTextNoSpace.toCharArray()) {
                int index = refTable.indexOf(String.valueOf(c));
                if (index != -1) {
                    strNum.append(index).append(",");
                }
            }
            return strNum.toString();
        }

        // Decode the string with an offset
        public String decode(String encodedText, String offset) {
            String[] encodedTextSplit = encodedText.split(",");
            int offsetIndex = refTable.indexOf(offset);
            StringBuilder finalString = new StringBuilder();
            for (String s : encodedTextSplit) {
                int num = Integer.parseInt(s);
                int newOffset = (num - offsetIndex + refTable.size()) % refTable.size();
                finalString.append(refTable.get(newOffset));
            }
            return finalString.toString();
        }

        // Assume only have one space in the plain text
        public String findSpaceIndex(String input) {
            int index = plainText.indexOf(" ");
            if (index == -1) {
                return input; // No space found
            }
            return new StringBuilder(input).insert(index, " ").toString();
        }
    }

    public static void main(String[] args) {
        String reftable = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";
        String plainText = "HELLO WORLD";
        Words obj = new Words(reftable, plainText);

        // Get text to index
        String encodedText = obj.encode();
        
        // Test decoding with offset B
        String decodedTextB = obj.decode(encodedText, "B");
        System.out.println(obj.findSpaceIndex(decodedTextB));
        
        // Test decoding with offset F
        String decodedTextF = obj.decode(encodedText, "F");
        System.out.println(obj.findSpaceIndex(decodedTextF));
    }
}
