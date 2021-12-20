class Stats{
    public static void main(String[] args){
        if (args[0].equals("--product")){
            double out = 1;
            for (int i = 1; i < args.length; i++){
                out *= Double.parseDouble(args[i]);
            }
            System.out.print(out);
        }
        else if (args[0].equals("--mean")){
            double out = 0;
            for (int i = 1; i < args.length; i++){
                out += Double.parseDouble(args[i]);
            }
            out /= args.length - 1;
            System.out.print(out);
        }
        else if (args[0].equals("--total")){
            double out = 0;
            for (int i = 1; i < args.length; i++){
                out += Double.parseDouble(args[i]);
            }
            System.out.print(out);
        }
        else if (args[0].equals("--max")){
            double max = Double.parseDouble(args[1]);
            for (int i = 1; i < args.length; i++){
                if (Double.parseDouble(args[i]) > max){
                    max = Double.parseDouble(args[i]);
                }
            }
            System.out.print(max);
        }
        else if (args[0].equals("--min")){
            double min = Double.parseDouble(args[1]);
            for (int i = 1; i < args.length; i++){
                if (Double.parseDouble(args[i]) < min){
                    min = Double.parseDouble(args[i]);
                }
            }
            System.out.print(min);
        }
        else {
            System.out.print("Bad option " + args[0]);
        }
    }
}
//Evanston Zhou helped me with stats.java