import org.checkerframework.checker.confidential.qual.Confidential;
import org.checkerframework.checker.confidential.qual.NonConfidential;

public class ConfidentialControlFlow {
  void ternary(boolean flag, @Confidential String conf, @NonConfidential String nonConf) {
    @NonConfidential String s1 = flag ? nonConf : nonConf;
    @Confidential String s2 = flag ? conf : conf;
    @Confidential String s3 = flag ? conf : nonConf;
    @Confidential String s4 = flag ? nonConf : conf;

    // :: error: [assignment]
    @NonConfidential String s5 = flag ? conf : nonConf;
    // :: error: [assignment]
    @NonConfidential String s6 = flag ? nonConf : conf;
  }

  void ternaryInt(boolean flag, @Confidential int conf, @NonConfidential int nonConf) {
    @NonConfidential int i1 = flag ? nonConf : nonConf;
    @Confidential int i2 = flag ? conf : nonConf;

    // :: error: [assignment]
    @NonConfidential int i3 = flag ? conf : nonConf;
  }

  void ifElseFlow(boolean flag, @Confidential String conf, @NonConfidential String nonConf) {
    @Confidential String confRes;
    if (flag) {
      confRes = conf;
    } else {
      confRes = nonConf;
    }
  }

  void ternaryAsArg(boolean flag, @Confidential String conf, @NonConfidential String nonConf) {
    sinkNonConf(flag ? nonConf : nonConf);

    // :: error: [argument]
    sinkNonConf(flag ? conf : nonConf);
  }

  void sinkNonConf(@NonConfidential String nonConf) {}
}
