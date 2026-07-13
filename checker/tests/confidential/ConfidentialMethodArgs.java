import org.checkerframework.checker.confidential.qual.Confidential;
import org.checkerframework.checker.confidential.qual.NonConfidential;

public class ConfidentialMethodArgs {
  void testStringArgs(@Confidential String conf, @NonConfidential String nonConf) {
    sinkNonConf(nonConf);
    sinkConf(nonConf);
    sinkConf(conf);

    // :: error: [argument]
    sinkNonConf(conf);
  }

  void sinkNonConfInt(@NonConfidential int i) {}

  void sinkConfInt(@Confidential int i) {}

  void sinkNonConfInteger(@NonConfidential Integer i) {}

  void sinkConfInteger(@Confidential Integer i) {}

  void testIntArgs(@Confidential int confInt, @NonConfidential int nonConfInt) {
    sinkNonConfInt(nonConfInt);
    sinkConfInt(nonConfInt);
    sinkConfInt(confInt);

    // :: error: [argument]
    sinkNonConfInt(confInt);
  }

  void testIntegerArgs(@Confidential Integer confInt, @NonConfidential Integer nonConfInt) {
    sinkNonConfInteger(nonConfInt);
    sinkConfInteger(nonConfInt);
    sinkConfInteger(confInt);

    // :: error: [argument]
    sinkNonConfInteger(confInt);
  }

  void testBoxingArgs(@Confidential int confInt, @NonConfidential int nonConfInt) {
    sinkConfInteger(confInt);
    sinkNonConfInteger(nonConfInt);

    // :: error: [argument]
    sinkNonConfInteger(confInt);
  }

  void testLiteralArgs() {
    sinkNonConf("hello");
    sinkConf("hello");
    sinkNonConfInt(42);
    sinkConfInt(42);
  }

  @NonConfidential
  String returnNonConf(@NonConfidential String s) {
    return s;
  }

  @NonConfidential
  String returnNonConfError(@Confidential String s) {
    // :: error: [return]
    return s;
  }

  @Confidential
  String returnConf(@Confidential String s) {
    return s;
  }

  @Confidential
  String returnConfFromNonConf(@NonConfidential String s) {
    return s;
  }

  @NonConfidential
  int returnNonConfInt(@NonConfidential int i) {
    return i;
  }

  @NonConfidential
  int returnNonConfIntError(@Confidential int i) {
    // :: error: [return]
    return i;
  }

  @Confidential
  int returnConfInt(@Confidential int i) {
    return i;
  }

  void sinkNonConf(@NonConfidential String s) {}

  void sinkConf(@Confidential String s) {}
}

