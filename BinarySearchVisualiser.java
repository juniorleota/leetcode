public class BinarySearchVisualiser {
    /**
     * Prints out binary search state in the current format [1ₗ, 1, 1, 2, 2, 2, 3ₘ, 3, 3, 4, 4, 4, 4ₕ].
     *
     * @param arr array current being binary searched
     * @param l   low pointer
     * @param m   mid pointer
     * @param h   high pointer
     */
    public static void print(int[] arr, int l, int m, int h) {
        int n = arr.length;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < n; i++) {
            String elem = genCurrentElement(arr, i, l, m, h);
            sb.append(elem);
            if (i < n - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        System.out.println(sb);
    }

    private static String genCurrentElement(int[] arr, int i, int l, int m, int h) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[i]);

        if (i == l) sb.append(Pointer.LOW.subscript);
        if (i == m) sb.append(Pointer.MID.subscript);
        if (i == h) sb.append(Pointer.HIGH.subscript);

        return sb.toString();
    }

    enum Pointer {
        MID("\u2098"),
        LOW("\u2097"),
        HIGH("\u2095");

        String subscript;

        Pointer(String subscript) {
            this.subscript = subscript;
        }
    }
}
