import java.util.*;
import java.io.*;
class csketch{
  static int flow,k,w;
  static int[][] table;
  static int[] s;
  static int[] temp;
  static Map<String,Integer> hmap = new HashMap<>();
  static Map<String,String> store = new HashMap<>();
  static Set<Integer> hset = new HashSet<>();
  public static void main(String[] args) throws IOException{
    try{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of flows: ");
        flow = sc.nextInt();
        System.out.println("Enter the number of hashes: ");
        k = sc.nextInt();
        System.out.println("Enter the size of each counter: ");
        w = sc.nextInt();
        table = new int[k][w];
        s = new int[k];
        temp = new int[k];
        randomgenerator();
        File f = new File("project3input.txt");
        BufferedReader readline = new BufferedReader(new FileReader(f));
        String str = readline.readLine();
        int i = 1,var;
        //int low = 0,high = Integer.MAX_VALUE/2;
        Random r = new Random();
        String[] line;
        while((str = readline.readLine()) != null){
          line = str.split("\\s+");
          var = (r.nextInt(Integer.MAX_VALUE)) + Integer.MIN_VALUE/2;
          if(hset.contains(var)){
            continue;
          }
          hset.add(var);
          hmap.put(line[0],var);
          store.put(line[0],line[1]);
          for(int j = 0;j < s.length;j++){
            encode(var,j,Integer.parseInt(line[1]));
            // table[j][hindex(var,j)] -= Integer.parseInt(line[1]);
            // else
            //   table[j][hindex(var,j)] += Integer.parseInt(line[1]);
          }
        }
        readline.close();
        int median = 0,sum = 0;
        float error;
        readline = new BufferedReader(new FileReader(f));
        str = readline.readLine();
        TreeMap<Integer,String> tmap = new TreeMap<>(Collections.reverseOrder());
        while((str = readline.readLine()) != null){
          line = str.split("\\s+");
          int num = hmap.get(line[0]);
           //{table[0][hindex(num,0)],table[1][hindex(num,1)],table[2][hindex(num,2)]};
          for(int j = 0;j < s.length;j++)
            decode(num,j);
          Arrays.sort(temp);
          median = temp[1];
          sum += Math.abs(median - Integer.parseInt(line[1]));
          tmap.put(median,line[0]);
        }
        error = (float) (sum/flow);
        i = 1;
        BufferedWriter bwriter = new BufferedWriter(new FileWriter("csketch.txt"));
        bwriter.write("The error is : "+error+ "\n");
        for(Map.Entry<Integer,String> entry : tmap.entrySet()){
          bwriter.write("\n Flow ID: " +entry.getValue()+ " Estimated Size: " +entry.getKey()+ " True Size: " +store.get(entry.getValue()));
          i += 1;
          if(i > 100)
            break;
        }
        bwriter.close();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static void randomgenerator() {
    //int high = Integer.MAX_VALUE/2;
    Random r = new Random();
    //int range = (high - low);
    for(int i=0; i<k; i++)
      s[i] = r.nextInt(Integer.MAX_VALUE) + Integer.MIN_VALUE/2;
  }
  public static void encode(int var, int position,int truesize){
    int hval = (var^s[position]) % w;
    if(hval < 0){
      hval += w;
      table[position][hval] += truesize;
    }
    else{
      table[position][hval] -= truesize;
    }
  }
  public static void decode(int var, int position){
    int hval = (var^s[position]) % w;
    if(hval < 0){
      hval += w;
      temp[position] = table[position][hval];
    }
     else{
      temp[position] = table[position][hval] * -1;
    }
  }
}
