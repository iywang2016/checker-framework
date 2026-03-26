import org.checkerframework.checker.confidential.qual.Confidential;
import org.checkerframework.checker.confidential.qual.NonConfidential;

public class ConfidentialCasting {
  void castToConfidential(@NonConfidential String nonConf, @NonConfidential int nonConfInt) {
    // :: warning: [cast.unsafe]
    @Confidential String s = (@Confidential String) nonConf;
    // :: warning: [cast.unsafe]
    @Confidential int i = (@Confidential int) nonConfInt;
  }

  void castConfidentialToConfidential(@Confidential String conf) {
    @Confidential String s = (@Confidential String) conf;
  }

  void castToNonConfidential(@Confidential String conf, @Confidential int confInt) {
    // :: error: [assignment] :: warning: [cast.unsafe]
    @NonConfidential String s = (@NonConfidential String) conf;
    // :: error: [assignment] :: warning: [cast.unsafe]
    @NonConfidential int i = (@NonConfidential int) confInt;
  }

  void numericWidening(@Confidential int confInt, @NonConfidential int nonConfInt) {
    @Confidential long cl = confInt;
    @NonConfidential long ncl = nonConfInt;

    // :: error: [assignment]
    @NonConfidential long ncl2 = confInt;
  }

  void numericNarrowing(@Confidential long confLong, @NonConfidential long nonConfLong) {
    @Confidential int ci = (int) confLong;
    @NonConfidential int nci = (int) nonConfLong;

    // :: error: [assignment]
    @NonConfidential int nci2 = (int) confLong;
  }
}
