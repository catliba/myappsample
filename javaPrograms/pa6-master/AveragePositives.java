class AveragePositives {
    public static void main(String[] args){//we expect inputs of be appropriate inputs to Double.parseDouble
        if (args.length == 0){
            System.out.println(0);
            return;
        }
        double total = 0;
        double count = 0;
        for (int i = 0; i < args.length; i++){
            if (Double.parseDouble(args[i])>0){
                total += Double.parseDouble(args[i]);
                count++;
            }
        }
        if (count == 0){
            count = 1;
        }
        double mean = total/ count;
        System.out.println("The mean is "+ mean);
    }
}
