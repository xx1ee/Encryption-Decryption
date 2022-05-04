class Problem {
    public static void main(String[] args) {
        int k = 0;
        for (int i = 0 ; i < args.length ; i++){
            if (args[i].equals("test")) {
                System.out.println(i);
                k++;
            }
        }
        if (k == 0)
        {
            System.out.println("-1");
        }
    }
}