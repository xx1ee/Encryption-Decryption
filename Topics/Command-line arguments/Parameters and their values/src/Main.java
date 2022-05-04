class Problem {
    public static void main(String[] args) {
        int k = 0;
        for (int i = 1; i < args.length ; i+=2)
        {
            System.out.println(args[k] + "=" + args[i]);
            k+=2;
        }
    }
}