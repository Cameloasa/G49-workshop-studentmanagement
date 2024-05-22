package se.lexicon.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerInputService implements UserInputService{


    //Dependency Injection
    private Scanner scanner;
    //Constructor
    @Autowired
    public ScannerInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getString() {

        return scanner.nextLine();
    }

    @Override
    public int getInt() {

        return scanner.nextInt();
    }
}
