package se.lexicon.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ScannerInputService implements UserInputService{


    Scanner scanner;
    @Autowired
    public ScannerInputService() {
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
