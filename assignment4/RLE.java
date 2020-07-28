class RLE {

    public String RLE_Encoding(String source) {
        StringBuffer dest = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            int runLength = 1;
            while (i + 1 < source.length() && source.charAt(i) == source.charAt(i + 1)) {
                runLength++;
                i++;
            }
            dest.append(runLength);
            dest.append(source.charAt(i));
        }
        return dest.toString();
    }

    public String RLE_DeEncoding(String text) {
        int count = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isDigit(c)) {
                count = count * 10 + c - '0';
            } else if (count == 0) {
                result.append(c);
            } else {
                while (count > 0) {
                    result.append(c);
                    count--;
                }
            }

        }
        return result.toString();
    }

}