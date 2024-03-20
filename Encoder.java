import java.util.Scanner; 

public class Encoder {
    static Scanner scanner = new Scanner(System.in); 
    static char offsetChar;
    public static void main(String[] args) {
        // Create an instance of Encoder
        Encoder encoder = new Encoder(); 
        encoder.run();
    }
    
    public void run() {
        // Set isValidInput is false
        boolean isValidInput = false;
        System.out.print("Enter character: ");
        // Read user input and take the first character
        offsetChar = Character.toUpperCase(scanner.nextLine().charAt(0));   
        
        // Check if the input character is in the reference table
        if (getIndex(offsetChar) != -1) {
            // Exit the loop when isValidInput is true
            isValidInput = true; 
            System.out.println("The entered character is: " + offsetChar);
        } else {
            System.out.println("Invalid character!");
        }
        // Prompt the user to enter text to encode
        System.out.print("Enter text to encode: ");
        String inputPlaintext =  scanner.nextLine().toUpperCase();
        // Call out the encode function
        String newEncodedText = encode(inputPlaintext);
        // Print out the encoded text
        System.out.println("Encoded text: " + offsetChar + newEncodedText);
        // Clear the buffer
        System.out.println();
        // Prompt the user to enter text to decode
        System.out.print("Enter text to decode: ");
        String inputDecodedText =  scanner.nextLine().toUpperCase();
        // Call out the decode function
        String decodedText = decode(inputDecodedText);
        // Print out the decoded text
        System.out.println("Decoded text: " + decodedText );
        scanner.close();
    }
    
    // Reference Table
    static char[] referenceTable = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '(', ')', '*', '+', ',', '-', '.', '/'
    };

    // Get the index of the character
    public static int getIndex(char ch) {
        // Convert char array to string
        String referenceString = new String(referenceTable); 
        // Get the index of the character in the string
        return referenceString.indexOf(ch);
    }

    // encode function
    public String encode(String plainText) {
        // Find the index of the offset character
        int offsetIndex = getIndex(offsetChar);
    
        // Create a new StringBuilder to store the encoded text
        StringBuilder newEncodedText = new StringBuilder();
    
        // Calculate the new reference table with the shifted characters
        char[] shiftedTable = new char[referenceTable.length];
        // Loop through each element in the reference table
        for (int i = 0; i < referenceTable.length; i++) {
            // Calculate the new index for the current element after shifting
            int newIndex = (i + offsetIndex) % referenceTable.length;
            // Check if it is positive 
            if (newIndex < 0) {
                newIndex += referenceTable.length;
            }
            // Assign the shifted character to the newIndex position in the shiftedTable
            shiftedTable[newIndex] = referenceTable[i];
        }
    
        // Iterate through each character in the plaintext
        for (char ch : plainText.toCharArray()) {
            // Check if the character is in the reference table
            int index = getIndex(ch);
            // if index is positive
            if (index != -1) {
                // Append the shifted character to the encoded text
                newEncodedText.append(shiftedTable[index]);
            } else {
                // If the character is not in the reference table, append it as it is
                newEncodedText.append(ch);
            }
        }
        // return the encoded text in string format
        return newEncodedText.toString();
    }

    // decode function
    public String decode(String encodedText) {
        // Find the offset character from the encoded text
        char offsetChar = encodedText.charAt(0); // Offset character
    
        // Find the index of the offset character
        int offsetIndex = getIndex(offsetChar);
    
        // Calculate the shift table with the shifted characters
        char[] shiftTable = new char[referenceTable.length];
        // Loop elements in the reference table
        for (int i = 0; i < referenceTable.length; i++) {
            int newIndex = (i - offsetIndex + referenceTable.length) % referenceTable.length;
            shiftTable[newIndex] = referenceTable[i];
        }
    
        // Create a new StringBuilder to store the decoded text
        StringBuilder decodedText = new StringBuilder();
    
        // Iterate through each character in the encoded text starting from index 1 (skipping the offset character)
        for (int i = 1; i < encodedText.length(); i++) {
            char ch = encodedText.charAt(i);
            // Check if the character is in the shift table
            int index = getIndex(ch);
            if (index != -1) {
                // Append the original character to the decoded text
                decodedText.append(shiftTable[index]);
            } else {
                // If the character is not in the shift table, append it as it is
                decodedText.append(ch);
            }
        }
        // return decoded text in string format
        return decodedText.toString();    
    }    
}