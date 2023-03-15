package com.tradetactician.application.dependencies;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashing {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 512;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    private static final SecureRandom RAND = new SecureRandom();

    public static String generateSalt (final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return null;
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return String.valueOf(Base64.getEncoder().encodeToString(salt));
    }
    public static String hashPassword (String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return String.valueOf(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return null;

        } finally {
            spec.clearPassword();
        }
    }
    public static boolean verifyPassword (String password, String key, String salt) {
        String optEncrypted = hashPassword(password, salt);
        if (optEncrypted==null) return false;
        return optEncrypted.equals(key);
    }

    public static String generateRandomPassword(){
        String chars="yigPzpSqfWeSvUsPeZpN4q56uFftoS2PcBS2UqajDapJes7vr7\n" +
                "5Q2TwqPuo8Ii7bwuwECGMm11CPi1TOXKF8znES5LrdNRBx0MMf\n" +
                "6RKVobMia0GaoZCfUfmzcQGyYv7N2OJ2g9bs91xHZNgm6MYPcC\n" +
                "DUOXtcm9F6Ds2DE9fCsGGWwPR2wvi7r4rUMhSaY4FOYmqZVZWZ\n" +
                "VhDIxh0JHiKKP1TEZCa6hs6zMviZ8Lcks6yJPHJxohpa8ql0yB\n" +
                "EvtPG95zmjHhvJJW6EIoALVG8YW77EoY7d2AXlMJht731eMTlB\n" +
                "RiQOyFVTEh1qFiPFwQXfWrCyOBG3ZeCFSzTukzb4gSj8Xdwb5Q\n" +
                "N5W8yTA8Rlks2JbkrPAhSiL0mOkfiMCDS0wkZg3mkETDdy55IZ\n" +
                "VRzm4DMIQ8WMhGvmlFmPZYEeXSEpFgnPGCNUkujSnGpzP7H8h4\n" +
                "2UVzV3eNxQckklDzqYWJMGXkFwJ90awF9SJCs6QfrgRATe7IWw\n" +
                "94Afd1RF66OmPyWB5UelWwh4bOHXm6oTW6FoQB79xXgq6Y15TJ\n" +
                "EImWMvHbiz6A5OuikJojvPTzeA6SmNZGxLPY1UCe10uCYoMR2y\n" +
                "pe9zPFsmyqNmGpwKAwHXsFPwr8tcHPqAJbWr58b3RrFAJXyTTz\n" +
                "hxWiCYXmTE8jYk45WXh1RD2W60x78DRj1XyQwwDs76oOTYNAGd\n" +
                "SyQpC0mrgzfzOrtOyNGpbe7e3Gbsr0xS2ImpSlcQtoPlk8Vw6z\n" +
                "1OTG1CNgZcSG6BSUiHm8zL3JrBJdONMeMvBCliwtnAVT5sKzOO\n" +
                "OwImsDcJICPv28KvCszqU3OqKDnQQZuoQlS6IPFA2KnRRj7FjT\n" +
                "uyyJC1YauPN3fkgQHlcCbKVCCC2l672e9sb8SXscOPx8oryIKu\n" +
                "b3Wm5VdqDtKBZbC89BrfObJVurF3dE2ol4EtQHHF52pnzCXWog\n" +
                "QEFcuiK2RNR8qroFpUfNozUsBPrbZHk7cvQ3MSYPByAyZP8Ej0\n" +
                "QliQNMFSplXxgMaClCMAKdDWyzd50qhXRq9NuuQMzmERAaitXV\n" +
                "uXcrahf2xK9M5NSitLjfs3EiAcZ8yj37kv4T0QOEHXj5DMLhBK\n" +
                "q6JdLhvc2lXAtJVCdVtLIV8yxc3s8BJlYRMgDTKMwLmST1KikG\n" +
                "gUgBhxaaoBiSlFq2OK7Z4sIahvoR9a5OVIDXHBg9UqLshsCb6C\n" +
                "2A48SROJ41pj8b7URLOv9ied1lcRmLL18Gwudyd6bPZcV67VQx\n" +
                "uf5H3FD6EVFtF168tznsSe0Vdqmxht4vIBLt1ExrcGjtEFPwlH\n" +
                "8Kowqc5HakoBpjQ7WckJh4JycHvN8NgUsJgZQ81RvpvMBgtwqc\n" +
                "SoY8Q4Rf9T7fhLwiG3lAaOo28PbNWw6S6PnmGVjQGSUIcQEVqs\n" +
                "9HAclVKsvpiEdM8sogssX96Yh3H5HzgZglaF7orpLYctEqeq5V\n" +
                "DBbg7JKZBV3u0pOfcRK1GznjaNUntrcOw23NkN0QYbngZNkVJY\n" +
                "ru5FGEyfy8A1LEsXYeMouhTYcoAjKasum1DbyAFgwvinvw3nSt\n" +
                "WhO1vKIsoMxA87RzSWvZt06nFIo34g81VT2mmughJmt4Rjt0Hf\n" +
                "fJcNOWSKKTJ3BCyiJo7QhysAkpCwBAi3Vij4G8w8QgjHTyKaTj\n" +
                "vmXmeclnCcVWSDJTWnznzBTizGqzNEeNJ1JxrkK7UgAnKVS7Nw\n" +
                "BALG26D5hEyOteMNA3PMseJ3CuHMxSjnuJ3cRZ7bSGhFBYiFuH\n" +
                "HhRy1va4ewAk8baAVTRP091irR7VtinujudLFRSjaa0PqXwgeZ\n" +
                "sE9ia3cligP1ParFMhahjPMdTs1fPMV4R1m0YnkpaK6M6qbD2Y\n" +
                "aAKRqFheT1VVxNTJBq1bRyn9NIUqSqOGfUz8gX2pNBLHBXInZA\n" +
                "Af2cXJF7mBhpgOxsCrWdIkdommig9UUkDcbVRhfdzsg6FqdXMR\n" +
                "B9kn8DdENXACKLh7NIa4PsWMWUgZJ1Eoj6ykYWbXp4qRkaVMAX\n" +
                "pQG392KS0hAD515SwJh9cl3fFB1HyFUQ71xY3hJKwpfNQeDqmK\n" +
                "KnvvAj0jrsvS2Xp6WpynAbJ48fEvokTXNCFcnPlKVbPGO4WyOn\n" +
                "RAWAI3VL6KMylRigZ8cAiH8FnWMFh4hlTLfxbi2L7Dc1x0wevw\n" +
                "KiCpKk7YYEeY7SOZt0NRp7lzUlBgP6MaZ141gtfZcoYOgRn88n\n" +
                "XmcUQp5utbdPaJA5X7rf92nuqgTASGJVwYnahimQpjfFy0Sr1Y\n" +
                "qyeYIX61dvYW9Mli0Sz59qby0FxWPBwZbnmxzxN1ITAdgmBKZX\n" +
                "OFHrXP9zHB5CXylrUcPVUIsXUg6eL0PkXF8q4WJ5cn5xV8n6iY\n" +
                "rBYuF5C6YQv89DRgoDqtGzkQhFk8qg0GPlSFYcGMBIBJDS9PL3\n" +
                "WLcWsTq7ygtsAJwL1NMyBhM2dqAWZ1XSkoqSAXuEjejxc10GRV\n" +
                "aKe7akILiRsFHlfSEn6OMm64NvYM1UiPZQgBj3xJcaB1HeD74I\n" +
                "8yQSC04XDD1HVHZGhy06J2lirLZMbjPtCzxpSLUx8RUvG1YaMA\n" +
                "3MutgtlsMCjblqZNgHm7QqMLVYxmpnLNAjmiu8lBUuTnpXIHk8\n" +
                "gpwv9A2z31Oa50ZdzbRgxFXFv7PSCqX7A3kHxjE2fjamoQFZYz\n" +
                "Ypm9OFoKSkBSvlmltrQxUTLn3hR3btToTGtDsiKMd5EZ7QBkTf\n" +
                "d0cxWeKejdFHvC4kX6ZgIuYiNV1EI9jSiI4RVlY1CvoAL3I2gS\n" +
                "RXJF7g8mC83vw2H0kxLNrotbWYTNVKkreXrgc5xuel5HEQ5pGI\n" +
                "6Vgs8ctwvvQSv99nITh05Cpwi2NhufShoay9dIjbKSVudyf4ho\n" +
                "UQBYFxpSyq7GVjlV8oUupgTC4gmRJqyPxUq9vzN9SMCLHYafwZ\n" +
                "QxcotNqZVEwgzDtdsCkv6NCWtI4RW2kE3KHTj0dv3Z57GX4144\n" +
                "ztAMgLmvQ83L2qSQDbYqvpWbDupS5gn9BlFH387tFgIdhrH6V9\n" +
                "yXGOABxNN6L9RAPmwcQvL1zWtN1Cktp79Bdi5rMmmxEg2SmR7A\n" +
                "VyVEh6ATHxhpqhXRjkTHFnxeV6HrdPJ9Vt7AiUc0y59dXM8m6K\n" +
                "0juAP4lF82dIn5FyMeJCZIPBx7BzmAco8sZ8ye4EwtCj6dfDTk\n" +
                "cEiiiPD3kYF2AkkRykZCoIshgxd8EzzpOVmLVeGIpf859DYi0n\n" +
                "KOejWK5kba00SAGLX6tjyWcuwFQq6WMgTIvqgCU39Jcez4sBek\n" +
                "w0XYoLTkowc1Ufeg5jWzz5DeMDPPUfmkR8tgBBiODksnUOk9Rd\n" +
                "Solj18HGAwb8mGdsVYtx74yHzOgdebQgqcHGklCIezGXQ2dRzb\n" +
                "FPL5Yb1keeOX0Mx6lq5BwgOsTY05FvPMHz29IVRdAjacnLoCAr\n" +
                "dQB8NvbVkLsEQ6eiS3Nxk6zv7jPMCuyE7yC5sOS4lfSWxVI0On\n" +
                "RzE9OhbRVmcYXFkR7WIVCjdiaY1GKivISMWWuDBkgueyK3ViBd\n" +
                "d2GsQVIPjVOA9PwHNPnc3AFbsm3GKnRIPfBSbHcDoqbIR0NuCq\n" +
                "QQs7nhOQcqTage3lyWQEQMAQyGZ3xTErj1KAEvqBfVgEAePV3B\n" +
                "fAYpInYZHNAanRSc7I3oof2lyufhqzyFFzeUjUaXOhHmxSwST2\n" +
                "WaYt6htx0Ublvp6IPlPsgnheAYILPmR4unhPYTyqFtPr8JTwgh\n" +
                "mt275LI3KrmLa1x3I6JdcNZalbVXU5Qxa8JKlzJQm6R3Co367K\n" +
                "0mLrZPpNwHdBaEMbJcwYy6mlcoFEZGCxvbj3xvQ3qQlE9izN15\n" +
                "CggknV0p83edVdjnkqBzuqYiMrGo6IbMc17ORuk4lHkJ5CG1dS\n" +
                "RebgoxrhvM3WxiHphs09lLjwNhlrPqF09m83ccLhFaSUQA2OMg\n" +
                "v4aw6OjpI1X9xxqRB647IP50qHjxmWHTBSywZJByT1o2zgaT3c\n" +
                "crPagtp6vpkFEOdCPR4lO2iOHcTGDCHdfd6eshWFOpmNJVHQWo\n" +
                "8PJG6YPiCnGzqcA1w14YplyI4sR62fnLA5rVtifqGEGofCzV8a\n" +
                "6xYNjbf96eK4zZjwrkrhG5uQREVFfwf2eZvjkqGCXuDnUIOKxB\n" +
                "McCYseIZHiJoQTP5oMOlT5yvGgLsoUPUGKEZTcX7kcvWFcp3dh\n" +
                "RTJP10OEUzOQicTAn7TCIntodt0Rbmhr8OrxhYtY237h5Ejdz0\n" +
                "GDQaIMrhQUWZIUfcR4TIh93WYceVmJ00JsS9Ym0fEHP8vQfLHB\n" +
                "dtba1q1X0rPDIIfpyddGQCHZvvaSRcWKnI59bTKhjqSoAantpJ\n" +
                "aA4D7L8UCHqbS2T8KgdS1V0fqgOmQRyXwCXbpJAdsggNYXNkuh\n" +
                "2l51JA3TFraFZG9mLB4aoe4G4DG9XawRbyBe2EKIYA5pkxnGAC\n" +
                "9PxPDhDglnVEvyw7LaftjWhKN133Gwy8Wot4VSopGpflaz9a8f\n" +
                "SJy6aZg9F73MBM8dXQkzAewJuO2DJUOCAm7ELr0YbwOunZuAL5\n" +
                "YH5YbvumZ7CfFikcDy56KwcIsOMSnKpKM6yTcBXFcDnhwWV0as\n" +
                "VLHL7F4JixolZvKiJSYzAoj9v9heY5Ct7YaoGJxSLKCpfCEH8e\n" +
                "cYSc8r2bvokHuYTktcsKRBHzetqV93XGdrAmi63pqH2oduZYXc\n" +
                "oIOpST6DjylEHDYutV6ZXqNlQtjXVhiKNl6aavWvik9tSRd2qD\n" +
                "R3AgAyqmugWfocZJ2qDzD7rYJPjldgdNhEJ8uDU24MQiLDNelg\n" +
                "2BNIEZc7puePufThHnqCXqlTahEtDQU9SduIgvbbifRjtQ47M9\n" +
                "LlCkDk7vbdPoxH2Bo8JqRqMUj8RRcffzFoIVvifYh1TbCNOaBV\n" +
                "rnglOH0HbPZsX7fSvoEGJOMYvQB3Jv4zzYNi8iRbmWGJG0HsdM\n" +
                "yXLtpB8AM8GzRNlFrNd8ryD4y5FOgiPtkNrNBu8nrvcw1Zr5vF\n" +
                "QsHgsTkH6ogIxj2ZyM0RlYKzpR58jfzYrQEveSGYa4pAq30dyN";

        char[] arrayOfChars=chars.toCharArray();
        StringBuilder password= new StringBuilder();
        for (int i=0;i<12;i++){
            password.append(arrayOfChars[(int) ((Math.random() * (5000)) + 100)]);
        }
        return password.toString();
    }
}
