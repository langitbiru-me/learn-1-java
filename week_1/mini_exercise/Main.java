package week_1.mini_exercise;

import java.io.*;
import java.util.Scanner;


public class Main {
   private static final String FILENAME = "notes.txt";

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Pilihan menu :");
                System.out.println("1. Tambah Note");
                System.out.println("2. Lihat Note");
                System.out.println("3. Hapus semua note");
                System.out.println("4. Keluar");
                System.out.print("Masukkan pilihan: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Masukkan note: ");
                        String note = scanner.nextLine();
                        saveNote(note);
                        break;
                    case "2":
                        readNotes();
                        break;
                    case "3":
                        clearNotes();
                        break;
                    case "4":
                        System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                        return; // Exit the program
                    default:
                        System.out.println("Pilihan tidak valid!"); 
                        break;
                }
            }
        }
    }


  // Fungsi untuk menambah note (Append mode: true)
    private static void saveNote(String note) {
        try (FileWriter fw = new FileWriter(FILENAME, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(note);
            System.out.println("Note berhasil disimpan!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    // func untuk read notes
    private static void readNotes(){
        System.out.println("Daftar Note:");
        try (BufferedReader btr = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = btr.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Belum ada note yang disimpan.");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }

    // clear notes
    private static void clearNotes() {
        try (PrintWriter pw = new PrintWriter(FILENAME)){
            pw.print("");
            System.out.println("Semua note berhasil dihapus!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menghapus note: " + e.getMessage());
        }
    }

}
