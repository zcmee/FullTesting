package zcmee.com.github.FullTesing.example1;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BeforeAfterExtension implements BeforeEachCallback, AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("Inside afterEach ExtensionContext");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("Inside beforeEach ExtensionContext");

    }
}
