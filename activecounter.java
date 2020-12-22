import java.util.*;
import java.io.*;
class activecounter{
  static int csize;
  static int[] counter;
  static Map<String,Integer> hmap = new HashMap<>();
  static Set<Integer> hset = new HashSet<>();
  public static void main(String[] args) throws IOException{
    try{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the active counter: ");
        csize = sc.nextInt();
        counter = new int[csize];
        int i = 1,var;
        Random r = new Random();
        String ce = "";
        int ceval = 0,cnval = 1;
        for(int k = 0;k < csize/2;k++)
          ce += "0";
        while(i != 1000000){
          if(r.nextInt((int) Math.pow(2,ceval)) == 0){
            String bin = Integer.toBinaryString(cnval);
            while(bin.length() < csize/2){
              bin = "0"+bin;
            }
            if(bin.length() > csize/2){
              bin = bin.substring(0,(csize/2) + 1);
              cnval /= 2;
              cnval -= 1;
              ceval += 1;
              ce = Integer.toBinaryString(ceval);
              while(ce.length() < csize/2){
                ce = "0"+ce;
              }
              for(int j = csize/2,index = 0;j < csize;j++,index++){
               counter[j] = Character.getNumericValue(ce.charAt(index));
              }
            }

            for(int j = 0;j < csize/2;j++){
              counter[j] = Character.getNumericValue(bin.charAt(j));
            }
            cnval += 1;
          }
          i += 1;
        }
        int num=0,exponent=0;
        for(int j = 0;j < csize;j++){
          if(j < csize/2){
            num = num*2 + counter[j];
          }
          else
            exponent = exponent*2 + counter[j];
        }
        BufferedWriter bwriter = new BufferedWriter(new FileWriter("activecounter.txt"));
        bwriter.write("The final value of the active counter is : "+num * Math.pow(2,exponent)+ "\n");
        bwriter.close();
        //System.out.println(num * Math.pow(2,exponent));
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
