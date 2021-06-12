package numbers;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] input;
        long firstDigit = 0;
        long secondDigit = 0;
        boolean isRunning = true;
        String[] properties;
        String request = "Enter a request:";
        String instruction = "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.";
        String firstParameterError = "The first parameter should be a natural number or zero.\\n";
        String secondParameterError = "The second parameter should be a natural number or zero.\\n";


        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println(instruction);
        System.out.println(request);

        input = scanner.nextLine().split(" ");

        while (isRunning) {
            if (input[0].equals("0")) {
                break;
            } else if (input[0].equals("")) {
                System.out.println(instruction);
                System.out.println(request);
            } else if (input.length == 1) {
                firstDigit = Long.parseLong(input[0]);
                if (firstDigit > 0) {
                    printResult(firstDigit, 0);
                    System.out.println(request);
                } else {
                    System.out.println(firstParameterError);
                    System.out.println(request);
                }
            } else if (input.length == 2) {
                firstDigit = Long.parseLong(input[0]);
                secondDigit = Long.parseLong(input[1]);
                if (firstDigit > 0) {
                    if (secondDigit > 0) {
                        printResult(firstDigit, secondDigit);
                        System.out.println(request);
                    } else {
                        System.out.println(secondParameterError);
                        System.out.println(request);
                    }
                } else {
                    System.out.println(firstParameterError);
                    System.out.println(request);
                }
            } else {
                firstDigit = Long.parseLong(input[0]);
                secondDigit = Long.parseLong(input[1]);
                if (firstDigit > 0) {
                    if (secondDigit > 0) {
                        properties = new String[input.length - 2];
                        System.arraycopy(input, 2, properties, 0, input.length - 2);
                        printResult(firstDigit, secondDigit, properties);
                        System.out.println(request);
                    } else {
                        System.out.println(secondParameterError);
                        System.out.println(request);
                    }
                } else {
                    System.out.println(firstParameterError);
                    System.out.println(request);
                }
            }
            //input = null;
            input = scanner.nextLine().split(" ");
            if (input[0].equals("0")) {
                isRunning = false;
            }
        }

        System.out.println("Goodbye!\n");
    }

    public static void printResult(long firstDigit, long secondDigit) {
        if (secondDigit > 0) {
            for (int i = 0; i < secondDigit; i++) {
                String toPrint = firstDigit + " is ";
                HashMap<String, Boolean> map = new HashMap<>();
                map.put("even", (isEven(firstDigit)));
                map.put("odd", (isOdd(firstDigit)));
                map.put("buzz", (isBuzz(firstDigit)));
                map.put("duck", (isDuck(firstDigit)));
                map.put("palindromic", (isPalindrome(firstDigit)));
                map.put("gapful", (isGapful(firstDigit)));
                map.put("spy", (isSpy(firstDigit)));
                map.put("square", (isSquare(firstDigit)));
                map.put("sunny", (isSunny(firstDigit)));
                map.put("jumping", (isJumping(firstDigit)));

                for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                    if (entry.getValue()) {
                        toPrint += entry.getKey() + ", ";
                    }
                }
                System.out.println(toPrint.substring(0, toPrint.length() - 2));
                firstDigit++;
            }

        } else if (secondDigit == 0) {
            System.out.println("Properties of " + firstDigit + "\n" +
                    "even: " + (isEven(firstDigit)) + "\n" +
                    "odd: " + (isOdd(firstDigit)) + "\n" +
                    "buzz: " + (isBuzz(firstDigit)) + "\n" +
                    "duck: " + isDuck(firstDigit) + "\n" +
                    "palindromic: " + isPalindrome(firstDigit) + "\n" +
                    "gapful: " + isGapful(firstDigit) + "\n" +
                    "spy: " + isSpy(firstDigit) + "\n" +
                    "square: " + isSquare(firstDigit) + "\n" +
                    "sunny: " + (isSunny(firstDigit)) + "\n" +
                    "jumping: " + (isJumping(firstDigit)));
        }
    }

    public static void printResult(long firstDigit, long secondDigit, String[] properties) {
        String availableProperties = "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]";
        long foundNumbers = 0;

        if (!isRightProperties(properties)) {
            System.out.println(wrongPropertiesError(properties));
            System.out.println(availableProperties);
            return;
        }

        if (isMutuallyExclusiveProperties(properties)) {
            System.out.println(mutuallyExclusivePropertiesError(properties));
            return;
        }

        while (foundNumbers != secondDigit) {

            if (isRightNumber(properties, firstDigit)) {
                printResult(firstDigit, 1);
                foundNumbers++;
            }
            firstDigit++;
        }
    }

    public static boolean isRightNumber(String[] properties, long number) {
        Map<String, Boolean> resultMap = new HashMap<>();

        resultMap.put("even", isEven(number));
        resultMap.put("odd", isOdd(number));
        resultMap.put("buzz", isBuzz(number));
        resultMap.put("duck", isDuck(number));
        resultMap.put("palindromic", isPalindrome(number));
        resultMap.put("gapful", isGapful(number));
        resultMap.put("spy", isSpy(number));
        resultMap.put("square", isSquare(number));
        resultMap.put("sunny", isSunny(number));
        resultMap.put("jumping", isJumping(number));

        int counter = 0;

        for (String prop : properties) {
            if (resultMap.get(prop.toLowerCase(Locale.ROOT))) {
                counter++;
            }
        }
        return counter == properties.length;
    }

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(long number) {
        return number % 2 != 0;
    }

    public static boolean isBuzz(long number) {
        String s = String.valueOf(number);
        long lastNumber = Long.parseLong(String.valueOf(s.charAt(s.length() - 1)));
        return (lastNumber == 7 || number % 7 == 0);
    }

    public static boolean isDuck(long number) {
        String s = String.valueOf(number);
        return s.contains("0");
    }

    public static boolean isPalindrome(long number) {
        long reverseNumber = 0;
        String s = String.valueOf(number);
        StringBuilder sb = new StringBuilder(s);
        try {
            reverseNumber = Long.parseLong(sb.reverse().toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return number == reverseNumber;
    }

    public static boolean isGapful(long number) {
        if (number < 100) {
            return false;
        }
        String s = String.valueOf(number);
        long firstAndLast = Long.parseLong(String.valueOf(s.charAt(0)) + String.valueOf(s.charAt(s.length() - 1)));
        return number % firstAndLast == 0;
    }

    public static boolean isSpy(long number) {
        long numToSplit = number;
        long multiplyResult = 1;
        long additionResult = 0;
        ArrayList<Long> digits = new ArrayList<>();
        while (numToSplit > 0) {
            digits.add(numToSplit % 10);
            numToSplit = numToSplit / 10;
        }
        for (Long x : digits) {
            multiplyResult *= x;
            additionResult += x;
        }
        return multiplyResult == additionResult;
    }

    public static boolean isSquare(double x) {
        double sr = Math.sqrt(x);
        return ((sr - Math.floor(sr)) == 0);
    }


    public static boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    public static boolean isJumping(long number) {
        int counter = 1;
        if (number < 10) {
            return true;
        }
        String s = String.valueOf(number);
        String[] digits = s.split("");
        for (int i = 0; i < digits.length - 1; i++) {
            long firstDigit = Long.parseLong(digits[i]);
            long secondDigit = Long.parseLong(digits[i + 1]);
            if (firstDigit - 1 == secondDigit || firstDigit + 1 == secondDigit) {
                counter++;
            }
        }
        return counter == digits.length;
    }

    public static boolean isRightProperties(String[] properties) {
        String availableProperties = "EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING";
        for (String x : properties) {
            if (!availableProperties.contains(x.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    public static String wrongPropertiesError(String[] properties) {
        StringBuilder result = new StringBuilder();
        ArrayList<String> wrongProperties = new ArrayList<>();


        String availableProperties = "EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING";
        for (String x : properties) {
            if (!availableProperties.contains(x.toUpperCase())) {
                wrongProperties.add(x);
            }
        }
        if (wrongProperties.size() > 1) {
            result.append("The properties [");
        } else {
            result.append("The property [");
        }

        for (String x : wrongProperties) {
            result.append(x.toUpperCase(Locale.ROOT));
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());

        if (wrongProperties.size() > 1) {
            result.append("] are wrong.");
        } else {
            result.append("] is wrong.");
        }
        return result.toString();

    }

    public static boolean isMutuallyExclusiveProperties(String[] properties) {
        if (Arrays.asList(properties).contains("even") && Arrays.asList(properties).contains("odd")) {
            return true;
        } else if (Arrays.asList(properties).contains("duck") && Arrays.asList(properties).contains("spy")) {
            return true;
        } else if (Arrays.asList(properties).contains("sunny") && Arrays.asList(properties).contains("square")) {
            return true;
        }
        return false;
    }

    public static String mutuallyExclusivePropertiesError(String[] properties) {
        StringBuilder result = new StringBuilder();
        result.append("The request contains mutually exclusive properties: [");

        if (Arrays.asList(properties).contains("even") && Arrays.asList(properties).contains("odd")) {
            result.append("EVEN, ODD]");
        } else if (Arrays.asList(properties).contains("duck") && Arrays.asList(properties).contains("spy")) {
            result.append("DUCK, SPY]");
        } else if (Arrays.asList(properties).contains("sunny") && Arrays.asList(properties).contains("square")) {
            result.append("SUNNY, SQUARE]");
        }
        result.append("\n" + "There are no numbers with these properties.");

        return result.toString();
    }
}
