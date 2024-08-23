import org.checkerframework.checker.confidential.qual.NonConfidential;

public class SimpleConfidential {

  void executeNonConfidential(@NonConfidential String s) {}

  void executeConfidential(String s) {}

  void nonConfidentialRef(@NonConfidential String s) {
    executeNonConfidential(s);
    executeConfidential(s);
  }

  void confidentialRef(String s) {
    // :: error: (argument)
    executeNonConfidential(s);
    executeConfidential(s);
  }
}