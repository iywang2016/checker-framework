import org.checkerframework.checker.confidential.qual.Confidential;
import org.checkerframework.checker.confidential.qual.NonConfidential;

public class ConfidentialArithmetic {
  void addition(@Confidential int confInt, @NonConfidential int nonConfInt) {
    @Confidential int r1 = confInt + nonConfInt;
    @Confidential int r2 = confInt + confInt;
    @NonConfidential int r3 = nonConfInt + nonConfInt;

    // :: error: [assignment]
    @NonConfidential int r4 = nonConfInt + confInt;
    // :: error: [assignment]
    @NonConfidential int r5 = confInt + nonConfInt;
    // :: error: [assignment]
    @NonConfidential int r6 = confInt + confInt;
  }

  void subtraction(@Confidential int confInt, @NonConfidential int nonConfInt) {
    @Confidential int r1 = confInt - nonConfInt;
    @NonConfidential int r2 = nonConfInt - nonConfInt;

    // :: error: [assignment]
    @NonConfidential int r3 = confInt - confInt;
    // :: error: [assignment]
    @NonConfidential int r4 = confInt - nonConfInt;
    // :: error: [assignment]
    @NonConfidential int r5 = nonConfInt - confInt;
  }

  void compoundAssignment(@Confidential int confInt, @NonConfidential int nonConfInt) {
    @NonConfidential int x = 0;
    x += nonConfInt;

    @Confidential int y = 0;
    y += confInt;
    y += nonConfInt;
  }

  void comparison(@Confidential int confInt, @NonConfidential int nonConfInt) {
    // :: error: [assignment]
    @NonConfidential boolean b1 = confInt > nonConfInt;
    // :: error: [assignment]
    @NonConfidential boolean b2 = confInt == nonConfInt;
    @NonConfidential boolean b3 = nonConfInt < nonConfInt;
  }

  void intStringConcatenation(
      @Confidential int confInt,
      @NonConfidential int nonConfInt,
      @Confidential String confStr,
      @NonConfidential String nonConfStr) {
    @Confidential String s1 = confStr + confInt;
    @NonConfidential String s2 = nonConfStr + nonConfInt;

    // :: error: [assignment]
    @NonConfidential String s3 = nonConfStr + confInt;
  }
}
