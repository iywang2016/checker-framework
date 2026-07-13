import org.checkerframework.checker.confidential.qual.Confidential;
import org.checkerframework.checker.confidential.qual.NonConfidential;

public class ConfidentialAssignment {
  void stringAssignments(@Confidential String conf, @NonConfidential String nonConf) {
    @Confidential String s1 = nonConf;
    @Confidential String s3 = conf;
    @NonConfidential String s4 = nonConf;

    // :: error: [assignment]
    @NonConfidential String s2 = conf;
  }

  void literalDefaults() {
    // Literals default to @NonConfidential
    @NonConfidential String s = "hello";
    @Confidential String cs = "hello";
  }

  void intAssignments(@Confidential int confInt, @NonConfidential int nonConfInt) {
    @Confidential int i1 = nonConfInt;
    @Confidential int i3 = confInt;
    @NonConfidential int i4 = nonConfInt;

    // :: error: [assignment]
    @NonConfidential int i2 = confInt;
  }

  void integerAssignments(@Confidential Integer confInt, @NonConfidential Integer nonConfInt) {
    @Confidential Integer i1 = nonConfInt;
    @Confidential Integer i3 = confInt;
    @NonConfidential Integer i4 = nonConfInt;

    // :: error: [assignment]
    @NonConfidential Integer i2 = confInt;
  }

  void boxing(@Confidential int confInt, @NonConfidential int nonConfInt) {
    @Confidential Integer boxed1 = confInt;
    @NonConfidential Integer boxed2 = nonConfInt;

    // :: error: [assignment]
    @NonConfidential Integer boxed3 = confInt;
  }

  void unboxing(@Confidential Integer confInteger, @NonConfidential Integer nonConfInteger) {
    @Confidential int unboxed1 = confInteger;
    @NonConfidential int unboxed2 = nonConfInteger;

    // :: error: [assignment]
    @NonConfidential int unboxed3 = confInteger;
  }

  void intLiteralDefaults() {
    @NonConfidential int i = 42;
    @Confidential int ci = 42;
    @NonConfidential Integer bi = 42;
    @Confidential Integer cbi = 42;
  }

  void reassignment(@Confidential String conf, @NonConfidential String nonConf) {
    @Confidential String s = nonConf;
    s = conf;

    @NonConfidential String s2 = nonConf;
    // :: error: [assignment]
    s2 = conf;
  }
}
