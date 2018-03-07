
import java.util.Comparator;
import java.util.TreeSet;

public class IPAddress {
    private String ip;
    IPAddress()
    {
        ip = rand(0, 255) + "." + rand(0, 255) + "." + rand(0, 255) + "." + rand(0, 255);
    }

    //***********************************main()****************************************
    public static void main (String ... args)
    {
        TreeSet<String> addresses = new TreeSet<String>(new IPv4());
        
        while (addresses.size() < 1000)
        {
            addresses.add(new IPAddress().ip);
        }
        
        //Add the first and last 10 addresses to arrays
        String[] first10 = new String[10];
        String[] last10 = new String[10];
        for (int i = 0; i < 10; i++)
        {
            first10[i] = addresses.pollFirst();
            last10[i] = addresses.pollLast();
        }
        
        //Print the first 10 IPv4 addresses
        System.out.println("The first 10 IPv4 addresses.");
        for (int i = 0; i < 10; i++)
        {
            System.out.println(first10[i]);
            addresses.add(first10[i]);
        }
        //Print the last 10 IPv4 addresses
        System.out.println("\nThe last 10 IPv4 addresses.");
        for (int i = 9; i >= 0; i--)
        {
            System.out.println(last10[i]);
            addresses.add(last10[i]);
        }
        
    }
    
  //***********************************rand()************************************
    private static int rand(int a, int b)
    {
       return((int)((b-a+1)*Math.random() + a));
    }

}

class IPv4 implements Comparator<String>
{
    @Override
    //***********************************compare()************************************
    public int compare(String str1, String str2)
    {
        int dotCount = 0;
        int i = 0;
        String[] strParts1 = new String[4];
        String[] strParts2 = new String[4];
        
        //Initializing strings in arrays
        for (int j = 0; j < 4; j++)
        {
            strParts1[j] = "";
            strParts2[j] = "";
        }
        
        //Parsing the first IPv4 into four parts in an array
        while(i < str1.length())
        {
            if (str1.charAt(i) != '.')
                strParts1[dotCount] += str1.charAt(i);
            else
                dotCount++;
            i++;
        }
        
        //Parsing the second IPv4 into four parts in an array
        i = 0;
        dotCount = 0;
        while(i < str2.length())
        {
            if (str2.charAt(i) != '.')
                strParts2[dotCount] += str2.charAt(i);
            else
                dotCount++;
            i++;
        }
        
        //Comparing the Ints
        for (int j = 0; j < 3; j++)
        {
            if (!strParts1[j].equals(strParts2[j]))
            {
                if (Integer.parseInt(strParts1[j]) < Integer.parseInt(strParts2[j]))
                    return -1;
                else
                    return 1;
            }
        }
        if (Integer.parseInt(strParts1[3]) < Integer.parseInt(strParts2[3]))
            return -1;
        else
            return 1;
    }
}