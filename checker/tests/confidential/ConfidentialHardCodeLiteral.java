package confidential;

import org.checkerframework.checker.confidential.qual.Confidential;

public class ConfidentialHardCodeLiteral {
  void hardCodedLiteral() {
    // This should fail to prevent leak in the VCS
    @Confidential String secret = "secret_key";
    @Confidential int pan = 123456;
    @Confidential Integer dob = 123456;
  }
}
