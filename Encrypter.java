import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	String content = readFile(inputFilePath);
        System.out.println("Original Content: " + content);
        String encryptedText = encrypt(content, shift);
        System.out.println("Encrypted Content: " + encryptedText);
        writeFile(encryptedText, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	String content = readFile(messageFilePath);
        System.out.println("Encrypted Content: " + content);
        String decryptedText = decrypt(content, shift);
        System.out.println("Decrypted Content: " + decryptedText);
        writeFile(decryptedText, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        //String message = "";
        //TODO: Read file from filePath
        StringBuilder message = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                message.append(scanner.nextLine()).append("\n");
            }
        } catch (IOException e) {
            throw new Exception("Error reading file: " + e.getMessage());
        }
        return message.toString();
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    private static String encrypt(String text, int shift) {
    	StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char encryptedChar = (char) (((ch - base + shift) % 26) + base);
                result.append(encryptedChar);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    /**
     * Decrypts the given text using a shift value.
     *
     * @param text  the text to be decrypted
     * @param shift the shift value for decryption
     * @return the decrypted text
     */
    private static String decrypt(String text, int shift) {
    	return encrypt(text, 26 - shift); // Decrypting is like encrypting with the opposite shift
    
    }

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
