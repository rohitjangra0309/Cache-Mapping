import java.util.*;
public class End {
    public static int log2(int N) {
        int result = (int)(Math.log(N) / Math.log(2));
        return result;
    }
    public static int binaryToDec(String input){
        int result;
        result = Integer.parseInt(input,2);
        return result;
    }
    static void initialize(String[][] arr,int cl,int bs){
        for(int i=0;i<cl;i++){
            for(int j=0;j<bs;j++) {
                arr[i][j] = "null";
            }
        }
    }
    static void blockade(int[] arr, int cl){
        for(int i=0;i<cl;i++) arr[i] = -1;
    }
    static void setter(int[] arr,int k){
        int partitionSize = arr.length/k;
        int x = 0;
        int i = 0;
        while(i<arr.length){
            for(int j=0;j<partitionSize;j++){
                arr[i+j] = x;
            }
            x++;
            i += partitionSize;
        }
    }
    static void Display(String[][] arr,int cl,int bs,int[] b){
        for(int i=0;i<cl;i++){
            System.out.print("Line No. (Decimal) "+i+" | ");
            if(b[i]==-1) System.out.print("No Block | ");
            else System.out.print("Block No." +b[i]+" | ");
            System.out.print("Data(Decimal) -> ");
            for(int j=0;j<bs;j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void kDisplay(String[][] arr,int cl,int bs,int[] b,int[] b1){
        for(int i=0;i<cl;i++){
            System.out.print("Set No. (Decimal) "+b1[i] +" | ");
            System.out.print("Line No. (Decimal) "+i+" | ");
            if(b[i]==-1) System.out.print("No Block | ");
            else System.out.print("Block No." +b[i]+" | ");
            System.out.print("Data(Decimal) -> ");
            for(int j=0;j<bs;j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    static void kMapper(int[][] arr,int[] b,int k,int sl){
        for(int i=0;i<b.length;i++){
            for(int j=0;j<sl;j++){
                for(int l=0;l<k;l++){
                    if(b[i]==l) arr[l][j] = i;
                }
            }
        }
        for(int i=0;i<k;i++){
            for(int j=0;j<sl;j++){
                if(i==0 && j==0) arr[i][j] -= sl-1;
                if(i!=0 && j==0) arr[i][j] -= sl-1;
            }
        }
        for(int i=0;i<k;i++){
            int add = arr[i][0];
            for(int j=0;j<sl;j++){
                if(j!=0) arr[i][j] = add+j;
            }
        }

    }
    static void directBlocker(String address, int[] la, String[][] ca, String value, int index, int offset, int blockSize){
        String blockAddress = address.substring(0,16-offset);
        int blockNumber = binaryToDec(blockAddress);
        String lastBit = blockAddress.substring(16-offset-index);
        int lineNumber = binaryToDec(lastBit);
        int wordNumber = binaryToDec(address.substring(16-offset));
        if(la[lineNumber] == -1){
            System.out.println("The Line "+lineNumber+" is Empty : Cache Miss "+" Block Number "+blockNumber+" is placed");
            la[lineNumber] = blockNumber;
        }
        else if(blockNumber==la[lineNumber]){
            System.out.println("Identical Block present "+ la[lineNumber]+" : Cache Hit, the value of word has been updated");
        }
        else{
            System.out.println("Block Number " +la[lineNumber] +" is present now in Line Number"+ lineNumber+" is replaced by Block Number "+blockNumber);
            la[lineNumber] = blockNumber;
            for(int i=0;i<blockSize;i++) ca[lineNumber][i] = "null";
        }
        if(ca[lineNumber][wordNumber].equals("null")){
            System.out.println("The word is empty : Value added");
        }
        if(!ca[lineNumber][wordNumber].equals("null")){
            System.out.println("The word is not empty : Value Updated");
        }
        ca[lineNumber][wordNumber] = value;
    }
    static void directReader(String address,int[] la,String[][] ca,int index,int offset){
        String blockAddress = address.substring(0,16-offset);
        int blockNumber = binaryToDec(blockAddress);
        String lastBit = blockAddress.substring(16-offset-index);
        int lineNumber = binaryToDec(lastBit);
        int wordNumber = binaryToDec(address.substring(16-offset));
        System.out.println("Block Number : "+blockNumber);
        System.out.println("Word Number : "+wordNumber);
        System.out.println("Line Number : "+lineNumber);
        if(blockNumber==la[lineNumber]){
            System.out.println("Block "+ blockNumber+" present : Cache Hit, the value of word is");
            System.out.println(ca[lineNumber][wordNumber]);
        }
        else if(la[lineNumber]==-1){
            System.out.println("The Line "+lineNumber+" is Empty : Cache Miss");
        }
        else{
            System.out.println("The Line "+lineNumber+" is Containing Block Number "+ la[lineNumber] +" : Cache Miss ");
            System.out.println("But the input Block Number is "+blockNumber);
        }

    }
    static void associativeBlocker(String address, int[] la, String[][] ca, String value, int offset, int blockSize){
        Random r = new Random();
        String blockAddress = address.substring(0,16-offset);
        int blockNumber = binaryToDec(blockAddress);
        int wordNumber = binaryToDec(address.substring(16-offset));
        int lineNumber = r.nextInt(la.length);
        for(int i=0;i<la.length;i++){
            if(la[i]==-1){
                lineNumber = i;
                break;
            }
        }
        for(int i=0;i<la.length;i++){
            if(la[i]==blockNumber){
                lineNumber = i;
                break;
            }
        }
        if(la[lineNumber] == -1){
            System.out.println("The Line "+lineNumber+" is Empty : Cache Miss "+" Block Number "+blockNumber+" is placed");
            la[lineNumber] = blockNumber;
        }
        else if(blockNumber==la[lineNumber]){
            System.out.println("Identical Block present "+ la[lineNumber]+" : Cache Hit, the value of word has been updated");
        }
        else{
            System.out.println("Block Number " +la[lineNumber] +" is present now in Line Number "+ lineNumber+" is replaced by Block Number "+blockNumber);
            la[lineNumber] = blockNumber;
            for(int i=0;i<blockSize;i++) ca[lineNumber][i] = "null";
        }
        if(ca[lineNumber][wordNumber].equals("null")){
            System.out.println("The word is empty : Value added");
        }
        if(!ca[lineNumber][wordNumber].equals("null")){
            System.out.println("The word is not empty : Value Updated");
        }
        ca[lineNumber][wordNumber] = value;
    }
    static void associativeReader(String address,int[] la,String[][] ca,int offset){
        Random r = new Random();
        String blockAddress = address.substring(0,16-offset);
        int blockNumber = binaryToDec(blockAddress);
        int wordNumber = binaryToDec(address.substring(16-offset));
        int lineNumber = r.nextInt(la.length);
        for(int i=0;i<la.length;i++){
            if(la[i]==-1){
                lineNumber = i;
                break;
            }
        }
        for(int i=0;i<la.length;i++){
            if(la[i]==blockNumber){
                lineNumber = i;
                break;
            }
        }
        System.out.println("Block Number : "+blockNumber);
        System.out.println("Word Number : "+wordNumber);
        System.out.println("Line Number : "+lineNumber);
        if(la[lineNumber] == -1){
            System.out.println("The Line "+lineNumber+" is Empty : Cache Miss");
        }
        else if(blockNumber==la[lineNumber]){
            System.out.println("Block "+ blockNumber+" present : Cache Hit, the value of word is");
            System.out.println(ca[lineNumber][wordNumber]);
        }
        else{
            System.out.println("The Line "+lineNumber+" is Containing Block Number "+ la[lineNumber] +" : Cache Miss ");
            System.out.println("But the input Block Number is "+blockNumber);
        }

    }
    static void wayBlocker(String address, int[] la, String[][] ca, int[][] map, String value, int offset, int blockSize,int k,int sl){
        String blockAddress = address.substring(0,16-offset);
        int blockNumber = binaryToDec(blockAddress);
        int wordNumber = binaryToDec(address.substring(16-offset));
        int setNumber = blockNumber % k;
        int lineNumber = (int) (Math.random() * (map[setNumber][map[setNumber].length-1]+1 -  map[setNumber][0]+ 1) + map[setNumber][0]);
        for(int i=0;i<sl;i++){
            if(la[map[setNumber][i]]==-1){
                lineNumber = map[setNumber][i];
                break;
            }
        }
        for(int i=0;i<sl;i++){
            if(la[map[setNumber][i]]==blockNumber){
                lineNumber = map[setNumber][i];
                break;
            }
        }

        if(la[lineNumber] == -1){
            System.out.println("The Line "+lineNumber+" is Empty : Cache Miss "+" Block Number "+blockNumber+" is placed in Set No. "+setNumber);
            la[lineNumber] = blockNumber;
        }
        else if(blockNumber==la[lineNumber]){
            System.out.println("Identical Block present "+ la[lineNumber]+" : Cache Hit, the value of word has been updated");
        }
        else{
            System.out.println("Block Number " +la[lineNumber] +" is present now in Line Number"+ lineNumber+" is replaced by Block Number "+blockNumber);
            la[lineNumber] = blockNumber;
            for(int i=0;i<blockSize;i++) ca[lineNumber][i] = "null";
        }
        if(ca[lineNumber][wordNumber].equals("null")){
            System.out.println("The word is empty : Value added");
        }
        if(!ca[lineNumber][wordNumber].equals("null")){
            System.out.println("The word is not empty : Value Updated");
        }
        ca[lineNumber][wordNumber] = value;
    }
    static void wayReader(String address, int[] la, String[][] ca, int[][] map, int offset, int k,int sl){
        String blockAddress = address.substring(0,16-offset);
        int blockNumber = binaryToDec(blockAddress);
        int wordNumber = binaryToDec(address.substring(16-offset));
        int setNumber = blockNumber % k;
        int lineNumber = (int) (Math.random() * (map[setNumber][map[setNumber].length-1]+1 -  map[setNumber][0]+ 1) + map[setNumber][0]);
        for(int i=0;i<sl;i++){
            if(la[map[setNumber][i]]==-1){
                lineNumber = map[setNumber][i];
                break;
            }
        }
        for(int i=0;i<sl;i++){
            if(la[map[setNumber][i]]==blockNumber){
                lineNumber = map[setNumber][i];
                break;
            }
        }
        System.out.println("Block Number : "+blockNumber);
        System.out.println("Word Number : "+wordNumber);
        System.out.println("Set Number : "+setNumber);
        System.out.println("Line Number : "+lineNumber);
        if(la[lineNumber] == -1){
            System.out.println("The Line "+lineNumber+" is Empty : Cache Miss");
        }
        else if(blockNumber==la[lineNumber]){
            System.out.println("Block "+ blockNumber+" present : Cache Hit, the value of word is");
            System.out.println(ca[lineNumber][wordNumber]);
        }
        else{
            System.out.println("The Line "+lineNumber+" is Containing Block Number "+ la[lineNumber] +" : Cache Miss ");
            System.out.println("But the input Block Number is "+blockNumber);
        }
    }
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the Size(power of 2) of Cache(Word) : ");
        int cacheSize = s.nextInt();
        System.out.print("Enter the Size(power of 2) of Block(Word) : ");
        int blockSize = s.nextInt();
        System.out.print("The cache lines will be equal to \"cacheSize/blockSize\" : ");
        int cacheLine = cacheSize/blockSize;
        System.out.println(cacheLine);
        int offset = log2(blockSize);
        int index = log2(cacheLine);
        String[][] cache = new String[cacheLine][blockSize];
        int[] blockChecker = new int[cacheLine];
        int[] setChecker = new int[cacheLine];
        initialize(cache,cacheLine,blockSize);
        blockade(blockChecker,cacheLine);
        System.out.print("By which type of mapping you want to perform operation on cache : ");
        System.out.println("1.Direct Mapping, 2.Fully Associative Mapping, 3.K-way set Associative Mapping : ");
        System.out.println("Enter the choice ");
        int choice = s.nextInt();
        switch (choice){
            case 1:
                System.out.println("Direct Mapping -> ");
                System.out.println("Initially the Cache Memory : ");
                Display(cache,cacheLine,blockSize,blockChecker);
                System.out.println("Enter the number of queries : ");
                int query = s.nextInt();
                for(int i=0;i<query;i++){
                    System.out.println("1.READ, 2.WRITE");
                    int choice0 = s.nextInt();
                    switch (choice0){
                        case 1:
                            System.out.println("Enter the \"16 Bit\" Address you want to Read ");
                            String address0 = s.next();
                            System.out.println();
                            directReader(address0,blockChecker,cache,index,offset);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Enter the \"16 Bit\" Address you want to write ");
                            String address = s.next();
                            System.out.println();
                            System.out.println("Enter the Data you want to write");
                            String data = s.next();
                            System.out.println();
                            directBlocker(address,blockChecker,cache,data,index,offset,blockSize);
                            System.out.println();
                            break;
                    }
                }
                System.out.println("Finally the Cache Memory : ");
                Display(cache,cacheLine,blockSize,blockChecker);
                System.out.println();
                break;
            case 2:
                System.out.println("Fully Associative -> ");
                System.out.println("Initially the Cache Memory : ");
                Display(cache,cacheLine,blockSize,blockChecker);
                System.out.println("Enter the number of queries : ");
                int query0 = s.nextInt();
                for(int i=0;i<query0;i++){
                    System.out.println("1.READ, 2.WRITE");
                    int choice1 = s.nextInt();
                    switch (choice1){
                        case 1:
                            System.out.println("Enter the \"16 Bit\" Address you want to Read ");
                            String address2 = s.next();
                            System.out.println();
                            associativeReader(address2,blockChecker,cache,offset);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Enter the \"16 Bit\" Address you want to write ");
                            String address1 = s.next();
                            System.out.println();
                            System.out.println("Enter the Data you want to write");
                            String data1 = s.next();
                            System.out.println();
                            associativeBlocker(address1,blockChecker,cache,data1,offset,blockSize);
                            System.out.println();
                            break;
                    }
                }
                System.out.println("Finally the Cache Memory : ");
                Display(cache,cacheLine,blockSize,blockChecker);
                System.out.println();
                break;
            case 3:
                System.out.println("K-Way Set Associative-> ");
                System.out.println("Enter the Value of K");
                int k = s.nextInt();
                setter(setChecker,k);
                System.out.println("Initially the Cache Memory : ");
                kDisplay(cache,cacheLine,blockSize,blockChecker,setChecker);
                int setLine = cacheLine/k;
                int[][] mapper = new int[k][setLine];
                kMapper(mapper,setChecker,k,setLine);
                System.out.println("Enter the number of queries : ");
                int query1 = s.nextInt();
                for(int i=0;i<query1;i++){
                    System.out.println("1.READ, 2.WRITE");
                    int choice2 = s.nextInt();
                    switch (choice2){
                        case 1:
                            System.out.println("Enter the \"16 Bit\" Address you want to Read ");
                            String address2 = s.next();
                            System.out.println();
                            wayReader(address2,blockChecker,cache,mapper,offset,k,setLine);
                            System.out.println();
                            break;
                        case 2:
                            System.out.println("Enter the \"16 Bit\" Address you want to write ");
                            String address1 = s.next();
                            System.out.println();
                            System.out.println("Enter the Data you want to write");
                            String data1 = s.next();
                            System.out.println();
                            wayBlocker(address1,blockChecker,cache,mapper,data1,offset,blockSize,k,setLine);
                            System.out.println();
                            break;
                    }
                }
                System.out.println("Finally the Cache Memory : ");
                kDisplay(cache,cacheLine,blockSize,blockChecker,setChecker);
                System.out.println();
                break;
        }
    }
}
