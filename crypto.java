import java.util.Scanner;

public class crypto {

    public static void main(String[] args) {
        String normalized;
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter string you want to cypher! ");
        String text = input.nextLine();
        System.out.print("Please enter what shift you want to use! ");
        int shift = input.nextInt();
        System.out.print("How many letters do you want per group? ");
        int groupLength = input.nextInt();
        normalized = normalizeText(text);
        normalized = caesarify(normalized, shift);
        normalized = groupify(normalized, groupLength);
        System.out.println(normalized);
        String decrypted = decrypt(normalized, shift);
        System.out.println(decrypted);
    }

    public static String normalizeText (String text){
        String normalizing = text.replace(" ", "");
        normalizing = normalizing.replace("!", "");
        normalizing = normalizing.replace("?", "");
        normalizing = normalizing.replace(".", "");
        normalizing = normalizing.replace(",", "");
        normalizing = normalizing.replace(":", "");
        normalizing = normalizing.replace(";", "");
        normalizing = normalizing.replace("'", "");
        normalizing = normalizing.replace("\"", "");
        normalizing = normalizing.replace("(", "");
        normalizing = normalizing.replace(")", "");
        normalizing = normalizing.toUpperCase();
        return normalizing;
    }

    public static String caesarify(String cypher, int shift){
        String cyphered = cypher;
        for (int i = 0; i < 26; i++) {
            char beforeCypher = (char) (97 + i);
            String beforeCypherStr = String.valueOf(beforeCypher);
            String afterCypher = (97 + i + shift) + " ";
            cyphered = cyphered.replace(beforeCypherStr.toUpperCase(), afterCypher);
        }
        System.out.println(cyphered);
        for (int i = (97 + shift); i < (123 + shift); i++){
            if (i < 97){
                char replacement = (char)(123 - (97 - i));
                String replacementStr = String.valueOf(replacement);
                String beforeReplacement = i + " ";
                cyphered = cyphered.replace(beforeReplacement, replacementStr.toUpperCase());
            } else if (i > 122) {
                char replacement = (char)(96 + (i - 122));
                String replacementStr = String.valueOf(replacement);
                String beforeReplacement = i + " ";
                cyphered = cyphered.replace(beforeReplacement, replacementStr.toUpperCase());
            } else {
                char replacement = (char)i;
                String replacementStr = String.valueOf(replacement);
                String beforeReplacement = i + " ";
                cyphered = cyphered.replace(beforeReplacement, replacementStr.toUpperCase());
            }
        }
        return cyphered;
    }

    public static String groupify (String groups, int groupLength){
        int length = groups.length();
        String groups1 = "";
        if ((length % groupLength) > 0) {
            String numberOfX = "";
            for (int i = 0; i < groupLength - (length % groupLength); i++) {
                numberOfX = numberOfX + "x";
            }
            groups1 = new StringBuilder(groups).insert(length, numberOfX).toString();
            length += numberOfX.length();
        } else {
            groups1 = groups;
        }
        int groupNumber = length/groupLength;
        String groupedText = "";
        if (groupNumber == 1){
            groupedText = groups1;
        } else {
            for (int i = 1; i < groupNumber; i++) {
                int spaceLocation = length - (groupLength * i);
                groupedText = new StringBuilder(groups1).insert(spaceLocation, " ").toString();
                groups1 = groupedText;
            }
        }
        return groupedText;
    }

    public static String decrypt(String decrypting, int shift){
        String decryptStr = decrypting.replace("x", "");
        decryptStr = decryptStr.replace(" ", "");
        decryptStr = caesarify(decryptStr, -1 * shift);
        return decryptStr;
    }
}
