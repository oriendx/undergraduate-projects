Given an input string, complete the deleteMe method, returning a new string in which all occurrences of "ME" are recursively deleted.
Don't use loops (don't write for or while anywhere in your code).
Do not use any regular expressions or methods such as match, split, replace, replaceAll.
Test case 1:
deleteMe("ABCDME") → "ABCD"

Test case 2:
deleteMe("PPMEMEPP") → "PPPP"

public static String deleteMe(String input) {
        char[] chars = new char[input.length()];
        return check(chars, input.toCharArray(), 0, 0);
    }

    private static String check(char[] resou, char[] chars, Integer i , Integer j) {
        if (i == chars.length) {
            return new String(resou);
        }
        if (chars[i] == 'M') {
            if (chars[i + 1] == 'E') {
                i = i + 2;
                return check(resou, chars, i, j);
            }else {
                resou[j] = chars[i];
                j++;
            }
        }else {
            resou[j] = chars[i];
            j++;
        }
        i++;
        return check(resou, chars, i, j);
    }
}
